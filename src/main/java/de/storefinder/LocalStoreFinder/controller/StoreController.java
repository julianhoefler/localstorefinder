package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.mapper.StoreMapper;
import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import de.storefinder.LocalStoreFinder.models.entities.Store;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.models.responses.PutOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import de.storefinder.LocalStoreFinder.services.FilterService;
import de.storefinder.LocalStoreFinder.services.StoreInputValidationService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@Setter
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
    private StoreCategoryRepository storeCategoryRepository;

    @Autowired
    OpeningTimeRepository openingTimeRepository;

    @Autowired
    StoreInputValidationService storeInputValidationService;

    @Autowired
    StoreMapper storeMapper;

    @Autowired
    FilterService filterService;

    @PutMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Erstellt einen Store mit zufälliger UUID")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity<PutOutputModel<String>> createStore(@RequestBody StoreInputModel storeInputModel) {
        if (storeInputValidationService.validate(storeInputModel)) {
            Store store = storeMapper.mapToEntity(storeInputModel);

            storeRepository.save(store);

            PutOutputModel<String> response = PutOutputModel.<String>builder()
                    .id(store.getId())
                    .message("Store successfully added")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity(PutOutputModel.<String>builder()
                .message("Request Data not correct")
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = StoreOutputModel.class))))
    @ApiResponse(responseCode = "500", description = "Die Daten im Backend sind nicht konsistent oder ein anderer Fehler ist aufgetreten",
            content = @Content(schema = @Schema(implementation = String.class, example = "Something went wrong! Try it again later")))
    public ResponseEntity<?> getAllStores(@RequestParam(required = false) String zip, @RequestParam(required = false) Integer umkreis) {
        ArrayList<StoreOutputModel> outputStores = new ArrayList<>();
        Iterable<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            StoreOutputModel storeOutputModel = storeMapper.mapToResponse(store);
            if (storeOutputModel != null) {
                if (zip != null) {
                    if (filterService.checkFilter(zip, umkreis, storeOutputModel)) {
                        outputStores.add(storeOutputModel);
                    }
                } else {
                    outputStores.add(storeOutputModel);
                }
            } else {
                return new ResponseEntity<>("Something went wrong! Try it again later", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(outputStores, HttpStatus.OK);
    }

    @GetMapping("/stores/{uuid}")
    @ApiResponse(responseCode = "200", description = "Erstellt einen Store mit zufälliger UUID")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public ResponseEntity<?> getStoreByUuid(@PathVariable String uuid) {
        Optional<Store> store = storeRepository.findById(uuid);

        if (store.isPresent()) {
            StoreOutputModel storeOutputModel = storeMapper.mapToResponse(store.get());
            return new ResponseEntity<>(storeOutputModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong! Try it again later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/stores/{uuid}")
    @ApiResponse(responseCode = "200", description = "Erstellt einen Store mit zufälliger UUID")
    @ApiResponse(responseCode = "404", description = "Die eingegebenen Parameter stimmen nicht")
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
        storeCategoryRepository.deleteAllByStoreId(uuid);

        storeRepository.deleteById(uuid);
        return new ResponseEntity<>("Deleted store Successfully", HttpStatus.OK);
    }
}
