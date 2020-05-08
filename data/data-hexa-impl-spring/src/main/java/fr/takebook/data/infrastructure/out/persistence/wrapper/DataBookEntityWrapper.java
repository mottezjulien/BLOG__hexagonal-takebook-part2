package fr.takebook.data.infrastructure.out.persistence.wrapper;

import fr.takebook.data.model.DataBook;
import fr.takebook.data.infrastructure.out.persistence.entity.DataBookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBookEntityWrapper {

    @Autowired
    private DataBookAuthorEntityWrapper authorAssembler;

    public DataBook toModel(DataBookEntity entity) {
        DataBook dto = new DataBook();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(authorAssembler.toModel(entity.getAuthor()));
        return dto;
    }


}
