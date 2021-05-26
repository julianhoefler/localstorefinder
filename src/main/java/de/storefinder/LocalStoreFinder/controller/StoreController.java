package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.mapper.AddressMapper;
import de.storefinder.LocalStoreFinder.mapper.OpeningTimeMapper;
import de.storefinder.LocalStoreFinder.mapper.OpeningTimesMapper;
import de.storefinder.LocalStoreFinder.mapper.PaymentMapper;
import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimeOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimesOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
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
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity<?> getAllStores() {
        ArrayList<StoreOutputModel> outputStores = new ArrayList<>();
        Iterable<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            Optional<Address> address = addressRepository.findById(store.getAddress());
            Optional<Payment> payment = paymentRepository.findById(store.getPayment());
            Optional<OpeningTimes> openingTimes = openingTimesRepository.findById(store.getOpeningTimes());

            if (address.isPresent() && payment.isPresent() && openingTimes.isPresent()) {
                Optional<OpeningTime> monday = openingTimeRepository.findById(openingTimes.get().getMonday());
                Optional<OpeningTime> tuesday = openingTimeRepository.findById(openingTimes.get().getTuesday());
                Optional<OpeningTime> wednesday = openingTimeRepository.findById(openingTimes.get().getWednesday());
                Optional<OpeningTime> thursday = openingTimeRepository.findById(openingTimes.get().getThursday());
                Optional<OpeningTime> friday = openingTimeRepository.findById(openingTimes.get().getFriday());
                Optional<OpeningTime> saturday = openingTimeRepository.findById(openingTimes.get().getSaturday());
                Optional<OpeningTime> sunday = openingTimeRepository.findById(openingTimes.get().getSunday());

                if (
                        monday.isPresent()
                                && tuesday.isPresent()
                                && wednesday.isPresent()
                                && thursday.isPresent()
                                && friday.isPresent()
                                && saturday.isPresent()
                                && sunday.isPresent()
                ) {

                    StoreOutputModel storeOutputModel = StoreOutputModel.builder()
                            .id(store.getId())
                            .name(store.getName())
                            .description(store.getDescription())
                            .address(address.get())
                            .payment(payment.get())
                            .preImage(store.getPreImage())
                            .openingTimes(
                                    OpeningTimesOutputModel.builder()
                                            .monday(OpeningTimeMapper.mapToResponse(monday.get()))
                                            .tuesday(OpeningTimeMapper.mapToResponse(tuesday.get()))
                                            .wednesday(OpeningTimeMapper.mapToResponse(wednesday.get()))
                                            .thursday(OpeningTimeMapper.mapToResponse(thursday.get()))
                                            .friday(OpeningTimeMapper.mapToResponse(friday.get()))
                                            .saturday(OpeningTimeMapper.mapToResponse(saturday.get()))
                                            .sunday(OpeningTimeMapper.mapToResponse(sunday.get()))
                                            .build()
                            )
                            .build();
                    outputStores.add(storeOutputModel);
                } else {
                    return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(outputStores, HttpStatus.OK);
    }

    @DeleteMapping("/stores/{uuid}")
    @ApiResponse(responseCode = "200", description = "Erstellt einen Store mit zufälliger UUID")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity<String> deleteById(@PathVariable String uuid) {
        Optional<Store> store = storeRepository.findById(uuid);
        Optional<OpeningTimes> openingTimes = openingTimesRepository.findById(store.get().getOpeningTimes());

        openingTimeRepository.deleteById(openingTimes.get().getMonday());
        openingTimeRepository.deleteById(openingTimes.get().getTuesday());
        openingTimeRepository.deleteById(openingTimes.get().getWednesday());
        openingTimeRepository.deleteById(openingTimes.get().getThursday());
        openingTimeRepository.deleteById(openingTimes.get().getFriday());
        openingTimeRepository.deleteById(openingTimes.get().getSaturday());
        openingTimeRepository.deleteById(openingTimes.get().getSunday());

        openingTimesRepository.deleteById(store.get().getOpeningTimes());
        addressRepository.deleteById(store.get().getAddress());
        paymentRepository.deleteById(store.get().getPayment());

        storeRepository.deleteById(uuid);
        return new ResponseEntity<>("Deleted store Successfully", HttpStatus.OK);
    }
}
