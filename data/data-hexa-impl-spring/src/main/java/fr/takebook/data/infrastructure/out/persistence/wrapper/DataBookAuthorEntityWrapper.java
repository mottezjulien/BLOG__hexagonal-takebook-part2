package fr.takebook.data.infrastructure.out.persistence.wrapper;

import fr.takebook.data.model.DataAuthor;
import fr.takebook.data.infrastructure.out.persistence.entity.DataBookAuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class DataBookAuthorEntityWrapper {

    public DataAuthor toModel(DataBookAuthorEntity entity) {
        DataAuthor dto = new DataAuthor();
        dto.setUuid(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }

}
