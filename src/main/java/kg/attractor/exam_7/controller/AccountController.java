package kg.attractor.exam_7.controller;

import kg.attractor.exam_7.dto.AccountDto;
import kg.attractor.exam_7.dto.CreateAcc;
import kg.attractor.exam_7.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NotBoundException;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public HttpStatus createAcc(@RequestBody CreateAcc createAcc) {
        accountService.createAcc(createAcc);
        return HttpStatus.CREATED;
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam String accNumber) throws NotBoundException {
        return ResponseEntity.ok(accountService.getBalance(accNumber));
    }
}
