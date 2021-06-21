package com.example.security.config;

import com.example.security.entity.Account;
import com.example.security.serivce.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    AccountService accountService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object credential = usernamePasswordAuthenticationToken.getCredentials();
        if (credential == null){
            throw new UsernameNotFoundException("Credential not found");
        }
        String accessToken = String.valueOf(credential);
        Account account = accountService.findAccountByToken(accessToken);
        if (account == null){
            throw new UsernameNotFoundException("Cannot find user with authentication token " + accessToken);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPasswordHash())
                .roles(account.getRoleString())
                .build();
    }
}