package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.HistoryDto;

import java.util.List;

public interface TransactionService {
    List<HistoryDto> getTransactionHistory();
}
