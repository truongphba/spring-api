package com.example.security.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credential {
    @Id
    private String tokenKey;
    @Column(insertable = false, updatable = false)
    private long accountId ;
    private long createdAt;
    private long expiredAt;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    public boolean isExpired() {
        return this.expiredAt < Calendar.getInstance().getTimeInMillis();
    }

    public static final class CredentialBuilder {
        private String tokenKey;
        private long createdAt;
        private long expiredAt;
        private Account account;

        private CredentialBuilder() {
        }

        public static CredentialBuilder aCredential() {
            return new CredentialBuilder();
        }

        public CredentialBuilder withTokenKey(String tokenKey) {
            this.tokenKey = tokenKey;
            return this;
        }

        public CredentialBuilder withCreatedAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CredentialBuilder withExpiredAt(long expiredAt) {
            this.expiredAt = expiredAt;
            return this;
        }

        public CredentialBuilder withAccount(Account account) {
            this.account = account;
            return this;
        }

        public Credential build() {
            Credential credential = new Credential();
            credential.setTokenKey(tokenKey);
            credential.setCreatedAt(createdAt);
            credential.setExpiredAt(expiredAt);
            credential.setAccount(account);
            return credential;
        }
    }
}
