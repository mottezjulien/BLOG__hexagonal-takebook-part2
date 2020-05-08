package fr.takebook.data.ports;

import fr.takebook.data.model.DataBook;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface DataBookOutputPort {

    Stream<DataBook> findAll();

    Optional<DataBook> findById(UUID id);
}
