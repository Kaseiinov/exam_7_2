package kg.attractor.exam_7.controller;

import kg.attractor.exam_7.dto.CreateAcc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    @PostMapping
    public HttpStatus createAcc(@RequestBody CreateAcc createAcc) {
        return HttpStatus.CREATED;
    }
}
