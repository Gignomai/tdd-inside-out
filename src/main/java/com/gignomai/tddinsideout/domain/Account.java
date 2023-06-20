package com.gignomai.tddinsideout.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Account {
    private BigDecimal balance;

    public Account() {
        balance = BigDecimal.ZERO;
    }

    public void fund(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
