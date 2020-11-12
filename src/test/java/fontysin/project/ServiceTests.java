package fontysin.project;

import fontysin.project.controllers.DemoController;
import fontysin.project.controllers.UserController;
import fontysin.project.controllers.Util;
import fontysin.project.model.user.AppUser;
import fontysin.project.repositories.UserRepository;
import fontysin.project.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void getUserByPcnTest(){
        //Arrange
        int pcn = 123456;
        AppUser user = new AppUser(pcn, "Alex", "Jones");

        when(userRepository.findByPcn(pcn)).thenReturn(Optional.of(user));

        //Act
        AppUser actual = userService.getUserByPcn(pcn);

        //Assert
        assertEquals(user, actual);
    }

    @Test
    void getAllUsers(){
        //Arrange
        AppUser user1 = new AppUser(123456,"Alex", "Jones");
        AppUser user2 = new AppUser(654321, "Steve", "Diamond");
        AppUser user3 = new AppUser(112233, "Kendall", "Schmidt");
        AppUser[] userList = {user1, user2, user3};

        Iterable<AppUser> iterable = Arrays.asList(userList);

        when(userRepository.findAll()).thenReturn(iterable);

        //Act
        Iterable<AppUser> actual = userService.getAllUsers();

        //Assert
        assertEquals(iterable, actual);
    }

    @Test
    void createUserTest(){
        //Arrange
        AppUser user = new AppUser(332211, "John", "Johnson");

        when(userRepository.save(user)).thenReturn(user);

        //Act
        AppUser actual = userService.createUser(user);

        //Assert
        assertEquals(user, actual);
    }

    @Test
    void deleteUserTest(){
        //Arrange
        AppUser user = new AppUser(321012, "Ronald", "McDonald");

        when(userRepository.findByPcn(321012)).thenReturn(Optional.of(user));

        //Act
        boolean success = userService.deleteUser(321012);

        //Assert
        assertEquals(true, success);
    }

}
