package fr.takebook.data.infrastructure.out.persistence.repository;

import fr.takebook.data.infrastructure.out.persistence.entity.DataBookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DataBookSpringRepository extends CrudRepository<DataBookEntity, UUID> {


}
