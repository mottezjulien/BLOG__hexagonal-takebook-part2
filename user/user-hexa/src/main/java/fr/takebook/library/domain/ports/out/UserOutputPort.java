package fr.takebook.library.domain.ports.out;

import fr.takebook.library.domain.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface UserOutputPort {

    Optional<User> findById(UUID id);

    Stream<User> findAll();
}
