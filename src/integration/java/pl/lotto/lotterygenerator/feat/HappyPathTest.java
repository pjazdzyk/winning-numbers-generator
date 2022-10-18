package pl.lotto.lotterygenerator.feat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.lotto.lotterygenerator.BaseIntegrationSpec;
import pl.lotto.lotterygenerator.mockedcallers.TestConstants;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumberStatus;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import static org.assertj.core.api.Assertions.assertThat;

class HappyPathTest extends BaseIntegrationSpec implements TestConstants {

    @Test
    @DisplayName("should return new generated numbers when draw date is provided")
    public void happyPath_shouldGenerateWinningNumbers_whenDrawDateIsGiven() {
        // given

        // when
        WinningNumbersResponseDto actualWinNumbersDto = mockMvcWinningGeneratorCaller.mockedCallToGenerateWinningNumbers(DRAW_DATE);

        // then
        assertThat(actualWinNumbersDto.status()).isEqualTo(WinningNumberStatus.GENERATED);
        assertThat(actualWinNumbersDto.drawDate()).isEqualTo(DRAW_DATE);
        assertThat(actualWinNumbersDto.winningNumbers()).isEqualTo(WINNING_NUMBERS);
    }

    @Test
    @DisplayName("should return ALREADY_EXISTS status if attempted to generate for already stored draw date")
    public void happyPath_shouldNotGenerateNumbersAndReturnAlreadyExistsDto_whenTheSameDrawDateIsProvided() {
        // given
        mockMvcWinningGeneratorCaller.mockedCallToGenerateWinningNumbers(DRAW_DATE);

        // when
        WinningNumbersResponseDto actualSecondCallWinningNumbersDTO = mockMvcWinningGeneratorCaller.mockedCallToGenerateWinningNumbers(DRAW_DATE);

        // then
        assertThat(actualSecondCallWinningNumbersDTO.status()).isEqualTo(WinningNumberStatus.ALREADY_EXISTS);
        assertThat(actualSecondCallWinningNumbersDTO.drawDate()).isEqualTo(DRAW_DATE);
        assertThat(actualSecondCallWinningNumbersDTO.winningNumbers()).isNull();
    }

    @Test
    @DisplayName("should return winning numbers with LOADED_FROM_DB status when requested draw date exists in database")
    public void happyPath_shouldReturnWinningNumbersWithLoadedStatus_whenRequestedDrawDateExistsInDb() {
        // given
        mockMvcWinningGeneratorCaller.mockedCallToGenerateWinningNumbers(DRAW_DATE);

        // when
        WinningNumbersResponseDto actualSecondCallWinningNumbersDTO = mockMvcWinningGeneratorCaller.mockedCallToRetrieveWinningNumbers(DRAW_DATE);

        // then
        assertThat(actualSecondCallWinningNumbersDTO.status()).isEqualTo(WinningNumberStatus.LOADED_FROM_DB);
        assertThat(actualSecondCallWinningNumbersDTO.drawDate()).isEqualTo(DRAW_DATE);
        assertThat(actualSecondCallWinningNumbersDTO.winningNumbers()).isEqualTo(WINNING_NUMBERS);
    }


}
