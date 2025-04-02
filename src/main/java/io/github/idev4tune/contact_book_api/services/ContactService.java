package io.github.idev4tune.contact_book_api.services;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;

public interface ContactService {

    ContactDto save(Contact contact);

}
