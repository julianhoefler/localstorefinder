package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, String> {
}
