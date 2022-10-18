package pl.lotto.lotterygenerator.feat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.lotterygenerator.BaseIntegrationSpec;
import pl.lotto.lotterygenerator.mockedcallers.TestConstants;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumberStatus;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundPathTest extends BaseIntegrationSpec implements TestConstants {

    @Test
    @DisplayName("should return NOT_FOUND dto when queried for draw date which does not exists in database")
    public void notFoundPath_shouldReturnNotFoundStatus_whenNonExistingDrawDateIsGiven() {
        // given
        LocalDateTime sampleDrawDate = LocalDateTime.of(1974, 1, 1, 12, 10, 0);

        // when
        WinningNumbersResponseDto actualWinNumbersDto = mockMvcWinningGeneratorCaller.mockedCallToRetrieveWinningNumbers(sampleDrawDate);

        // then
        assertThat(actualWinNumbersDto.status()).isEqualTo(WinningNumberStatus.NOT_FOUND);
        assertThat(actualWinNumbersDto.drawDate()).isEqualTo(sampleDrawDate);
        assertThat(actualWinNumbersDto.winningNumbers()).isNull();
    }

}
