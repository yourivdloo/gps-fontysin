package fontysin.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontysin.project.dto.UserPropertyDTO;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;
import fontysin.project.model.user.properties.UserSkill;
import fontysin.project.services.PropertyService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PropertyController.class)
class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService mockPropertyService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void testAddUserProperty() throws Exception {
        // Setup
        final AppUser appUser = new AppUser(422773, "first", "", "last");
        final UserProperty userProperty = new UserSkill(appUser, "Organizing");

        when(mockPropertyService.addUserProperty(any(UserPropertyDTO.class))).thenReturn(userProperty);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/property/new")
                .content("{\"type\":\"UserSkill\",\"name\":\"Organizing\"}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(mapper.writeValueAsString(userProperty), response.getContentAsString());
    }
}
