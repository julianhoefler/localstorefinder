package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTime;
import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimesInputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimeOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimesOutputModel;
import de.storefinder.LocalStoreFinder.repositories.OpeningTimeRepository;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;
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

    public static OpeningTimesOutputModel mapToResponse(OpeningTimes openingTimes, OpeningTimeRepository openingTimeRepository) {
        Optional<OpeningTime> monday = openingTimeRepository.findById(openingTimes.getMonday());
        Optional<OpeningTime> tuesday = openingTimeRepository.findById(openingTimes.getTuesday());
        Optional<OpeningTime> wednesday = openingTimeRepository.findById(openingTimes.getWednesday());
        Optional<OpeningTime> thursday = openingTimeRepository.findById(openingTimes.getThursday());
        Optional<OpeningTime> friday = openingTimeRepository.findById(openingTimes.getFriday());
        Optional<OpeningTime> saturday = openingTimeRepository.findById(openingTimes.getSaturday());
        Optional<OpeningTime> sunday = openingTimeRepository.findById(openingTimes.getSunday());

        if (
                monday.isPresent()
                        && tuesday.isPresent()
                        && wednesday.isPresent()
                        && thursday.isPresent()
                        && friday.isPresent()
                        && saturday.isPresent()
                        && sunday.isPresent()
        ) {
            return OpeningTimesOutputModel.builder()
                    .monday(OpeningTimeMapper.mapToResponse(monday.get()))
                    .tuesday(OpeningTimeMapper.mapToResponse(tuesday.get()))
                    .wednesday(OpeningTimeMapper.mapToResponse(wednesday.get()))
                    .thursday(OpeningTimeMapper.mapToResponse(thursday.get()))
                    .friday(OpeningTimeMapper.mapToResponse(friday.get()))
                    .saturday(OpeningTimeMapper.mapToResponse(saturday.get()))
                    .sunday(OpeningTimeMapper.mapToResponse(sunday.get()))
                    .build();
        }

        return null;
    }
}
