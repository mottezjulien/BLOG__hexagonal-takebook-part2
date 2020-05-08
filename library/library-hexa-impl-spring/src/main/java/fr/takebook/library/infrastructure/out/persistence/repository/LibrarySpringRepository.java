package fr.takebook.library.infrastructure.out.persistence.repository;

import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LibrarySpringRepository extends CrudRepository<LibraryEntity, UUID> {


}