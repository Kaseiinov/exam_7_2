package kg.attractor.exam_7.controller;

import jakarta.validation.Valid;
import kg.attractor.exam_7.dto.CreateAccDto;
import kg.attractor.exam_7.dto.TopUpDto;
import kg.attractor.exam_7.exceptions.WrongUserException;
import kg.attractor.exam_7.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.rmi.NotBoundException;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/balance")
    public HttpStatus topUpBalance(Authentication authentication, @RequestBody @Valid TopUpDto topUp) throws WrongUserException {
        accountService.toUp(authentication, topUp);
        return HttpStatus.OK;
    }

    @PostMapping
    public HttpStatus createAcc(@RequestBody CreateAccDto createAcc) {
        accountService.createAcc(createAcc);
        return HttpStatus.CREATED;
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accNumber) throws NotBoundException {
        return ResponseEntity.ok(accountService.getBalance(accNumber));
    }
}
