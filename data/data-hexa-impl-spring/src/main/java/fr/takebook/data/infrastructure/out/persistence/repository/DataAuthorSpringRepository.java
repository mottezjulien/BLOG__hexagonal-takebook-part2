package fr.takebook.data.infrastructure.out.persistence.repository;

import fr.takebook.data.infrastructure.out.persistence.entity.DataBookAuthorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DataAuthorSpringRepository extends CrudRepository<DataBookAuthorEntity, UUID> {


}
