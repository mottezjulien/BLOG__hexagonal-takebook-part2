package fr.takebook.library.infrastructure.out.adapter;

import fr.takebook.data.ports.DataBookInputPort;
import fr.takebook.library.domain.model.book.LibraryBook;
import fr.takebook.library.domain.model.book.LibraryBookState;
import fr.takebook.library.domain.model.book.LibraryBorrowedBook;
import fr.takebook.library.domain.model.book.LibraryBorrower;
import fr.takebook.library.domain.ports.in.UserInputPort;
import fr.takebook.library.domain.ports.out.LibraryBookOutputPort;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryBookEntity;
import fr.takebook.library.infrastructure.out.persistence.repository.LibraryBookSpringRepository;
import fr.takebook.library.infrastructure.out.wrapper.LibraryBookDataWrapper;
import fr.takebook.library.infrastructure.out.wrapper.LibraryBookWrapper;
import fr.takebook.library.infrastructure.out.wrapper.LibraryBorrowerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@Component
public class LibraryBookOutputAdapter implements LibraryBookOutputPort {

    @Autowired
    private LibraryBookSpringRepository repository;

    @Autowired
    private DataBookInputPort dataBookInputPort;

    @Autowired
    private UserInputPort userInputPort;

    @Autowired
    private LibraryBookWrapper libraryBookWrapper;

    @Autowired
    private LibraryBookDataWrapper libraryBookDataWrapper;

    @Autowired
    private LibraryBorrowerWrapper libraryBorrowerWrapper;

    @Override
    public Optional<LibraryBook> findById(UUID id) {
        return repository.findById(id)
                .map(entity -> fromEntityWithDataAndUser(entity));
    }


    @Override
    public Stream<LibraryBook> findByBorrower(LibraryBorrower borrower) {
        return StreamSupport
                .stream(repository.findByBorrowerId(borrower.getId()).spliterator(), false)
                .map(entity -> fromEntityWithDataAndUser(entity));
    }

    @Override
    public Stream<LibraryBook> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(entity -> fromEntityWithDataAndUser(entity));
    }

    private LibraryBook fromEntityWithDataAndUser(LibraryBookEntity entity) {
        LibraryBook model = libraryBookWrapper.fromEntity(entity);
        dataBookInputPort.findById(entity.getDataId())
                .ifPresent(dataBook -> model.setData(libraryBookDataWrapper.fromDataModel(dataBook)));
        if(model.getState() == LibraryBookState.BORROWED){
            userInputPort.findById(entity.getBorrowerId())
                    .ifPresent(borrower -> ((LibraryBorrowedBook)model).setBorrower(libraryBorrowerWrapper.fromUserModel(borrower)));

        }
        return model;
    }

    @Override
    public void update(LibraryBook book) {
        repository.save(libraryBookWrapper.toEntity(book));
    }


}
