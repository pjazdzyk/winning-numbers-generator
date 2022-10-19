package pl.lottery.lotterygenerator.mockedcallers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.lottery.lotterygenerator.winningnumbergenerator.dto.WinningNumbersRequestDto;

import java.time.LocalDateTime;

@Service
public class JsonConverters {

    private final ObjectMapper objectMapper;

    public JsonConverters(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convertDateTimeToWinningNumbersRequestBody(LocalDateTime drawDate) throws JsonProcessingException {
        WinningNumbersRequestDto winningNumbersRequestDto = new WinningNumbersRequestDto(drawDate);
        return objectMapper.writeValueAsString(winningNumbersRequestDto);
    }


}
