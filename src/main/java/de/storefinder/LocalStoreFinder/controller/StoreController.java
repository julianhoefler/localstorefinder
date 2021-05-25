package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.mapper.AddressMapper;
import de.storefinder.LocalStoreFinder.mapper.OpeningTimesMapper;
import de.storefinder.LocalStoreFinder.mapper.PaymentMapper;
import de.storefinder.LocalStoreFinder.mapper.StoreMapper;
import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import de.storefinder.LocalStoreFinder.services.StoreInputValidationService;
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

    @Autowired
    StoreInputValidationService storeInputValidationService;

    @Autowired
    StoreMapper storeMapper;

    @PutMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Erstellt einen Store mit zufälliger UUID")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity<String> createStore(@RequestBody StoreInputModel storeInputModel) {
        if (storeInputValidationService.validate(storeInputModel)) {
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
            return new ResponseEntity<>("Successfully added to database.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Request Data not correct", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = StoreOutputModel.class))))
    @ApiResponse(responseCode = "500", description = "Die Daten im Backend sind nicht konsistent oder ein anderer Fehler ist aufgetreten",
            content = @Content(schema = @Schema(implementation = String.class, example = "Something went wrong! Try it again later")))
    public ResponseEntity<?> getAllStores() {
        ArrayList<StoreOutputModel> outputStores = new ArrayList<>();
        Iterable<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            StoreOutputModel storeOutputModel = storeMapper.mapToResponse(store);
            if (storeOutputModel != null) {
                outputStores.add(storeOutputModel);
            } else {
                return new ResponseEntity<>("Something went wrong! Try it again later", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(outputStores, HttpStatus.OK);
    }
}
