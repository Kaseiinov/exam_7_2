package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.AccountDto;
import kg.attractor.exam_7.dto.CreateAccDto;
import kg.attractor.exam_7.dto.TopUpDto;
import kg.attractor.exam_7.exceptions.WrongUserException;
import org.springframework.security.core.Authentication;

import java.rmi.NotBoundException;
import java.util.List;

public interface AccountService {
    List<AccountDto> getAccounts();

    void toUp(Authentication authentication, TopUpDto topUp) throws WrongUserException;

    void createAcc(CreateAccDto createAcc);

    Double getBalance(String accNum) throws NotBoundException;
}
