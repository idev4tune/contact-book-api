package io.github.idev4tune.contact_book_api.entities.dto;

import io.github.idev4tune.contact_book_api.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {

    private Long id;
    private String name;
    private String email;
    private Boolean favorite;

    public static ContactDto fromContact(Contact contact) {
        return new ContactDto(
            contact.getId(),
            contact.getName(),
            contact.getEmail(),
            contact.getFavorite()
        );
    }
}
