package io.github.idev4tune.contact_book_api.repositories;

import io.github.idev4tune.contact_book_api.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
