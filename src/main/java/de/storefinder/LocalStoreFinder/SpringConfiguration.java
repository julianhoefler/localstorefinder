package de.storefinder.LocalStoreFinder;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import de.storefinder.LocalStoreFinder.mapper.StoreMapper;
import de.storefinder.LocalStoreFinder.repositories.*;
import de.storefinder.LocalStoreFinder.services.StoreInputValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.format.DateTimeFormatter;

@Configuration
public class SpringConfiguration {
    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String timeFormat = "HH:mm";

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OpeningTimeRepository openingTimeRepository;

    @Autowired
    OpeningTimesRepository openingTimesRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            builder.serializers(new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)));
        };
    }

    @Bean
    public StoreInputValidationService storeInputValidationService() {
        return new StoreInputValidationService();
    }

    @Bean
    public StoreMapper storeMapper() {
        StoreMapper storeMapper = new StoreMapper();
        storeMapper.setAddressRepository(addressRepository);
        storeMapper.setPaymentRepository(paymentRepository);
        storeMapper.setOpeningTimeRepository(openingTimeRepository);
        storeMapper.setOpeningTimesRepository(openingTimesRepository);
        storeMapper.setCategoryRepository(categoryRepository);

        return storeMapper;
    }
}
