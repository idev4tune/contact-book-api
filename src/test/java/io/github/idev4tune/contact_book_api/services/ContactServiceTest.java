package io.github.idev4tune.contact_book_api.services;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;
import io.github.idev4tune.contact_book_api.repositories.ContactRepository;
import io.github.idev4tune.contact_book_api.services.implementation.ContactServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.databind.ObjectWriter.GeneratorSettings.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    private final Contact contact = Contact.builder()
                                    .name("Bruno")
                                    .email("bruno@teste.com")
                                    .favorite(true)
                                    .build();

    private final Page<Contact> contacts = new PageImpl<>(Collections.singletonList(contact));

    private Page<ContactDto> contactsDto;

    private final PageRequest pageRequest = PageRequest.of(0, 1);

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    @DisplayName("(1) It must create a new contact record")
    void whenSaveMethodCalledThenMustCreateNewContactRecord() {
        Contact contactCreated = Contact.builder()
                            .id(1L)
                            .name("Bruno")
                            .email("bruno@teste.com")
                            .favorite(true)
                            .build();
        when(contactRepository.save(contact)).thenReturn(contactCreated);

        ContactDto contactDto = contactService.save(contact);

        assertThat(contactDto.getId(), is(equalTo(1L)));
        assertThat(contactDto.getName(), is(equalTo(contact.getName())));
        assertThat(contactDto.getEmail(), is(equalTo(contact.getEmail())));
        assertThat(contactDto.getFavorite(), is(equalTo(contact.getFavorite())));
    }

    @Test
    @DisplayName("(2) It must return a page of contacts")
    void whenFindAllMethodIsCalledThenReturnAListOfContacts() {

        when(contactRepository.findAll(pageRequest)).thenReturn(contacts);

        contactsDto = contactService.findAll(pageRequest);

        assertThat(contactsDto.getContent(), is(not(empty)));
    }

    @Test
    @DisplayName("(3) The contacts returned must be ok")
    void whenFindAllMethodIsCalledThenTheContactsMustBeOk() {

        when(contactRepository.findAll(pageRequest)).thenReturn(contacts);

        contactsDto = contactService.findAll(pageRequest);

        assertThat(contactsDto.getContent().get(0).getId(), is(equalTo(contact.getId())));
        assertThat(contactsDto.getContent().get(0).getName(), is(equalTo(contact.getName())));
        assertThat(contactsDto.getContent().get(0).getEmail(), is(equalTo(contact.getEmail())));
        assertThat(contactsDto.getContent().get(0).getFavorite(), is(equalTo(contact.getFavorite())));
    }
}
