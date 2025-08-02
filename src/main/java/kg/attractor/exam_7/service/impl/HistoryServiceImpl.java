package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.HistoryDao;
import kg.attractor.exam_7.dto.HistoryDto;
import kg.attractor.exam_7.model.History;
import kg.attractor.exam_7.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryDao historyDao;

    @Override
    public List<HistoryDto> getHistoriesByAccNum(String accNum) {
        List<History> histories = historyDao.getHistoryByAccNum(accNum);
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
