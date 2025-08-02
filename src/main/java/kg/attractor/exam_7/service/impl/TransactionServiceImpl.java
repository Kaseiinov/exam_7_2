package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.HistoryDao;
import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.model.History;
import kg.attractor.exam_7.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final HistoryDao historyDao;

    @Override
    public List<HistoryDto> getTransactionHistory() {
        List<History> histories = historyDao.getTransactionHistory();
        return histories
                .stream()
                .map(h -> HistoryDto
                        .builder()
                        .fromAcc(h.getFromAcc())
                        .toAcc(h.getToAcc())
                        .amountMoney(h.getAmountMoney())
                        .approved(h.getApproved())
                        .build()
                ).toList();
    }
}
