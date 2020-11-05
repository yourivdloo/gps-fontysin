package fontysin.project;

import fontysin.project.model.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class EntityTests {

    @Test
    void appUserCtor(){
        //Arrange
        int pcn = 123456;
        String firstName = "Alex";
        String lastName = "Jones";

        //Act
        AppUser appUser = new AppUser(pcn, firstName, lastName);

        //Assert
        assertEquals(pcn, appUser.getPcn());
    }

    @Test
    void userHobbyCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "Programming";
        //Act
        UserHobby hobby = new UserHobby(user, name);
        //Assert
        assertEquals(name, hobby.getName());
    }

    @Test
    void userInterestCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "Programming";
        //Act
        UserInterest interest = new UserInterest(user, name);
        //Assert
        assertEquals(name, interest.getName());
    }

    @Test
    void userJobCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "McDonald's employee";
        String city = "Eindhoven";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(50);
        //Act
        UserJob job = new UserJob(user, name, city, startDate, endDate);
        //Assert
        assertEquals(name, job.getName());
        assertEquals(city, job.getCity());
    }

    @Test
    void userLanguageCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "Dutch";
        //Act
        UserLanguage language = new UserLanguage(user, name);
        //Assert
        assertEquals(name, language.getName());
    }

    @Test
    void userLicenseCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "Fontys HBO-ICT Software engineer";
        //Act
        UserLicense license = new UserLicense(user, name);
        //Assert
        assertEquals(name, license.getName());
    }

    @Test
    void userParticipationCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "Fontys open day";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(2);
        //Act
        UserParticipation participation = new UserParticipation(user, name, startDate, endDate);
        //Assert
        assertEquals(name, participation.getName());
    }

    @Test
    void userPersonalityTraitCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "Social";
        //Act
        UserPersonalityTrait trait = new UserPersonalityTrait(user, name);
        //Assert
        assertEquals(name, trait.getName());
    }

    @Test
    void userReferenceCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "student";
        String phoneNumber = "0612345678";
        String email = "Alex.Jones@gmail.com";
        //Act
        UserReference reference = new UserReference(user, name, phoneNumber, email);
        //Assert
        assertEquals(name, reference.getName());
    }

    @Test
    void userSkillCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "quick thinker";
        //Act
        UserSkill skill = new UserSkill(user, name);
        //Assert
        assertEquals(name, skill.getName());
    }

    @Test
    void userStudyCtor(){
        //Arrange
        AppUser user = new AppUser(123456, "Alex", "Jones");
        String name = "quick thinker";
        String school = "Fontys";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusYears(5);
        //Act
        UserStudy study = new UserStudy(user, name, school, startDate, endDate, false);
        //Assert
        assertEquals(name, study.getName());
        assertEquals(school, study.getSchool());
        assertFalse(study.isFinished());
    }
}
