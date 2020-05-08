package fr.takebook.library.infrastructure.out.persistence.repository;

import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryShelfEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LibraryShelfSpringRepository extends CrudRepository<LibraryShelfEntity, UUID> {

    List<LibraryShelfEntity> findByLibrary(LibraryEntity library);

}