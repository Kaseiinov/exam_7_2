package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.AccountDao;
import kg.attractor.exam_7.dao.HistoryDao;
import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.dto.TransactionDto;
import kg.attractor.exam_7.exceptions.InvalidCurrencyException;
import kg.attractor.exam_7.exceptions.NotAccaptableException;
import kg.attractor.exam_7.exceptions.NotEnoughFundsOnAccountException;
import kg.attractor.exam_7.exceptions.NotFoundException;
import kg.attractor.exam_7.model.History;
import kg.attractor.exam_7.service.AccountService;
import kg.attractor.exam_7.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final HistoryDao historyDao;
    private final AccountDao accountDao;
    private final AccountService accountService;

    @Override
    public void rollBack(Long id) throws NotEnoughFundsOnAccountException, NotAccaptableException, InvalidCurrencyException {
        History history = historyDao.getTrueHistoryById(id).orElseThrow(NotFoundException::new);
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromAcc(history.getFromAcc());
        transactionDto.setToAcc(history.getToAcc());
        transactionDto.setAmount(history.getAmountMoney());
        accountService.makeTransaction(transactionDto);
    }

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
