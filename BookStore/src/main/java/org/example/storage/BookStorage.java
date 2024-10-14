package org.example.storage;

import org.example.models.BookModels;

import java.util.ArrayList;
import java.util.List;

public class BookStorage {
    private static List<BookModels> books = new ArrayList<>();
    public static List<BookModels> getBooks(){
        return books;
    }
}
