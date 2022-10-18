package pl.lotto.lotterygenerator.winningnumbergenerator;

import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumberStatus;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.util.List;
import java.util.stream.Collectors;

class WinningNumberMapper {

    public static WinningNumbersResponseDto toDto(WinningNumbers winningNumbers, WinningNumberStatus status) {
        return new WinningNumbersResponseDto(winningNumbers.drawDate(), winningNumbers.winningNumbers(), status);
    }

    static List<WinningNumbersResponseDto> toDtoList(List<WinningNumbers> winningNumbers, WinningNumberStatus status) {
        return winningNumbers.stream()
                .map(winNums -> WinningNumberMapper.toDto(winNums, status))
                .collect(Collectors.toList());
    }

}
