package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.CreateAccDto;
import kg.attractor.exam_7.dto.TopUpDto;
import kg.attractor.exam_7.exceptions.WrongUserException;
import org.springframework.security.core.Authentication;

import java.rmi.NotBoundException;

public interface AccountService {
    void toUp(Authentication authentication, TopUpDto topUp) throws WrongUserException;

    void createAcc(CreateAccDto createAcc);

    Double getBalance(String accNum) throws NotBoundException;
}
