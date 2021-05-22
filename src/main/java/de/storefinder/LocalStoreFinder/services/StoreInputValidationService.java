package de.storefinder.LocalStoreFinder.services;

import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimeInputModel;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimesInputModel;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;

import java.time.LocalTime;

public class StoreInputValidationService {
    public boolean validate(StoreInputModel storeInputModel) {
        if (validateAddress(storeInputModel.getAddress())
                && validateOpeningTimes(storeInputModel.getOpeningTimes())) {
            return true;
        }
        return false;
    }

    private boolean validateAddress(AddressInputModel addressInputModel) {
        return addressInputModel.getCity() != null
                && addressInputModel.getStreet() != null
                && addressInputModel.getZip() != null
                && addressInputModel.getNumber() > 0;
    }

    private boolean validateOpeningTimes(OpeningTimesInputModel openingTimesInputModel) {
        return validateOpeningTime(openingTimesInputModel.getMonday())
                && validateOpeningTime(openingTimesInputModel.getTuesday())
                && validateOpeningTime(openingTimesInputModel.getWednesday())
                && validateOpeningTime(openingTimesInputModel.getThursday())
                && validateOpeningTime(openingTimesInputModel.getFriday())
                && validateOpeningTime(openingTimesInputModel.getSaturday())
                && validateOpeningTime(openingTimesInputModel.getSunday());
    }

    private boolean validateOpeningTime(OpeningTimeInputModel openingTimeInputModel) {
        if (openingTimeInputModel.isOpen() && openingTimeInputModel.getOpeningTime() != null && openingTimeInputModel.getClosingTime() != null) {
            try {
                LocalTime.parse(openingTimeInputModel.getOpeningTime());
                LocalTime.parse(openingTimeInputModel.getClosingTime());
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
