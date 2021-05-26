package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import de.storefinder.LocalStoreFinder.models.entities.Store;
import de.storefinder.LocalStoreFinder.repositories.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
