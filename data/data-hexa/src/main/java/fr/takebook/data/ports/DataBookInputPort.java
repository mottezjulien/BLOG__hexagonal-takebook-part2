package fr.takebook.data.ports;

import fr.takebook.data.model.DataBook;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class DataBookInputPort {

    private DataBookOutputPort outputPort;

    public DataBookInputPort(DataBookOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public Stream<DataBook> findByTitle(String pattern) {
        return findAll().filter(book -> isMatchWithTitle(book, pattern));
    }

    private boolean isMatchWithTitle(DataBook book, String pattern) {
        return book.getTitle()
                .toUpperCase()
                .contains(pattern.toUpperCase());
    }

    public Stream<DataBook> findAll() {
        return outputPort.findAll();
    }

    public Optional<DataBook> findById(UUID id) {
        return outputPort.findById(id);
    }

}
