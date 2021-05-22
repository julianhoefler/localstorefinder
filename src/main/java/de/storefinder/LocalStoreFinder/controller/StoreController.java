package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.mapper.AddressMapper;
import de.storefinder.LocalStoreFinder.mapper.OpeningTimesMapper;
import de.storefinder.LocalStoreFinder.mapper.PaymentMapper;
import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OpeningTimesRepository openingTimesRepository;

    @Autowired
    OpeningTimeRepository openingTimeRepository;

    @PutMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Erstellt einen Store mit zufälliger UUID")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity createStore(@RequestBody StoreInputModel storeInputModel) {
        String uuid = UUID.randomUUID().toString();

        Address address = AddressMapper.mapToEntity(storeInputModel.getAddress());
        addressRepository.save(address);

        Payment payment = PaymentMapper.mapToEntity(storeInputModel.getPayment());
        paymentRepository.save(payment);

        OpeningTimes openingTimes = OpeningTimesMapper.mapToEntity(storeInputModel.getOpeningTimes(), openingTimeRepository);
        openingTimesRepository.save(openingTimes);

        Store store = Store.builder()
                .id(uuid)
                .name(storeInputModel.getName())
                .address(address.getId())
                .description(storeInputModel.getDescription())
                .payment(payment.getId())
                .openingTimes(openingTimes.getId())
                .preImage(storeInputModel.getPreImage())
                .build();

        storeRepository.save(store);
        return new ResponseEntity("Successfully added to database.", HttpStatus.OK);
    }

    @GetMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus", content = @Content(array = @ArraySchema(schema = @Schema(implementation = StoreOutputModel.class))))
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity<?> getAllStores() {
        ArrayList<StoreOutputModel> outputStores = new ArrayList<>();
        Iterable<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            Optional<Address> address = addressRepository.findById(store.getAddress());
            Optional<Payment> payment = paymentRepository.findById(store.getPayment());
            Optional<OpeningTimes> openingTimes = openingTimesRepository.findById(store.getOpeningTimes());

            if (address.isPresent() && payment.isPresent() && openingTimes.isPresent()) {

                StoreOutputModel storeOutputModel = StoreOutputModel.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .description(store.getDescription())
                        .address(address.get())
                        .payment(payment.get())
                        .preImage(store.getPreImage())
                        .openingTimes(
                                OpeningTimesMapper.mapToResponse(openingTimes.get(), openingTimeRepository)
                        )
                        .build();
                outputStores.add(storeOutputModel);
            } else {
                return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(outputStores, HttpStatus.OK);
    }
}
