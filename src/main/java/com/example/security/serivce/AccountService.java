package com.example.security.serivce;

import com.example.security.entity.Account;
import com.example.security.entity.Credential;
import com.example.security.repository.AccountRepository;
import com.example.security.repository.CredentialRepository;
import com.example.security.util.DataTimeUtil;
import com.example.security.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //register API
    public Account register(Account requestAccount) {
        Account account = Account.AccountBuilder.anAccount()
                .withUsername(requestAccount.getUsername())
                .withPasswordHash(passwordEncoder.encode(requestAccount.getPasswordHash()))
                .withRole(requestAccount.getRole())
                .withFullName(requestAccount.getFullName())
                .withStatus(requestAccount.getStatus())
                .build();
        accountRepository.save(account);
        return null;
    }

    // login API có token
//    public Credential login(String username, String password) {
//        Optional<Account> accountOptional = accountRepository.findAccountByUsername(username);
//        if (accountOptional.isPresent()) {
//            Account account = accountOptional.get();
//            if (passwordEncoder.matches(password, account.getPasswordHash())) {
//                Credential credential = Credential.CredentialBuilder.aCredential()
//                        .withTokenKey(StringUtil.generateAccessToken())
//                        .withCreatedAt(DataTimeUtil.getCurrentTimeInMLS())
//                        .withExpiredAt(DataTimeUtil.getTimeAfterDay(10))
//                        .withAccount(account)
//                        .build();
//                return credentialRepository.save(credential);
//            }
//        }
//        return null;
//    }

    public Account login (String username, String password) {
        Optional<Account> accountOptional = accountRepository.findAccountByUsername(username);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (passwordEncoder.matches(password, account.getPasswordHash())) {
                return account;
            }
        }
        return null;
    }

    public Account findAccountByToken(String accessToken) {
        Optional<Credential>  optionalCredential = credentialRepository.findById(accessToken);
        if (optionalCredential.isPresent()){
            Credential credential = optionalCredential.get();
            if (credential.isExpired()){
                return null;
            }
            return credential.getAccount();
        }
        return null;
    }
}
