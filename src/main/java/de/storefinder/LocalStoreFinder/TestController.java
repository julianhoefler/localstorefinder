package de.storefinder.LocalStoreFinder;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/stores")
    @ApiResponse(responseCode = "200", description = "Gibt alle Stores aus")
    @ApiResponse(responseCode = "400", description = "Die eingegebenen Parameter stimmen nicht")
    public String getAllStores(@RequestParam String plz, @RequestParam int umkreis) {
        //TODO: Implement real logic
        return "Alle Stores im Umkreis von " + umkreis + " km um " + plz;
    }
}
