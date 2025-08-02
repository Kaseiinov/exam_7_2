package kg.attractor.exam_7.controller;

import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.dto.TransactionDto;
import kg.attractor.exam_7.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TransactionService transactionService;

    @GetMapping("transactions")
    public List<HistoryDto> getTransactions() {
        return transactionService.getTransactionHistory();
    }
}
