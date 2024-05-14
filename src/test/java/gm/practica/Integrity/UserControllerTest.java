package gm.practica.Integrity;

import com.fasterxml.jackson.databind.ObjectMapper;
import gm.practica.DTOs.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void getUsersTest() throws Exception {
        String url = "/socialMeli/v1/users";

        RequestBuilder request = MockMvcRequestBuilders.get(url);

        UserDTO requestBody = new UserDTO(1L, "Juan Perez");

        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();

        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(mapper.writeValueAsString(requestBody));

        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType("application/json");

        mockMvc.perform(request)
                .andExpect(statusExpected)
                .andExpect(bodyExpected)
                .andExpect(contentTypeExpected);
    }







}