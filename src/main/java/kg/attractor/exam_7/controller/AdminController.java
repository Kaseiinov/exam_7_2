package kg.attractor.exam_7.controller;

import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.dto.TransactionDto;
import kg.attractor.exam_7.exceptions.InvalidCurrencyException;
import kg.attractor.exam_7.exceptions.NotAccaptableException;
import kg.attractor.exam_7.exceptions.NotEnoughFundsOnAccountException;
import kg.attractor.exam_7.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TransactionService transactionService;

    @PostMapping("transactions/rollback")
    public void rollBack(@RequestParam Long id) throws NotEnoughFundsOnAccountException, NotAccaptableException, InvalidCurrencyException {
        transactionService.rollBack(id);
    }

    @PostMapping("transactions/approval")
    public void approveTransactionById(@RequestParam Long id) {
        transactionService.approveTransaction(id);
    }

    @GetMapping("transactions")
    public List<HistoryDto> getTransactions() {
        return transactionService.getTransactionHistory();
    }

    @GetMapping("transactions/approval")
    public List<HistoryDto> getTransactionsApproval() {
        return transactionService.getTransactionHistoryApproval();
    }
}
