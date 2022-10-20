package pl.lottery.lotterygenerator.mockedcallers;

import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;
import pl.lottery.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;

@Service
public class MockMvcWinningGeneratorCaller {

    public static final String API_URL_GENERATE = "/api/v1/generate";
    public static final String API_URL_RETRIEVE = "/api/v1/numbers";
    public static final String DATE_PARAM_NAME = "drawDate";
    private final MockMcvCaller mockMcvCaller;
    private final JsonConverters jsonConverters;

    public MockMvcWinningGeneratorCaller(MockMcvCaller mockMcvCaller, JsonConverters jsonConverters) {
        this.mockMcvCaller = mockMcvCaller;
        this.jsonConverters = jsonConverters;
    }

    public WinningNumbersResponseDto mockedPostCallToGenerateWinningNumbers(LocalDateTime drawDate) {
        try {
            String requestBodyAsJson = jsonConverters.convertDateTimeToWinningNumbersRequestBody(drawDate);
            MvcResult mvcCallResult = mockMcvCaller.makePostMockedCallWithJson(API_URL_GENERATE, requestBodyAsJson);
            String responseAsJson = mvcCallResult.getResponse().getContentAsString();
            return jsonConverters.convertJsonResponseToWinningNumbersDto(responseAsJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WinningNumbersResponseDto mockedGetCallToRetrieveNumbers(LocalDateTime drawDate) {
        try {
            String drawDateAsString = drawDate.toString();
            MvcResult mvcCallResult = mockMcvCaller.makeGetMockedCallWithParam(API_URL_RETRIEVE, DATE_PARAM_NAME, drawDateAsString);
            String responseAsJson = mvcCallResult.getResponse().getContentAsString();
            return jsonConverters.convertJsonResponseToWinningNumbersDto(responseAsJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}