package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTime;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimeInputModel;

import java.util.UUID;

public class OpeningTimeMapper {

    public static OpeningTime mapToEntity(OpeningTimeInputModel openingTimeInputModel) {
        String uuid = UUID.randomUUID().toString();

        return OpeningTime.builder()
                .id(uuid)
                .isOpen(openingTimeInputModel.isOpen())
                .openingTime(openingTimeInputModel.getOpeningTime())
                .closingTime(openingTimeInputModel.getClosingTime())
                .build();
    }
}
