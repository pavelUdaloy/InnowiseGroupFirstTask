package by.controller;

import by.config.WebConfig;
import by.entity.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class RegControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void postttt() throws Exception {
        ServletContext servletContext = webApplicationContext.getServletContext();
        RegController regController = (RegController) webApplicationContext.getBean("regController");
        ObjectMapper objectMapper = (ObjectMapper) webApplicationContext.getBean("objectMapper");
        UserDto userDto = new UserDto();
        userDto.setEmail("pavelssaaab1111212@gmail.com");
        userDto.setFirstName("pavelssaaab1111212@gmail.com");
        userDto.setLastName("pavelssaaab1111212@gmail.com");

        MvcResult mvcResult = mockMvc.perform(post("/reg", 42L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult);
    }
}