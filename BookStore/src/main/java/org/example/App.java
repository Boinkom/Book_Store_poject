package org.example;

import org.example.models.BookModels;
import org.example.storage.BookStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        BookStorage.getBooks().add(new BookModels(UUID.randomUUID().toString(), "Book Title", "Author Name", 100, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTINy5-amxjxKf6QHby1HiDxgxePhCQ8FacfQ&s", List.of("Genre1", "Genre2"), 4.5f));
        BookStorage.getBooks().add(new BookModels("2", "Book Title 2", "Author Name 2", 200, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2J0F9wlWdpXVrrsfFkpYODLL863-EKIjfLA&s", List.of("Genre3", "Genre4"), 3.8f));
        BookStorage.getBooks().add(new BookModels("3", "Book Title 3", "Author Name", 100, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtitWPXvzCNClQSCL3ndM22_3V62W-ymWVcQ&s", List.of("Genre1", "Genre2"), 4.5f));
        BookStorage.getBooks().add(new BookModels("4", "Book Title 4", "Author Name 2", 200, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUHJzGlH303jpTR-8kf-uUbcFkieEahvhpp2he35VC3y4mXqb-qkF0MOwU4mJOLFUl6q4&usqp=CAU", List.of("Genre3", "Genre4"), 3.8f));
        BookStorage.getBooks().add(new BookModels("5", "Book Title 4", "Author Name 2", 200, "https://ficwriter.info/images/Pictures_for_Articles/Main/tn.jpg", List.of("Genre3", "Genre4"), 3.8f));
        BookStorage.getBooks().add(new BookModels("5", "Book Title 4", "Author Name 2", 200, "https://ficwriter.info/images/Pictures_for_Articles/Main/tn.jpg", List.of("Genre3", "Genre4"), 3.8f));
        BookStorage.getBooks().add(new BookModels("5", "Book Title 4", "Author Name 2", 200, "https://ficwriter.info/images/Pictures_for_Articles/Main/tn.jpg", List.of("Genre3", "Genre4"), 3.8f));
        BookStorage.getBooks().add(new BookModels("5", "Book Title 4", "Author Name 2", 200, "https://ficwriter.info/images/Pictures_for_Articles/Main/tn.jpg", List.of("Genre3", "Genre4"), 3.8f));


        SpringApplication.run(App.class, args);
    }
}
