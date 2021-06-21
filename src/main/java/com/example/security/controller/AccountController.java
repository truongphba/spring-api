package com.example.security.controller;

import com.example.security.entity.Account;
import com.example.security.serivce.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account login(@RequestBody String username, @RequestBody String password) {
        return accountService.login(username, password);
    }
}
