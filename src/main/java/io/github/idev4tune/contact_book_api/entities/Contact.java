package io.github.idev4tune.contact_book_api.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private Boolean favorite;

}
