package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OpeningTimeInputModel {

    private boolean isOpen;

    private Timestamp openingTime;

    private Timestamp closingTime;
}
