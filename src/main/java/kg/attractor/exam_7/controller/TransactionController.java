package kg.attractor.exam_7.controller;

import jakarta.validation.Valid;
import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.dto.TransactionDto;
import kg.attractor.exam_7.exceptions.InvalidCurrencyException;
import kg.attractor.exam_7.exceptions.NotEnoughFundsOnAccountException;
import kg.attractor.exam_7.service.AccountService;
import kg.attractor.exam_7.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final AccountService accountService;
    private final HistoryService historyService;

    @PostMapping("transactions")
    public void makeTransaction(@RequestBody @Valid TransactionDto transactionDto) throws NotEnoughFundsOnAccountException, InvalidCurrencyException {
        accountService.makeTransaction(transactionDto);

    }

    @GetMapping("/transactions/{accountNumber}/history")
    public List<HistoryDto> getHistoriesByAccNum(@PathVariable String accountNumber){
        return historyService.getHistoriesByAccNum(accountNumber);
    }
}
