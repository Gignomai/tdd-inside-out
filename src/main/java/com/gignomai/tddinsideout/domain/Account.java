package com.gignomai.tddinsideout.domain;

import lombok.Getter;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;

@Getter
public class Account {
    private BigDecimal balance;

    public Account() {
        balance = BigDecimal.ZERO;
    }

    public Account(BigDecimal initialAmount) {
        balance = initialAmount;
    }

    public void fund(BigDecimal amount) throws OperationNotSupportedException {
        if (amount.signum() > -1) {
            balance = balance.add(amount);
        } else {
            throw new OperationNotSupportedException("Negative amounts can't be funded.");
        }
    }

    public void withdraw(BigDecimal amount) throws OperationNotSupportedException {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        } else {
            throw new OperationNotSupportedException("Balance cannot be lower than withdraw amount.");
        }
    }
}
