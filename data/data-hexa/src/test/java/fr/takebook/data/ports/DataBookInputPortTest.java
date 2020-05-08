package fr.takebook.data.ports;

import fr.takebook.data.model.DataBook;
import fr.takebook.data.ports.DataBookOutputPort;
import fr.takebook.data.ports.DataBookInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DataBookInputPortTest {

    @Mock
    private DataBookOutputPort outputPort;

    @InjectMocks
    private DataBookInputPort service;

    private List<DataBook> books = List
            .of(book("title of book"),
                    book("my name is"),
                    book("third title"));

    @BeforeEach
    void setUp() {
        when(outputPort.findAll()).thenReturn(books.stream());
    }

    @Test
    public void findNoneIfWrongPattern() {
        Stream<DataBook> find = service.findByTitle("any");
        assertEquals(find.count(), 0);
    }

    @Test
    public void findOneIfValidOnePattern() {
        List<DataBook> find = service.findByTitle("my name").collect(Collectors.toList());
        assertAll(
                () -> assertThat(find, hasSize(1)),
                () -> assertThat(find, contains(books.get(1))));
    }

    @Test
    public void findTwoIfValidTwoPattern() {
        List<DataBook> find = service.findByTitle("title").collect(Collectors.toList());
        assertAll(
                () -> assertThat(find, hasSize(2)),
                () -> assertThat(find, contains(books.get(0), books.get(2))));
    }

    private DataBook book(String title) {
        DataBook book = new DataBook();
        book.setTitle(title);
        return book;
    }

}