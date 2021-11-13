package eu.europeana.controllers;

import com.google.gson.Gson;
import eu.europeana.config.ServiceConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Unit Tests for {@link ServiceController}
 *
 * @author Jorge Ortiz
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(ServiceConfiguration.class)
public class ServiceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    public void welcomeIndex() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to Europeana Smallest Positive Number Calculator API")));
    }

    @Test
    public void setMaxValue() throws Exception {
        this.mockMvc.perform(post("http://localhost:8080/upper/5")).andDo(print()).andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.uri").exists());
    }
}
