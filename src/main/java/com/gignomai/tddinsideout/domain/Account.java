package com.gignomai.tddinsideout.domain;

import lombok.Getter;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Account {
    private BigDecimal balance;
    private final List<BigDecimal> movements;

    public Account() {
        balance = BigDecimal.ZERO;
        movements = new ArrayList<>();
    }

    public Account(BigDecimal initialAmount) {
        balance = initialAmount;
        movements = new ArrayList<>();
        movements.add(initialAmount);
    }

    public void fund(BigDecimal amount) throws OperationNotSupportedException {
        if (amount.signum() > -1) {
            balance = balance.add(amount);
            movements.add(amount);
        } else {
            throw new OperationNotSupportedException("Negative amounts can't be funded.");
        }
    }

    public void withdraw(BigDecimal amount) throws OperationNotSupportedException {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            movements.add(amount.negate());
        } else {
            throw new OperationNotSupportedException("Balance cannot be lower than withdraw amount.");
        }
    }
}
