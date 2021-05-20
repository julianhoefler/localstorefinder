package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTime;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimeInputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimeOutputModel;

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

    public static OpeningTimeOutputModel mapToResponse(OpeningTime openingTime) {
        return OpeningTimeOutputModel.builder()
                .isOpen(openingTime.isOpen())
                .openingTime(openingTime.getOpeningTime())
                .closingTime(openingTime.getClosingTime())
                .build();
    }
}
