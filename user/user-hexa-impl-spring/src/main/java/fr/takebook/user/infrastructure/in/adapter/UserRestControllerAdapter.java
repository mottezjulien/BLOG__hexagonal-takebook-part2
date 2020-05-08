package fr.takebook.user.infrastructure.in.adapter;


import fr.takebook.library.domain.ports.in.UserInputPort;
import fr.takebook.user.infrastructure.in.transport.object.UserDTO;
import fr.takebook.user.infrastructure.in.transport.wrapper.UserDTOWrapper;
import fr.takebook.user.infrastructure.out.persistence.repository.UserSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/takebook/rest/user")
public class UserRestControllerAdapter {

    @Autowired
    private UserInputPort inputPort;

    @Autowired
    private UserDTOWrapper userDTOWrapper;

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/")
    @ResponseBody
    public Stream<UserDTO> findAll() {
        return inputPort.findAll()
                .map(model -> userDTOWrapper.fromModel(model));
    }

}
