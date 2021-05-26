package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import de.storefinder.LocalStoreFinder.models.entities.Payment;
import de.storefinder.LocalStoreFinder.models.entities.Store;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.AddressRepository;
import de.storefinder.LocalStoreFinder.repositories.OpeningTimeRepository;
import de.storefinder.LocalStoreFinder.repositories.OpeningTimesRepository;
import de.storefinder.LocalStoreFinder.repositories.PaymentRepository;
import lombok.Setter;

import java.util.Optional;

@Setter
public class StoreMapper {
    private AddressRepository addressRepository;
    private PaymentRepository paymentRepository;
    private OpeningTimesRepository openingTimesRepository;
    private OpeningTimeRepository openingTimeRepository;

    public StoreOutputModel mapToResponse(Store store) {
        Optional<Address> address = addressRepository.findById(store.getAddress());
        Optional<Payment> payment = paymentRepository.findById(store.getPayment());
        Optional<OpeningTimes> openingTimes = openingTimesRepository.findById(store.getOpeningTimes());

        if (address.isPresent() && payment.isPresent() && openingTimes.isPresent()) {

            return StoreOutputModel.builder()
                    .id(store.getId())
                    .name(store.getName())
                    .description(store.getDescription())
                    .address(
                            AddressMapper.mapToResponse(address.get())
                    )
                    .payment(
                            PaymentMapper.mapToResponse(payment.get())
                    )
                    .preImage(store.getPreImage())
                    .openingTimes(
                            OpeningTimesMapper.mapToResponse(openingTimes.get(), openingTimeRepository)
                    )
                    .build();
        }
        return null;
    }
}
