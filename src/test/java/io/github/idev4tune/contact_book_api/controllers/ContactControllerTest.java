package io.github.idev4tune.contact_book_api.controllers;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;
import io.github.idev4tune.contact_book_api.services.implementation.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static io.github.idev4tune.contact_book_api.utils.JsonConverterUtil.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {

    private static final String URN = "/api/v1/contacts";

    private final Contact contact = Contact.builder()
            .id(1L)
            .name("Bruno")
            .email("bruno@idev4tune.com")
            .favorite(true)
            .build();

    private final ContactDto contactDto = ContactDto.fromContact(contact);

    private Page<ContactDto> contacts;

    private final PageRequest pageRequest = PageRequest.of(0, 20);

    private MockMvc mockMvc;

    @Mock
    private ContactServiceImpl contactService;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    @DisplayName("(1) Must return 201 Created status")
    void whenPostMethodIsCalledThenCreateStatusCodeIsReturned() throws Exception {

        when(contactService.save(contact)).thenReturn(contactDto);

        mockMvc.perform(post(URN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contact)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("(2) Must return a new ContactDto object")
    void whenPostMethodIsCalledThenReturnTheNewContact() throws Exception {

        int id = 1;
        String name = "Bruno";
        String email = "bruno@idev4tune.com";
        boolean favorite = true;

        when(contactService.save(contact)).thenReturn(contactDto);

        mockMvc.perform(post(URN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contact)))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.favorite", is(favorite)));
    }

    @Test
    @DisplayName("(3) Must return 200 Ok status when findAll() is called")
    void whenFindAllMethodIsCalledThenReturnAPageOfContacts() throws Exception {

        when(contactService.findAll(pageRequest)).thenReturn(contacts);

        mockMvc.perform(MockMvcRequestBuilders.get(URN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
