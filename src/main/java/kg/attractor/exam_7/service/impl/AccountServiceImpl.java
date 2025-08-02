package kg.attractor.exam_7.service.impl;

import kg.attractor.exam_7.dao.AccountDao;
import kg.attractor.exam_7.dao.CurrencyDao;
import kg.attractor.exam_7.dao.UserDao;
import kg.attractor.exam_7.dto.CreateAccDto;
import kg.attractor.exam_7.dto.TopUpDto;
import kg.attractor.exam_7.exceptions.CurrencyNotFoundException;
import kg.attractor.exam_7.exceptions.UserNotFoundException;
import kg.attractor.exam_7.exceptions.WrongUserException;
import kg.attractor.exam_7.model.Account;
import kg.attractor.exam_7.model.Currency;
import kg.attractor.exam_7.model.User;
import kg.attractor.exam_7.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final UserDao userDao;
    private final CurrencyDao currencyDao;

    @Override
    public void toUp(Authentication authentication, TopUpDto topUp) throws WrongUserException {
        User user = userDao.findByPhone(authentication.getName()).orElseThrow(UserNotFoundException::new);
        boolean isUser = accountDao.isAccountOwnedByUser(topUp.getAccNumber(), user.getPhone());
        if (isUser) {
            accountDao.toUp(user.getId(), topUp.getAccNumber(), topUp.getBalance());
        }else {
            throw new WrongUserException();
        }
    }

    @Override
    public void createAcc(CreateAccDto createAcc) {
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

    @Override
    public Double getBalance(String accNum) throws NotBoundException {
        return accountDao.getBalanceByNum(accNum).orElseThrow(NotBoundException::new);
    }

}
