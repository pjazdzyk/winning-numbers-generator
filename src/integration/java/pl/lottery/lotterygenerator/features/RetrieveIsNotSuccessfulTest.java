package pl.lottery.lotterygenerator.features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import pl.lottery.lotterygenerator.BaseIntegrationSpec;
import pl.lottery.lotterygenerator.TestConstants;
import pl.lottery.lotterygenerator.winningnumbergenerator.dto.WinningNumberStatus;
import pl.lottery.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveIsNotSuccessfulTest extends BaseIntegrationSpec implements TestConstants {

    @DynamicPropertySource
    private static void propertyOverride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    @DisplayName("should return NOT_FOUND dto when queried for draw date which does not exists in database")
    public void notFoundPath_shouldReturnNotFoundStatus_whenNonExistingDrawDateIsGiven() {
        // given
        LocalDateTime sampleDrawDate = LocalDateTime.of(1974, 1, 1, 12, 10, 0);

        // when
        WinningNumbersResponseDto actualWinNumbersDto = mockMvcWinningGeneratorCaller.mockedGetCallToRetrieveNumbers(sampleDrawDate);

        // then
        assertThat(actualWinNumbersDto.status()).isEqualTo(WinningNumberStatus.NOT_FOUND);
        assertThat(actualWinNumbersDto.drawDate()).isEqualTo(sampleDrawDate);
        assertThat(actualWinNumbersDto.winningNumbers()).isNull();
    }

}
