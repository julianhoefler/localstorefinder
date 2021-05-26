package de.storefinder.LocalStoreFinder.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalTime;

@Data
@Builder
public class OpeningTimeInputModel {

    private boolean isOpen;

    @JsonFormat(pattern = "HH:mm")
    private String openingTime;

    @JsonFormat(pattern = "HH:mm")
    private String closingTime;
}
