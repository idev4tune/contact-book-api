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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

    private Contact contact = Contact.builder()
                                    .name("Bruno")
                                    .email("bruno@teste.com")
                                    .favorite(true)
                                    .build();

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
}
