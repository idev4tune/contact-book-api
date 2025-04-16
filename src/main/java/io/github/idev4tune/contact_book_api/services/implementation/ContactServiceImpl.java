package io.github.idev4tune.contact_book_api.services.implementation;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;
import io.github.idev4tune.contact_book_api.repositories.ContactRepository;
import io.github.idev4tune.contact_book_api.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public ContactDto save(Contact contact) {
        return ContactDto.fromContact(repository.save(contact));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ContactDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ContactDto::fromContact);
    }

}
