package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpeningTimesOutputModel {

    private OpeningTimeOutputModel monday;

    private OpeningTimeOutputModel tuesday;

    private OpeningTimeOutputModel wednesday;

    private OpeningTimeOutputModel thursday;

    private OpeningTimeOutputModel friday;

    private OpeningTimeOutputModel saturday;

    private OpeningTimeOutputModel sunday;
}
