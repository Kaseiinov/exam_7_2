package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.HistoryDto;

import java.util.List;

public interface TransactionService {
    void approveTransaction(Long id);

    List<HistoryDto> getTransactionHistory();

    List<HistoryDto> getTransactionHistoryApproval();
}
