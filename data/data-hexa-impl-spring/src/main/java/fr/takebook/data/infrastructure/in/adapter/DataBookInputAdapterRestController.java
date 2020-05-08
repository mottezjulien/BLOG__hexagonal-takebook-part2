package fr.takebook.data.infrastructure.in.adapter;

import fr.takebook.data.infrastructure.in.transport.object.DataBookSearchRequestDTO;
import fr.takebook.data.infrastructure.in.transport.object.DataBookDTO;
import fr.takebook.data.infrastructure.in.transport.wrapper.DataBookDTOWrapper;
import fr.takebook.data.ports.DataBookInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value = "/takebook/rest/data/book")
public class DataBookInputAdapterRestController {

    @Autowired
    private DataBookInputPort input;

    @Autowired
    private DataBookDTOWrapper wrapper;

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/")
    @ResponseBody
    public Stream<DataBookDTO> findAll() {
        return input.findAll()
                .map(entity -> wrapper.fromModel(entity));
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            path = "/search/")
    @ResponseBody
    public Stream<DataBookDTO> search(@RequestBody DataBookSearchRequestDTO request) {
        return input.findByTitle(request.getValue())
                .map(model -> wrapper.fromModel(model));
    }



}
