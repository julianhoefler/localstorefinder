package de.storefinder.LocalStoreFinder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public String createStore(@RequestBody StoreInputModel storeInputModel) {
        String uuid = UUID.randomUUID().toString();

        Store store = new Store();
        store.setId(uuid);
        store.setAddress(storeInputModel.getAddress());
        store.setName(storeInputModel.getName());
        store.setZip(storeInputModel.getZip());

        storeRepository.save(store);
        return "Successful";
    }

    @GetMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public String getAllStores(@RequestParam String plz, @RequestParam int umkreis) {
        //TODO: Implement real logic
        return "Alle Stores im Umkreis von " + umkreis + " km um " + plz;
    }
}
