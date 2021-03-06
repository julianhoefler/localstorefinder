package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.models.responses.CategoryOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@AllArgsConstructor
public class StoreMapper {
    private AddressRepository addressRepository;
    private PaymentRepository paymentRepository;
    private OpeningTimesRepository openingTimesRepository;
    private OpeningTimeRepository openingTimeRepository;
    private CategoryRepository categoryRepository;
    private StoreCategoryRepository storeCategoryRepository;

    public Store mapToEntity(StoreInputModel storeInputModel) {
        String uuid = UUID.randomUUID().toString();

        Address address = AddressMapper.mapToEntity(storeInputModel.getAddress());
        addressRepository.save(address);

        Payment payment = PaymentMapper.mapToEntity(storeInputModel.getPayment());
        paymentRepository.save(payment);

        OpeningTimes openingTimes = OpeningTimesMapper.mapToEntity(storeInputModel.getOpeningTimes(), openingTimeRepository);
        openingTimesRepository.save(openingTimes);

        ArrayList<StoreCategory> storeCategories = new ArrayList<>();

        for (String storeCategoryId : storeInputModel.getCategories()) {
            StoreCategory storeCategory = StoreCategoryMapper.mapToEntity(storeCategoryId, uuid);
            storeCategories.add(storeCategory);
            storeCategoryRepository.save(storeCategory);
        }

        return Store.builder()
                .id(uuid)
                .name(storeInputModel.getName())
                .address(address.getId())
                .description(storeInputModel.getDescription())
                .payment(payment.getId())
                .openingTimes(openingTimes.getId())
                .preImage(storeInputModel.getPreImage())
                .categories(storeCategories)
                .isActive(storeInputModel.isActive())
                .build();
    }

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
                    .isActive(store.isActive())
                    .build();
        }
        return null;
    }
}
