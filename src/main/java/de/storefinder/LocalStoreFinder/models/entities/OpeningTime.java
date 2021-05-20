package de.storefinder.LocalStoreFinder.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
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

    @Basic
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Basic
    @Temporal(TemporalType.TIME)
    private Date closingTime;
}
