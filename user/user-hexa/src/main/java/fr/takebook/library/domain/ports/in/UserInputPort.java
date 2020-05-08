package fr.takebook.library.domain.ports.in;

import fr.takebook.library.domain.model.User;
import fr.takebook.library.domain.ports.out.UserOutputPort;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class UserInputPort {

    private UserOutputPort outputPort;

    public UserInputPort(UserOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public Optional<User> findById(UUID id) {
        return outputPort.findById(id);
    }

    public Stream<User> findAll() {
        return outputPort.findAll();
    }
}
