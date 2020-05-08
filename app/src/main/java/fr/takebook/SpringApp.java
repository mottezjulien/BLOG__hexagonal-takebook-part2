package fr.takebook;

import fr.takebook.data.infrastructure.out.adapter.DataBookOutputAdapter;
import fr.takebook.data.ports.DataBookInputPort;
import fr.takebook.library.domain.ports.in.LibraryBookInputPort;
import fr.takebook.library.domain.ports.in.UserInputPort;
import fr.takebook.library.infrastructure.out.adapter.LibraryBookOutputAdapter;
import fr.takebook.user.infrastructure.out.adapter.UserOutputAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }

    @Bean
    public UserInputPort userInputPort(UserOutputAdapter userOutputAdapter) {
        return new UserInputPort(userOutputAdapter);
    }

    @Bean
    public DataBookInputPort dataBookInputPort(DataBookOutputAdapter dataBookOutputAdapter) {
        return new DataBookInputPort(dataBookOutputAdapter);
    }

    @Bean
    public LibraryBookInputPort libraryBookInputPort(LibraryBookOutputAdapter libraryBookOutputAdapter) {
        return new LibraryBookInputPort(libraryBookOutputAdapter);
    }

}
