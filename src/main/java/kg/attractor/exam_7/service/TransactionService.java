package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.exceptions.InvalidCurrencyException;
import kg.attractor.exam_7.exceptions.NotAccaptableException;
import kg.attractor.exam_7.exceptions.NotEnoughFundsOnAccountException;

import java.util.List;

public interface TransactionService {
    void rollBack(Long id) throws NotEnoughFundsOnAccountException, NotAccaptableException, InvalidCurrencyException;

    void approveTransaction(Long id);

    List<HistoryDto> getTransactionHistory();

    List<HistoryDto> getTransactionHistoryApproval();
}
