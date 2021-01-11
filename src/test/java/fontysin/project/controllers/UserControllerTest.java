package fontysin.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontysin.project.entities.dto.UserDTO;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;
import fontysin.project.entities.model.user.properties.UserHobby;
import fontysin.project.entities.model.user.properties.UserInterest;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.services.PropertyService;
import fontysin.project.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private PropertyService mockPropertyService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGetAllUsers() throws Exception {
        // Setup

        // Configure UserService.getAllUsers(...).
        final Iterable<AppUser> appUsers = List.of(new AppUser(0, "firstName", "", "lastName"));
        when(mockUserService.getAllUsers()).thenReturn(appUsers);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/user/all")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(appUsers), response.getContentAsString());
    }

    @Test
    void testGetAllUsers_NoUsersRegistered() throws Exception {
        // Setup

        // Configure UserService.getAllUsers(...).
        final Iterable<AppUser> appUsers = Collections.emptyList();
        when(mockUserService.getAllUsers()).thenReturn(appUsers);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/user/all")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testGetUserByPcn() throws Exception {
        // Setup
        final AppUser appUser = new AppUser(422773, "firstName", "", "lastName");
        final UserDTO expected = new UserDTO(appUser, Collections.emptyList());

        // Configure UserService.getUserByPcn(...).
        when(mockUserService.getUserByPcn(422773)).thenReturn(appUser);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/user/422773", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(expected), response.getContentAsString());
    }

    @Test
    void testGetUserByPcn_InvalidPcn() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(422773)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/user/400000", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testCreateUser() throws Exception {
        // Setup

        //Configure UserService.createUser(...).
        final AppUser appUser = new AppUser(422773, "firstName", "", "lastName");
        when(mockUserService.createUser(any(AppUser.class))).thenReturn(appUser);

        //Configure PropertyService.getUserProperties(...).
        final List<UserProperty> userProperties = new ArrayList<>();
        userProperties.add(new UserHobby(appUser, "Lego"));
        when(mockPropertyService.getUserProperties(any(int.class))).thenReturn(userProperties);

        //Expected Result
        final UserDTO expected = new UserDTO(appUser, userProperties);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/user/new")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\",\"UserPropertiesDTO\":[{\"hobbies\":[{\"name\":\"Lego\"}]}]}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(expected), response.getContentAsString());
    }

    @Test
    void testCreateUser_MissingArgument() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/user/new")
                .content("{\"firstName\":\"\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void testCreateUser_ServerError() throws Exception {
        // Setup
        when(mockUserService.createUser(any(AppUser.class))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/user/new")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    void testCreateUser_PrincipalHeaderMissing() throws Exception {
        // Setup
        when(mockUserService.createUser(any(AppUser.class))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/user/new")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    void testDeleteUser() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(0)).thenReturn(new AppUser(0, "firstName", "", "lastName"));
        when(mockUserService.deleteUser(0)).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/user/{pcn}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testDeleteUser_InvalidPcn() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(0)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/user/{pcn}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testDeleteUser_ServerError() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(0)).thenReturn(new AppUser(0, "firstName", "", "lastName"));
        when(mockUserService.deleteUser(0)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/user/{pcn}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    void updateUserInfo() throws Exception{
        // Setup
        AppUser user = new AppUser(438161, "Pim", "van", "Hooren");
        when(mockUserService.getUserByPcn(438161)).thenReturn(user);
        when(mockUserService.updateUser(any(AppUser.class))).thenReturn(user);
        when(mockPropertyService.getUserProperties(438161)).thenReturn(null);

        final List<UserProperty> userProperties = new ArrayList<>();
        userProperties.add(new UserHobby(user, "Lego"));
        when(mockPropertyService.getUserProperties(any(int.class))).thenReturn(userProperties);

        // Run the tests
        final MockHttpServletResponse response = mockMvc.perform(put("/api/user/438161")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testUpdateUserInfo_BadRequest() throws Exception{
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/api/user/438161")
                .content("{\"firstName\":\"\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void testUpdateUserInfo_NotFound() throws Exception{
        // Setup
        when(mockUserService.getUserByPcn(438161)).thenReturn(null);

        // Run the tests
        final MockHttpServletResponse response = mockMvc.perform(put("/api/user/438161")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
