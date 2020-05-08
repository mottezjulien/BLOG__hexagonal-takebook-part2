package fr.takebook.data.infrastructure.in.transport.wrapper;

import fr.takebook.data.infrastructure.in.transport.object.DataBookDTO;
import fr.takebook.data.model.DataBook;
import org.springframework.stereotype.Component;

@Component
public class DataBookDTOWrapper {

    public DataBookDTO fromModel(DataBook model) {
        DataBookDTO dto = new DataBookDTO();
        dto.setId(model.getId().toString());
        dto.setTitle(model.getTitle());
        dto.setAuthorName(model.getAuthor().getFirstName() + " " + model.getAuthor().getLastName());
        return dto;
    }

}
