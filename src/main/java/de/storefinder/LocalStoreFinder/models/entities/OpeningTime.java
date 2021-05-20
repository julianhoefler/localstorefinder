package de.storefinder.LocalStoreFinder.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@Entity
public class OpeningTime {

    public OpeningTime() {
    }

    @Id
    private String id;

    private boolean isOpen;

    private Timestamp openingTime;

    private Timestamp closingTime;
}
