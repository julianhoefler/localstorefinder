package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.responses.CategoryOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
@AllArgsConstructor
public class StoreMapper {
    private AddressRepository addressRepository;
    private PaymentRepository paymentRepository;
    private OpeningTimesRepository openingTimesRepository;
    private OpeningTimeRepository openingTimeRepository;
    private CategoryRepository categoryRepository;

    public StoreOutputModel mapToResponse(Store store) {
        Optional<Address> address = addressRepository.findById(store.getAddress());
        Optional<Payment> payment = paymentRepository.findById(store.getPayment());
        Optional<OpeningTimes> openingTimes = openingTimesRepository.findById(store.getOpeningTimes());

        List<String> categoriesIdList = store.getCategories().stream().map(StoreCategory::getCategoryId).collect(Collectors.toList());
        ArrayList<CategoryOutputModel> categoryOutputModels = new ArrayList<>();

        for (String categoryId : categoriesIdList) {
            CategoryOutputModel categoryOutputModel = CategoryMapper.mapToResponse(categoryId, categoryRepository);
            if (categoryOutputModel != null) {
                categoryOutputModels.add(categoryOutputModel);
            }
        }

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
                    .categories(categoryOutputModels)
                    .build();
        }
        return null;
    }
}
