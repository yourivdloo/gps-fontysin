package fontysin.project;

import fontysin.project.Controllers.DemoController;
import fontysin.project.Controllers.UserController;
import fontysin.project.Controllers.Util;
import fontysin.project.Models.user.AppUser;
import fontysin.project.Repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ControllerTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserController userController = new UserController();

//    @Mock
//    Util util;
//
//    @InjectMocks
//    DemoController demoController;
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }


//    @Test
//    void getPcnTest() {
//        //Arrange
//        when(util.GetPcn()).thenReturn(123456);
//
//        //Act
//        int pcn = util.GetPcn();
//        //ResponseEntity<String> actual = demoController.getSSO();
//
//        //Assert
//        int expected = 123456;
//        //ResponseEntity<String> expected = new ResponseEntity<>("Your PCN is: 123456", HttpStatus.OK);
//        assertEquals(expected, pcn);
//    }

    @Test
    void getUserByPcnTest(){
        //Arrange
        String pcn = "123456";
        AppUser user = new AppUser(pcn, "Alex", "Jones");

        when(userRepository.findByPcn(pcn)).thenReturn(Optional.of(user));

        //Act
        ResponseEntity<AppUser> actual = userController.getUserByPcn(pcn);

        //Assert
        ResponseEntity<AppUser> expected = new ResponseEntity<AppUser>(user, HttpStatus.FOUND);
        assertEquals(expected, actual);
    }
//
//    @Test
//    void getAllUsers(){
//        //Arrange
//        ArrayList<>
//        when(userRepository.findAll()).thenReturn(Collections.emptyList());
//
//        //Act
//        ResponseEntity<Iterable<AppUser>> actual = userController.getAllUsers();
//
//        //Assert
//        ResponseEntity<Iterable<AppUser>> expected =
//    }

}
