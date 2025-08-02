package kg.attractor.exam_7.controller;

import jakarta.validation.Valid;
import kg.attractor.exam_7.dto.*;
import kg.attractor.exam_7.exceptions.InvalidCurrencyException;
import kg.attractor.exam_7.exceptions.NotEnoughFundsOnAccountException;
import kg.attractor.exam_7.exceptions.WrongUserException;
import kg.attractor.exam_7.service.AccountService;
import kg.attractor.exam_7.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.rmi.NotBoundException;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final HistoryService historyService;



    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @PostMapping("/balance")
    public HttpStatus topUpBalance(Authentication authentication, @RequestBody @Valid TopUpDto topUp) throws WrongUserException {
        accountService.toUp(authentication, topUp);
        return HttpStatus.OK;
    }

    @PostMapping
    public HttpStatus createAcc(@RequestBody CreateAccDto createAcc, Authentication auth) {
        accountService.createAcc(createAcc, auth);
        return HttpStatus.CREATED;
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accNumber) throws NotBoundException {
        return ResponseEntity.ok(accountService.getBalance(accNumber));
    }
}
