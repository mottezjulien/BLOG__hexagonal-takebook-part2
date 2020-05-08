package fr.takebook.library.infrastructure.out.persistence.repository;

import fr.takebook.library.infrastructure.out.persistence.entity.LibraryBookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LibraryBookSpringRepository extends CrudRepository<LibraryBookEntity, UUID> {


    List<LibraryBookEntity> findByBorrowerId(UUID borrowerId);
}