package pl.lotto.lotterygenerator.winningnumbergenerator;

import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumberStatus;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class WinningNumberGeneratorFacade {

    private final WinningNumberGenerator winningNumberGenerator;
    private final WinningNumberRepository winningNumberRepository;

    public WinningNumberGeneratorFacade(WinningNumberGenerator winningNumberGenerator, WinningNumberRepository winningNumberRepository) {
        this.winningNumberGenerator = winningNumberGenerator;
        this.winningNumberRepository = winningNumberRepository;

    }

    public WinningNumbersResponseDto generateWinningNumbers(LocalDateTime drawDate) {
        if (checkIfActualNumbersAreAlreadyDrawnAndSaved(drawDate)) {
            return new WinningNumbersResponseDto(drawDate, null, WinningNumberStatus.ALREADY_EXISTS);
        }
        WinningNumbers winningNumbers = winningNumberGenerator.generateWinningNumbers(drawDate);
        winningNumberRepository.save(winningNumbers);
        return WinningNumberMapper.toDto(winningNumbers, WinningNumberStatus.GENERATED);
    }

    public WinningNumbersResponseDto retrieveWinningNumbersForDrawDate(LocalDateTime drawDate) {
        Optional<WinningNumbers> winningNumbersForDrawDateOptional = winningNumberRepository.findByDrawDate(drawDate);
        if (winningNumbersForDrawDateOptional.isEmpty()) {
            return notFoundDto(drawDate);
        }
        return WinningNumberMapper.toDto(winningNumbersForDrawDateOptional.get(), WinningNumberStatus.LOADED_FROM_DB);
    }

    public WinningNumbersResponseDto deleteWinningNumbersForDate(LocalDateTime drawDate) {
        Optional<WinningNumbers> winningNumbersForDrawDateOptional = winningNumberRepository.findByDrawDate(drawDate);
        if (winningNumbersForDrawDateOptional.isEmpty()) {
            return notFoundDto(drawDate);
        }
        winningNumberRepository.deleteByDrawDate(drawDate);
        return WinningNumberMapper.toDto(winningNumbersForDrawDateOptional.get(), WinningNumberStatus.DELETED);
    }

    public List<WinningNumbersResponseDto> getAllWinningNumbers() {
        List<WinningNumbers> allWinningNumbers = winningNumberRepository.findAll();
        return WinningNumberMapper.toDtoList(allWinningNumbers, WinningNumberStatus.LOADED_FROM_DB);
    }

    private boolean checkIfActualNumbersAreAlreadyDrawnAndSaved(LocalDateTime drawDate) {
        return winningNumberRepository.existsByDrawDate(drawDate);
    }

    private WinningNumbersResponseDto notFoundDto(LocalDateTime drawDate) {
        return new WinningNumbersResponseDto(drawDate, null, WinningNumberStatus.NOT_FOUND);
    }

}
