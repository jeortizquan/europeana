package eu.europeana.controllers;

import com.google.gson.Gson;
import eu.europeana.config.ServiceConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
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

    private static final String API_HOST ="http://localhost:8000";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @Test
    public void welcomeIndex() throws Exception {
        this.mockMvc.perform(get(API_HOST)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to Europeana Smallest Positive Number Calculator API")));
    }

    @Test
    public void setMaxValueJSON() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/5").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.smallestPositiveNumber").exists())
                .andExpect(jsonPath("$.to").exists())
                .andExpect(jsonPath("$.from").exists())
                .andExpect(jsonPath("$.elapsedTime").exists());
    }

    @Test
    public void setMaxValueXML() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/5").accept(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(xpath("/CalculationRecord/smallestPositiveNumber").exists())
                .andExpect(xpath("/CalculationRecord/from").exists())
                .andExpect(xpath("/CalculationRecord/to").exists())
                .andExpect(xpath("/CalculationRecord/elapsedTime").exists());
    }

    @Test
    public void setMaxValueWithoutHeader() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/5"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setMaxValueWithoutValue() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void setMaxValueWithHeaderWithoutValue() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void calculateValueWithoutHeader() throws Exception {
        this.mockMvc.perform(get(API_HOST+"/calculate"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setMaxValueNegativeValueJSON() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/-2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setMaxValueNegativeValueXML() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/-2").accept(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setMaxValueGreaterThanMaxLimit() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/123").accept(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void setMaxValueInvalidValue() throws Exception {
        this.mockMvc.perform(post(API_HOST+"/maxvalue/a").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateValueJSON() throws Exception {
        this.mockMvc.perform(get(API_HOST+"/calculate").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.smallestPositiveNumber").exists())
                .andExpect(jsonPath("$.to").exists())
                .andExpect(jsonPath("$.from").exists())
                .andExpect(jsonPath("$.elapsedTime").exists());
    }

    @Test
    public void calculateValueXML() throws Exception {
        this.mockMvc.perform(get(API_HOST+"/calculate").accept(MediaType.APPLICATION_XML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/CalculationRecord/smallestPositiveNumber").exists())
                .andExpect(xpath("/CalculationRecord/from").exists())
                .andExpect(xpath("/CalculationRecord/to").exists())
                .andExpect(xpath("/CalculationRecord/elapsedTime").exists());
    }
}
