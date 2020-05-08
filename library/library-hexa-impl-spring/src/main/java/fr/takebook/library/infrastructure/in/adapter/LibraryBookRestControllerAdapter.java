package fr.takebook.library.infrastructure.in.adapter;

import fr.takebook.library.domain.ports.in.LibraryBookInputPort;
import fr.takebook.library.domain.ports.in.LibraryBookInputException;
import fr.takebook.library.infrastructure.in.transport.object.BorrowRequestDTO;
import fr.takebook.library.infrastructure.in.transport.object.LibraryBookDTO;
import fr.takebook.library.infrastructure.in.transport.object.PutAwayRequestDTO;
import fr.takebook.library.infrastructure.in.transport.object.ReturnRequestDTO;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryBookDTOWrapper;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryBorrowerDTOWrapper;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryDTOWrapper;
import fr.takebook.library.infrastructure.in.transport.wrapper.LibraryShelfDTOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/takebook/rest/library/book")
public class LibraryBookRestControllerAdapter {

    @Autowired
    private LibraryBookInputPort inputPort;

    @Autowired
    private LibraryBookDTOWrapper libraryBookDTOWrapper;

    @Autowired
    private LibraryShelfDTOWrapper libraryShelfDTOWrapper;

    @Autowired
    private LibraryDTOWrapper libraryDTOWrapper;

    @Autowired
    private LibraryBorrowerDTOWrapper libraryBorrowerDTOWrapper;

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            path = "/")
    @ResponseBody
    public Stream<LibraryBookDTO> findAll() {
        return inputPort.findAll()
                .map(model -> libraryBookDTOWrapper.fromModel(model));
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            path = "/putAway/")
    @ResponseBody
    public LibraryBookDTO putAway(@RequestBody PutAwayRequestDTO request) throws LibraryBookInputException {
        return libraryBookDTOWrapper.fromModel(inputPort.putAwayBook(libraryBookDTOWrapper.toModel(request.getBook()), libraryShelfDTOWrapper.toModel(request.getShelf())));
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            path = "/return/")
    @ResponseBody
    public LibraryBookDTO doReturn(@RequestBody ReturnRequestDTO request) throws LibraryBookInputException {
        return libraryBookDTOWrapper.fromModel(inputPort.returnBook(libraryBookDTOWrapper.toModel(request.getBook()), libraryDTOWrapper.toModel(request.getLibrary())));
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            path = "/borrow/")
    @ResponseBody
    public LibraryBookDTO borrow(@RequestBody BorrowRequestDTO request) throws LibraryBookInputException {
        return libraryBookDTOWrapper.fromModel(inputPort.borrowBook(libraryBookDTOWrapper.toModel(request.getBook()), libraryBorrowerDTOWrapper.toModel(request.getBorrower())));
    }

}