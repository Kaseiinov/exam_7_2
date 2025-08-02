package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.AccountDao;
import kg.attractor.exam_7.dao.CurrencyDao;
import kg.attractor.exam_7.dao.UserDao;
import kg.attractor.exam_7.dto.CreateAcc;
import kg.attractor.exam_7.exceptions.CurrencyNotFoundException;
import kg.attractor.exam_7.exceptions.UserNotFoundException;
import kg.attractor.exam_7.model.Account;
import kg.attractor.exam_7.model.Currency;
import kg.attractor.exam_7.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;
    private final UserDao userDao;
    private final CurrencyDao currencyDao;

    public void createAcc(CreateAcc createAcc) {
        User user = userDao.findById(createAcc.getUserId()).orElseThrow(UserNotFoundException::new);
        Currency currency = currencyDao.findById(createAcc.getCurrency()).orElseThrow(CurrencyNotFoundException::new);
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "_" + user.getPhone();
        Account acc = Account
                .builder()
                .userId(user.getId())
                .currencyId(currency.getId())
                .balance(0.0)
                .uniqNumber(resultFileName)
                .build();
        accountDao.createAcc(acc);
        log.info("Created account {}", acc.getUniqNumber());
    }
}
