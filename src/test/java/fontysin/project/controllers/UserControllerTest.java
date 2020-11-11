package fontysin.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontysin.project.model.user.AppUser;
import fontysin.project.services.UserService;
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

import java.util.Collection;
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

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGetAllUsers() throws Exception {
        // Setup

        // Configure UserService.getAllUsers(...).
        final Iterable<AppUser> appUsers = List.of(new AppUser(0, "firstName", "lastName"));
        when(mockUserService.getAllUsers()).thenReturn(appUsers);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/users/all")
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
        final MockHttpServletResponse response = mockMvc.perform(get("/api/users/all")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testGetUserByPcn() throws Exception {
        // Setup

        // Configure UserService.getUserByPcn(...).
        final AppUser appUser = new AppUser(422773, "firstName", "lastName");
        when(mockUserService.getUserByPcn(422773)).thenReturn(appUser);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/users/422773", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(appUser), response.getContentAsString());
    }

    @Test
    void testGetUserByPcn_InvalidPcn() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(422773)).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/users/400000", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testCreateUser() throws Exception {
        // Setup

        //Configure UserService.createUser(...).
        final AppUser appUser = new AppUser(422773, "firstName", "lastName");
        when(mockUserService.createUser(any(AppUser.class))).thenReturn(appUser);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/users/new")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .header("x-ms-client-principal-name", "422773@student.fontys.nl")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(appUser), response.getContentAsString());
    }

    @Test
    void testCreateUser_MissingArgument() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/users/new")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/api/users/new")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/api/users/new")
                .content("{\"firstName\":\"firstName\",\"lastName\":\"lastName\"}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }

    @Test
    void testDeleteUser() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(0)).thenReturn(new AppUser(0, "firstName", "lastName"));
        when(mockUserService.deleteUser(0)).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/users/{pcn}", 0)
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
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/users/{pcn}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    void testDeleteUser_ServerError() throws Exception {
        // Setup
        when(mockUserService.getUserByPcn(0)).thenReturn(new AppUser(0, "firstName", "lastName"));
        when(mockUserService.deleteUser(0)).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/users/{pcn}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    }
}