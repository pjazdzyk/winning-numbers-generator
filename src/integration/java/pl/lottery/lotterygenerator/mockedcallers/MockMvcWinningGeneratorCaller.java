package pl.lottery.lotterygenerator.mockedcallers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;
import pl.lottery.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;

@Service
public class MockMvcWinningGeneratorCaller {

    public static final String API_URL_GENERATE = "/api/v1/generate";
    public static final String API_URL_RETRIEVE = "/api/v1/numbers";
    private final ObjectMapper objectMapper;
    private final MockMcvCaller mockMcvCaller;

    private final JsonConverters jsonConverters;

    public MockMvcWinningGeneratorCaller(ObjectMapper objectMapper, MockMcvCaller mockMcvCaller, JsonConverters jsonConverters) {
        this.objectMapper = objectMapper;
        this.mockMcvCaller = mockMcvCaller;
        this.jsonConverters = jsonConverters;
    }

    public WinningNumbersResponseDto mockedCallToGenerateWinningNumbers(LocalDateTime drawDate) {
        try {
            String requestBodyAsJson = jsonConverters.convertDateTimeToWinningNumbersRequestBody(drawDate);
            MvcResult mvcCallResult = mockMcvCaller.makePostControllerCall(API_URL_GENERATE, requestBodyAsJson);
            String responseAsJson = mvcCallResult.getResponse().getContentAsString();
            return objectMapper.readValue(responseAsJson, WinningNumbersResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WinningNumbersResponseDto mockedCallToRetrieveWinningNumbers(LocalDateTime drawDate) {
        try {
            String requestBodyAsJson = jsonConverters.convertDateTimeToWinningNumbersRequestBody(drawDate);
            MvcResult mvcCallResult = mockMcvCaller.makeGetControllerCall(API_URL_RETRIEVE, requestBodyAsJson);
            String responseAsJson = mvcCallResult.getResponse().getContentAsString();
            return objectMapper.readValue(responseAsJson, WinningNumbersResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}