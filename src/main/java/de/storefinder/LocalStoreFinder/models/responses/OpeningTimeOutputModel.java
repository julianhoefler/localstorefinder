package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class OpeningTimeOutputModel {

    private boolean isOpen;

    private Time openingTime;

    private Time closingTime;
}
