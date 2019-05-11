package bupt.coder.course;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class CourseControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String bashUrl = "/Course/";
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void put() throws Exception {
        String content = "{\n" +
                "  \"name\": \"NewCourse\",\n" +
                "  \"teacherId\": 1,\n" +
                "  \"startTime\": \"2019-01-01\",\n" +
                "  \"endTime\": \"2019-06-29\",\n" +
                "  \"place\": \"学六339\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(bashUrl).content(content).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }


    @Test
    public void delete() throws Exception {
        long id = 1;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(bashUrl + id).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }


    @Test
    public void post() throws Exception {
        long id = 2;
        String content = "{\n" +
                "  \"name\": \"NewCourse\",\n" +
                "  \"teacherId\": 1,\n" +
                "  \"startTime\": \"2019-01-01\",\n" +
                "  \"endTime\": \"2019-06-29\",\n" +
                "  \"place\": \"学六339\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(bashUrl + id).content(content).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void find() throws Exception {
        long id = 2;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(bashUrl + id).contentType(MediaType.APPLICATION_JSON)).andReturn();
        JsonNode jsonNode = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertEquals("SPM2", jsonNode.get("name").asText());
    }

    @Test
    public void fingAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(bashUrl).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }


}
