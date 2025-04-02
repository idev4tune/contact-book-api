package io.github.idev4tune.contact_book_api.services.implementation;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;
import io.github.idev4tune.contact_book_api.repositories.ContactRepository;
import io.github.idev4tune.contact_book_api.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public ContactDto save(Contact contact) {
        return ContactDto.fromContact(repository.save(contact));
    }

}
