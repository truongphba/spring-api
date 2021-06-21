package com.example.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String fullName;
    private String passwordHash;
    private int role;
    private int status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Credential> tokens;


    public String getRoleString() {
        return this.role == 1 ? "ADMIN" : "USER";
    }


    public static final class AccountBuilder {
        private String username;
        private String fullName;
        private String passwordHash;
        private int role;
        private int status;
        private Set<Credential> tokens;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public AccountBuilder withPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public AccountBuilder withRole(int role) {
            this.role = role;
            return this;
        }

        public AccountBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public AccountBuilder withTokens(Set<Credential> tokens) {
            this.tokens = tokens;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.setUsername(username);
            account.setFullName(fullName);
            account.setPasswordHash(passwordHash);
            account.setRole(role);
            account.setStatus(status);
            account.setTokens(tokens);
            return account;
        }
    }
}
