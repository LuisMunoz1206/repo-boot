package gm.practica.Integrity;


import com.fasterxml.jackson.databind.ObjectMapper;
import gm.practica.DTOs.FollowersListDTO;
import gm.practica.DTOs.UserFollowersDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class FollowControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void getFollowersByUserIdTest() throws Exception {
        String url = "/socialMeli/v1/followers/{userId}";
        Long param = 1L;

        RequestBuilder request = MockMvcRequestBuilders.get(url, param);

        UserFollowersDTO requestBody = new UserFollowersDTO(1L, "Juan Perez",
                List.of(
                new FollowersListDTO(2L, "Maria Gomez"),
                new FollowersListDTO(3L, "Carlos Rodriguez")
        ));

        ResultMatcher statusExpected = MockMvcResultMatchers.status().isOk();
        ResultMatcher bodyExpected = MockMvcResultMatchers.content().json(mapper.writeValueAsString(requestBody));
        ResultMatcher contentTypeExpected = MockMvcResultMatchers.content().contentType("application/json");

        mockMvc.perform(request)
                .andExpect(statusExpected)
                .andExpect(bodyExpected)
                .andExpect(contentTypeExpected);
    }







}
