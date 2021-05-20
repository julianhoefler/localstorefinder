package de.storefinder.LocalStoreFinder.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    private Time openingTime;

    private Time closingTime;
}
