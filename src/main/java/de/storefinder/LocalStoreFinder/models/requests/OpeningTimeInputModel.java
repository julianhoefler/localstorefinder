package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class OpeningTimeInputModel {

    private boolean isOpen;

    @Basic
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Basic
    @Temporal(TemporalType.TIME)
    private Date closingTime;
}
