package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class OpeningTimeInputModel {

    private boolean isOpen;

    private Time openingTime;

    private Time closingTime;
}
