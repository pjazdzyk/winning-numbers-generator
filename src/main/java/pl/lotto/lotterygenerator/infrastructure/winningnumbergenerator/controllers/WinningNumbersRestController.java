package pl.lotto.lotterygenerator.infrastructure.winningnumbergenerator.controllers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.lotterygenerator.winningnumbergenerator.WinningNumberGeneratorFacade;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersRequestDto;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;

@RestController
public class WinningNumbersRestController {

    private final WinningNumberGeneratorFacade winningNumberGeneratorFacade;

    public WinningNumbersRestController(WinningNumberGeneratorFacade winningNumberGeneratorFacade) {
        this.winningNumberGeneratorFacade = winningNumberGeneratorFacade;
    }

    @PostMapping(value="/api/v1/generate")
    public ResponseEntity<WinningNumbersResponseDto> generateWinningNumbers(@RequestBody WinningNumbersRequestDto winningNumbersRequestDto){
        LocalDateTime requestDrawDate = winningNumbersRequestDto.getDrawDate();
        WinningNumbersResponseDto hasBeenGenerated = winningNumberGeneratorFacade.generateWinningNumbers(requestDrawDate);
        return ResponseEntity.ok(hasBeenGenerated);
    }

    @Cacheable("number-retrieval")
    @GetMapping(value = "/api/v1/numbers")
    public ResponseEntity<WinningNumbersResponseDto> getWinningNumbers(@RequestBody WinningNumbersRequestDto winningNumbersRequestDto){
        LocalDateTime requestDrawDate = winningNumbersRequestDto.getDrawDate();
        WinningNumbersResponseDto winningNumbersResponseDto = winningNumberGeneratorFacade.retrieveWinningNumbersForDrawDate(requestDrawDate);
        return ResponseEntity.ok(winningNumbersResponseDto);
    }

}
