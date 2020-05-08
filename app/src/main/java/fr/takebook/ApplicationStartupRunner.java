package fr.takebook;

import fr.takebook.data.infrastructure.out.persistence.entity.DataBookAuthorEntity;
import fr.takebook.data.infrastructure.out.persistence.entity.DataBookEntity;
import fr.takebook.data.infrastructure.out.persistence.repository.DataAuthorSpringRepository;
import fr.takebook.data.infrastructure.out.persistence.repository.DataBookSpringRepository;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryBookEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryBookStateEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryEntity;
import fr.takebook.library.infrastructure.out.persistence.entity.LibraryShelfEntity;
import fr.takebook.library.infrastructure.out.persistence.repository.LibraryBookSpringRepository;
import fr.takebook.library.infrastructure.out.persistence.repository.LibraryShelfSpringRepository;
import fr.takebook.library.infrastructure.out.persistence.repository.LibrarySpringRepository;
import fr.takebook.user.infrastructure.out.persistence.entity.UserEntity;
import fr.takebook.user.infrastructure.out.persistence.repository.UserSpringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private DataAuthorSpringRepository dataAuthorRepository;

    @Autowired
    private DataBookSpringRepository dataBookRepository;

    @Autowired
    private LibrarySpringRepository libraryRepository;

    @Autowired
    private LibraryShelfSpringRepository libraryShelfRepository;

    @Autowired
    private LibraryBookSpringRepository libraryBookRepository;

    @Autowired
    private UserSpringRepository userRepository;

    @Override
    public void run(String... args) {

        UserEntity user1 = new UserEntity();
        user1.setFirstName("Plop1");
        user1.setLastName("Plop2");
        userRepository.save(user1);

        UserEntity user2 = new UserEntity();
        user2.setFirstName("Youhou1");
        user2.setLastName("Youhou2");
        userRepository.save(user2);

        DataBookAuthorEntity authorA = new DataBookAuthorEntity();
        authorA.setFirstName("Bob");
        authorA.setLastName("Martin");
        dataAuthorRepository.save(authorA);

        DataBookAuthorEntity authorB = new DataBookAuthorEntity();
        authorB.setFirstName("Jeanne");
        authorB.setLastName("Roland");
        dataAuthorRepository.save(authorB);

        DataBookAuthorEntity authorC = new DataBookAuthorEntity();
        authorC.setFirstName("Marco");
        authorC.setLastName("Livé");
        dataAuthorRepository.save(authorC);

        DataBookEntity book1 = new DataBookEntity();
        book1.setTitle("Le trone en pierre");
        book1.setAuthor(authorA);
        dataBookRepository.save(book1);

        DataBookEntity book2 = new DataBookEntity();
        book2.setTitle("Henry Pierre aux collège enchanté");
        book2.setAuthor(authorB);
        dataBookRepository.save(book2);

        DataBookEntity book3 = new DataBookEntity();
        book3.setTitle("Et si c'était faux !!");
        book3.setAuthor(authorC);
        dataBookRepository.save(book3);

        DataBookEntity book4 = new DataBookEntity();
        book4.setTitle("Elle et lui et l'autre");
        book4.setAuthor(authorC);
        dataBookRepository.save(book4);

        DataBookEntity book5 = new DataBookEntity();
        book5.setTitle("La dernière journée");
        book5.setAuthor(authorC);
        dataBookRepository.save(book5);

        LibraryEntity library1 = new LibraryEntity();
        library1.setName("Library A");
        libraryRepository.save(library1);

        LibraryEntity library2 = new LibraryEntity();
        library2.setName("Library B");
        libraryRepository.save(library2);

        LibraryShelfEntity shelf10 = new LibraryShelfEntity();
        shelf10.setLabel("LibA-001");
        shelf10.setLibrary(library1);
        libraryShelfRepository.save(shelf10);

        LibraryShelfEntity shelf11 = new LibraryShelfEntity();
        shelf11.setLabel("LibA-002");
        shelf11.setLibrary(library1);
        libraryShelfRepository.save(shelf11);

        LibraryShelfEntity shelf20 = new LibraryShelfEntity();
        shelf20.setLabel("S-A2036");
        shelf20.setLibrary(library2);
        libraryShelfRepository.save(shelf20);

        LibraryShelfEntity shelf21 = new LibraryShelfEntity();
        shelf21.setLabel("S-C420");
        shelf21.setLibrary(library2);
        libraryShelfRepository.save(shelf21);

        LibraryShelfEntity shelf22 = new LibraryShelfEntity();
        shelf22.setLabel("S-F78");
        shelf22.setLibrary(library2);
        libraryShelfRepository.save(shelf22);

        LibraryBookEntity libraryBook1 = new LibraryBookEntity();
        libraryBook1.setDataId(book1.getId());
        libraryBook1.setState(LibraryBookStateEntity.RETURNED);
        libraryBook1.setLibrary(library1);
        libraryBookRepository.save(libraryBook1);

        LibraryBookEntity libraryBook2 = new LibraryBookEntity();
        libraryBook2.setDataId(book5.getId());
        libraryBook2.setState(LibraryBookStateEntity.RETURNED);
        libraryBook2.setLibrary(library2);
        libraryBookRepository.save(libraryBook2);

        LibraryBookEntity libraryBook3 = new LibraryBookEntity();
        libraryBook3.setDataId(book5.getId());
        libraryBook3.setState(LibraryBookStateEntity.BORROWED);
        libraryBook3.setBorrowerId(user2.getId());
        libraryBookRepository.save(libraryBook3);

        LibraryBookEntity libraryBook4 = new LibraryBookEntity();
        libraryBook4.setDataId(book3.getId());
        libraryBook4.setState(LibraryBookStateEntity.STORED);
        libraryBook4.setShelf(shelf11);
        libraryBookRepository.save(libraryBook4);

        LibraryBookEntity libraryBook5 = new LibraryBookEntity();
        libraryBook5.setDataId(book4.getId());
        libraryBook5.setState(LibraryBookStateEntity.STORED);
        libraryBook5.setShelf(shelf22);
        libraryBookRepository.save(libraryBook5);

        LibraryBookEntity libraryBook6 = new LibraryBookEntity();
        libraryBook6.setDataId(book1.getId());
        libraryBook6.setState(LibraryBookStateEntity.STORED);
        libraryBook6.setShelf(shelf21);
        libraryBookRepository.save(libraryBook6);

    }

}