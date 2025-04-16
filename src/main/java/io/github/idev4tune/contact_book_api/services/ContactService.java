package io.github.idev4tune.contact_book_api.services;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {

    ContactDto save(Contact contact);

    Page<ContactDto> findAll(Pageable pageable);

}
