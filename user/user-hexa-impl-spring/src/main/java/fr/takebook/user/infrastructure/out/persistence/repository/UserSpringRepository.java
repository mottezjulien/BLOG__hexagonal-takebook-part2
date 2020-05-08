package fr.takebook.user.infrastructure.out.persistence.repository;

import fr.takebook.user.infrastructure.out.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserSpringRepository extends CrudRepository<UserEntity, UUID> {


}