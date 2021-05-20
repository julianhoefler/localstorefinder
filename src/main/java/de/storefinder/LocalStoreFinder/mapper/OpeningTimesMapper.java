package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTime;
import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimesInputModel;
import de.storefinder.LocalStoreFinder.repositories.OpeningTimeRepository;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OpeningTimesMapper {

    public static OpeningTimes mapToEntity(OpeningTimesInputModel openingTimesInputModel, OpeningTimeRepository openingTimeRepository) {
        String uuid = UUID.randomUUID().toString();

        OpeningTime monday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getMonday());
        OpeningTime tuesday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getTuesday());
        OpeningTime wednesday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getWednesday());
        OpeningTime thursday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getThursday());
        OpeningTime friday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getFriday());
        OpeningTime saturday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getSaturday());
        OpeningTime sunday = OpeningTimeMapper.mapToEntity(openingTimesInputModel.getSunday());

        openingTimeRepository.save(monday);
        openingTimeRepository.save(tuesday);
        openingTimeRepository.save(wednesday);
        openingTimeRepository.save(thursday);
        openingTimeRepository.save(friday);
        openingTimeRepository.save(saturday);
        openingTimeRepository.save(sunday);

        return OpeningTimes.builder()
                .id(uuid)
                .monday(monday.getId())
                .tuesday(tuesday.getId())
                .wednesday(wednesday.getId())
                .thursday(thursday.getId())
                .friday(friday.getId())
                .saturday(saturday.getId())
                .sunday(sunday.getId())
                .build();
    }
}
