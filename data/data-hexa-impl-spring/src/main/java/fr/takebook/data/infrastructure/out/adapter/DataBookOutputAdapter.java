package fr.takebook.data.infrastructure.out.adapter;

import fr.takebook.data.infrastructure.out.persistence.wrapper.DataBookEntityWrapper;
import fr.takebook.data.model.DataBook;
import fr.takebook.data.ports.DataBookOutputPort;
import fr.takebook.data.infrastructure.out.persistence.repository.DataBookSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class DataBookOutputAdapter implements DataBookOutputPort {

    @Autowired
    private DataBookSpringRepository repository;

    @Autowired
    private DataBookEntityWrapper assembler;

    @Override
    public Stream<DataBook> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> assembler.toModel(entity));
    }

    @Override
    public Optional<DataBook> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> assembler.toModel(entity));
    }

}
