package de.storefinder.LocalStoreFinder.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

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

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openingTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closingTime;
}
