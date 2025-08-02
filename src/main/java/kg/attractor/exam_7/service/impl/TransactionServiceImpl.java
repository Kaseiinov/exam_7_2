package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.AccountDao;
import kg.attractor.exam_7.dao.HistoryDao;
import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.exceptions.NotFoundException;
import kg.attractor.exam_7.model.History;
import kg.attractor.exam_7.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final HistoryDao historyDao;
    private final AccountDao accountDao;

    @Override
    public void approveTransaction(Long id) {
        History history = historyDao.getHistoryById(id).orElseThrow(NotFoundException::new);
        accountDao.makeTransaction(history.getFromAcc(), history.getToAcc(), history.getAmountMoney());
        historyDao.approveTransaction(id);
    }

    @Override
    public List<HistoryDto> getTransactionHistory() {
        List<History> histories = historyDao.getTransactionHistory();
        return histories
                .stream()
                .map(h -> HistoryDto
                        .builder()
                        .id(h.getId())
                        .fromAcc(h.getFromAcc())
                        .toAcc(h.getToAcc())
                        .amountMoney(h.getAmountMoney())
                        .approved(h.getApproved())
                        .build()
                ).toList();
    }

    @Override
    public List<HistoryDto> getTransactionHistoryApproval() {
        List<History> histories = historyDao.getTransactionHistoryApproval();
        return histories
                .stream()
                .map(h -> HistoryDto
                        .builder()
                        .id(h.getId())
                        .fromAcc(h.getFromAcc())
                        .toAcc(h.getToAcc())
                        .amountMoney(h.getAmountMoney())
                        .approved(h.getApproved())
                        .build()
                ).toList();
    }
}
