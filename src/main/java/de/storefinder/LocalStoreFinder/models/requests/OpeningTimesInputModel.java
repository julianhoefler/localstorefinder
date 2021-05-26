package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpeningTimesInputModel {

    private OpeningTimeInputModel monday;

    private OpeningTimeInputModel tuesday;

    private OpeningTimeInputModel wednesday;

    private OpeningTimeInputModel thursday;

    private OpeningTimeInputModel friday;

    private OpeningTimeInputModel saturday;

    private OpeningTimeInputModel sunday;
}
