package fr.takebook.user.infrastructure.out.persistence.wrapper;

import fr.takebook.library.domain.model.User;
import fr.takebook.user.infrastructure.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityWrapper {

    public User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        return model;
    }

}
