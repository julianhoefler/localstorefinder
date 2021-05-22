package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTime;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimeInputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimeOutputModel;

import java.time.LocalTime;
import java.util.UUID;

public class OpeningTimeMapper {

    public static OpeningTime mapToEntity(OpeningTimeInputModel openingTimeInputModel) {
        String uuid = UUID.randomUUID().toString();

        if (!openingTimeInputModel.isOpen()) {
            return OpeningTime.builder()
                    .id(uuid)
                    .isOpen(openingTimeInputModel.isOpen())
                    .build();
        }

        return OpeningTime.builder()
                .id(uuid)
                .isOpen(openingTimeInputModel.isOpen())
                .openingTime(LocalTime.parse(openingTimeInputModel.getOpeningTime()))
                .closingTime(LocalTime.parse(openingTimeInputModel.getClosingTime()))
                .build();
    }

    public static OpeningTimeOutputModel mapToResponse(OpeningTime openingTime) {
        if (!openingTime.isOpen()) {
            return OpeningTimeOutputModel.builder()
                    .isOpen(openingTime.isOpen())
                    .build();
        }
        return OpeningTimeOutputModel.builder()
                .isOpen(openingTime.isOpen())
                .openingTime(openingTime.getOpeningTime().toString())
                .closingTime(openingTime.getClosingTime().toString())
                .build();
    }
}
