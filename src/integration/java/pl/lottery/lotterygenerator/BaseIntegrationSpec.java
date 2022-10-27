package pl.lottery.lotterygenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import pl.lottery.lotterygenerator.infrastructure.controllers.WinningNumbersRestController;
import pl.lottery.lotterygenerator.mockedcallers.JsonConverters;
import pl.lottery.lotterygenerator.mockedcallers.MockMvcWinningGeneratorCaller;

@SpringBootTest(classes = AppRunner.class)
@Testcontainers
@AutoConfigureMockMvc
public class BaseIntegrationSpec {
    @Autowired
    protected WinningNumbersRestController winningNumbersRestController;
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected MockMvcWinningGeneratorCaller mockMvcWinningGeneratorCaller;
    @Autowired
    protected JsonConverters jsonConverters;
    @Container
    protected static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
    
}

