package fr.takebook.user.infrastructure.out.adapter;

import fr.takebook.library.domain.model.User;
import fr.takebook.library.domain.ports.out.UserOutputPort;
import fr.takebook.user.infrastructure.out.persistence.entity.UserEntity;
import fr.takebook.user.infrastructure.out.persistence.repository.UserSpringRepository;
import fr.takebook.user.infrastructure.out.persistence.wrapper.UserEntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class UserOutputAdapter implements UserOutputPort {

    @Autowired
    private UserSpringRepository repository;

    @Autowired
    private UserEntityWrapper assembler;

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> assembler.toModel(entity));
    }

    @Override
    public Stream<User> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> assembler.toModel(entity));
    }

}
