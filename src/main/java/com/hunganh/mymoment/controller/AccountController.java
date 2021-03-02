package com.hunganh.mymoment.controller;


import com.hunganh.mymoment.model.Account;
import com.hunganh.mymoment.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/accounts")
public class AccountController {
    private final AccountRepository accountRepository;
    @GetMapping(value = { "", "/" })
    List<Account> getAccountByUserName() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }
}
