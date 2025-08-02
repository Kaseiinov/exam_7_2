package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.AccountDto;
import kg.attractor.exam_7.dto.CreateAccDto;
import kg.attractor.exam_7.dto.TopUpDto;
import kg.attractor.exam_7.dto.TransactionDto;
import kg.attractor.exam_7.exceptions.InvalidCurrencyException;
import kg.attractor.exam_7.exceptions.NotAccaptableException;
import kg.attractor.exam_7.exceptions.NotEnoughFundsOnAccountException;
import kg.attractor.exam_7.exceptions.WrongUserException;
import org.springframework.security.core.Authentication;

import java.rmi.NotBoundException;
import java.util.List;

public interface AccountService {
    void makeTransaction(TransactionDto transactionDto) throws InvalidCurrencyException, NotEnoughFundsOnAccountException, NotAccaptableException;

    void makeTransaction(TransactionDto transactionDto, Authentication auth) throws InvalidCurrencyException, NotEnoughFundsOnAccountException;

    List<AccountDto> getAccounts();

    void toUp(Authentication authentication, TopUpDto topUp) throws WrongUserException;

    void createAcc(CreateAccDto createAcc, Authentication auth);

    Double getBalance(String accNum) throws NotBoundException;
}
