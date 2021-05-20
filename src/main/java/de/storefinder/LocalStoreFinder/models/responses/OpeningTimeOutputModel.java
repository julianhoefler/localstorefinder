package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class OpeningTimeOutputModel {

    private boolean isOpen;

    @Basic
    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Basic
    @Temporal(TemporalType.TIME)
    private Date closingTime;
}
