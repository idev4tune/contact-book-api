package io.github.idev4tune.contact_book_api;

import io.github.idev4tune.contact_book_api.entities.Contact;
import io.github.idev4tune.contact_book_api.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactBookApiApplication {

	@Bean
	public CommandLineRunner saveContact(@Autowired ContactRepository repository) {
		return args -> {
			Contact contact = new Contact();
			contact.setName("Bruno");
			contact.setEmail("bruno@commandlinerunner.com");
			contact.setFavorite(true);
			repository.save(contact);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ContactBookApiApplication.class, args);
	}

}
