package pl.lottery.lotterygenerator.winningnumbergenerator;

import pl.lottery.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface WinningNumberGeneratorFacade {
    WinningNumbersResponseDto generateWinningNumbers(LocalDateTime drawDate);

    WinningNumbersResponseDto retrieveWinningNumbersForDrawDate(LocalDateTime drawDate);

    WinningNumbersResponseDto deleteWinningNumbersForDate(LocalDateTime drawDate);

    List<WinningNumbersResponseDto> getAllWinningNumbers();
}
