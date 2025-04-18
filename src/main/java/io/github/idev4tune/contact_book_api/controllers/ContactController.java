package io.github.idev4tune.contact_book_api.controllers;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.entities.dto.ContactDto;
import io.github.idev4tune.contact_book_api.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;

    @PostMapping
    public ResponseEntity<ContactDto> save(@RequestBody Contact contact) {
        ContactDto contactDto = service.save(contact);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contactDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(contactDto);
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> findAll(Pageable pageable) {
        Page<ContactDto> contacts = service.findAll(pageable);
        return ResponseEntity.ok(contacts);
    }
}
