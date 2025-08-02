package kg.attractor.exam_7.service;

import kg.attractor.exam_7.dto.CreateAcc;

import java.rmi.NotBoundException;

public interface AccountService {
    void createAcc(CreateAcc createAcc);

    Double getBalance(String accNum) throws NotBoundException;
}
