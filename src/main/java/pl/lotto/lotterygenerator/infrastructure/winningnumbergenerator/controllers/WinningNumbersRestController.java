package pl.lotto.lotterygenerator.infrastructure.winningnumbergenerator.controllers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.lotterygenerator.winningnumbergenerator.WinningNumberGeneratorFacade;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersRequestDto;
import pl.lotto.lotterygenerator.winningnumbergenerator.dto.WinningNumbersResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class WinningNumbersRestController {

    private final WinningNumberGeneratorFacade winningNumberGeneratorFacade;

    public WinningNumbersRestController(WinningNumberGeneratorFacade winningNumberGeneratorFacade) {
        this.winningNumberGeneratorFacade = winningNumberGeneratorFacade;
    }

    @PostMapping(value="/api/v1/generate")
    public ResponseEntity<WinningNumbersResponseDto> generateWinningNumbersForDrawDate(@RequestBody WinningNumbersRequestDto winningNumbersRequestDto){
        LocalDateTime requestDrawDate = winningNumbersRequestDto.getDrawDate();
        WinningNumbersResponseDto hasBeenGenerated = winningNumberGeneratorFacade.generateWinningNumbers(requestDrawDate);
        return ResponseEntity.ok(hasBeenGenerated);
    }

    @Cacheable("number-retrieval")
    @GetMapping(value = "/api/v1/numbers")
    public ResponseEntity<WinningNumbersResponseDto> getWinningNumbersForDrawDate(@RequestBody WinningNumbersRequestDto winningNumbersRequestDto){
        LocalDateTime requestDrawDate = winningNumbersRequestDto.getDrawDate();
        WinningNumbersResponseDto winningNumbersResponseDto = winningNumberGeneratorFacade.retrieveWinningNumbersForDrawDate(requestDrawDate);
        return ResponseEntity.ok(winningNumbersResponseDto);
    }

    @GetMapping(value = "/api/v1/all")
    public ResponseEntity<List<WinningNumbersResponseDto>> getAllWiningNumbers(){
        List<WinningNumbersResponseDto> winningNumbersResponseDto = winningNumberGeneratorFacade.getAllWinningNumbers();
        return ResponseEntity.ok(winningNumbersResponseDto);
    }

    @DeleteMapping(value = "/api/v1/delete")
    public ResponseEntity<WinningNumbersResponseDto> deleteWinningNumbersForDrawDate(@RequestBody WinningNumbersRequestDto winningNumbersRequestDto){
        LocalDateTime requestDrawDate = winningNumbersRequestDto.getDrawDate();
        WinningNumbersResponseDto winningNumbersResponseDto = winningNumberGeneratorFacade.deleteWinningNumbersForDate(requestDrawDate);
        return ResponseEntity.ok(winningNumbersResponseDto);
    }

}
