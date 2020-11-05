package fontysin.project;

import fontysin.project.Models.user.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EntityTests {

    @Test
    void appUserConstructor(){
        //Arrange
        String pcn = "123456";
        String firstName = "Alex";
        String lastName = "Steve";

        //Act
        AppUser appUser = new AppUser(pcn, firstName, lastName);

        //Assert
        assertEquals(pcn, appUser.getPcn());
    }
}
