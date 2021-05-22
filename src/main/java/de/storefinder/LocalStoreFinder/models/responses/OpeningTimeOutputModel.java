package de.storefinder.LocalStoreFinder.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
public final class OpeningTimeOutputModel {

    private boolean isOpen;


    @JsonFormat(pattern = "HH:mm")
    private final LocalTime openingTime;

    @JsonFormat(pattern = "HH:mm")
    private final LocalTime closingTime;
}
