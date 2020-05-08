package fr.takebook.user.infrastructure.in.transport.wrapper;

import fr.takebook.library.domain.model.User;
import fr.takebook.user.infrastructure.in.transport.object.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOWrapper {

    public UserDTO fromModel(User model) {
        UserDTO dto = new UserDTO();
        dto.setId(model.getId().toString());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        return dto;
    }
}
