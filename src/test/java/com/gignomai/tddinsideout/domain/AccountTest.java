package com.gignomai.tddinsideout.domain;

import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {

    @Test
    void balanceIsZeroWhenAccountIsNewAndNoAmountIsAssigned() {
        Account account = new Account();

        assertThat(account.getBalance()).isZero();
    }

    @Test
    void balanceIsTenWhenAccountIsNewAndCreatedWithTen() {
        Account account = new Account(BigDecimal.TEN);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void balanceIsZeroWhenAccountIsEmptyAndFundedZero() throws OperationNotSupportedException {
        Account account = new Account();

        account.fund(BigDecimal.ZERO);

        assertThat(account.getBalance()).isZero();
    }

    @Test
    void balanceIsTenWhenAccountIsEmptyAndFundedTen() throws OperationNotSupportedException {
        Account account = new Account();

        account.fund(BigDecimal.TEN);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void balanceIsTwentyWhenBalanceIsTenAndFundedTen() throws OperationNotSupportedException {
        Account account = new Account(BigDecimal.TEN);

        account.fund(BigDecimal.TEN);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(20L));
    }

    @Test
    void shouldThrowOperationNotSupportedExceptionWhenFundAmountIsNegative() {
        Account account = new Account();

        Throwable exception = assertThrows(OperationNotSupportedException.class, () -> account.fund(BigDecimal.valueOf(-1L)));
        assertThat("Negative amounts can't be funded.").isEqualTo(exception.getMessage());
    }

    @Test
    void balanceIsZeroWhenHavingZeroWithdrawZero() throws OperationNotSupportedException {
        Account account = new Account();

        account.withdraw(BigDecimal.ZERO);

        assertThat(account.getBalance()).isZero();
    }

    @Test
    void balanceIsZeroWhenHavingTenWithdrawTen() throws OperationNotSupportedException {
        Account account = new Account(BigDecimal.TEN);

        account.withdraw(BigDecimal.TEN);

        assertThat(account.getBalance()).isZero();
    }

    @Test
    void balanceIsTenWhenHavingTwentyWithdrawTen() throws OperationNotSupportedException {
        Account account = new Account(BigDecimal.valueOf(20L));

        account.withdraw(BigDecimal.TEN);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void shouldThrowOperationNotSupportedExceptionWhenBalanceIsLowerThanWithdrawAmount() {
        Account account = new Account();

        Throwable throwable = assertThrows(OperationNotSupportedException.class, () -> account.withdraw(BigDecimal.TEN));

        assertThat("Balance cannot be lower than withdraw amount.").isEqualTo(throwable.getMessage());
    }

    @Test
    void shouldNotReturnAccountMovementsIfThereIsNoActivity() {
        Account account = new Account();

        List<BigDecimal> movements = account.getMovements();

        assertThat(movements.size()).isEqualTo(0);
    }

    @Test
    void shouldReturnOneAccountMovementWithValueTenWhenAccountIsCreatedWithTen() {
        Account account = new Account(BigDecimal.TEN);

        List<BigDecimal> movements = account.getMovements();

        assertThat(movements.size()).isEqualTo(1);
        assertThat(movements.get(0)).isEqualTo(BigDecimal.TEN);
    }

    @Test
    void shouldReturnTwoAccountMovementsWithValueTenWhenAccountIsEmptyAndFundedTenTwice() throws OperationNotSupportedException {
        Account account = new Account();

        account.fund(BigDecimal.TEN);
        account.fund(BigDecimal.TEN);

        List<BigDecimal> movements = account.getMovements();

        assertThat(movements.size()).isEqualTo(2);
        movements.forEach(m -> assertThat(m).isEqualTo(BigDecimal.TEN));
    }

    @Test
    void shouldReturnTwoAccountMovementsWithTotalValueOfZeroWhenAccountCreatedWithTenAndWithdrawTen() throws OperationNotSupportedException {
        Account account = new Account(BigDecimal.TEN);

        account.withdraw(BigDecimal.TEN);

        List<BigDecimal> movements = account.getMovements();

        assertThat(movements.size()).isEqualTo(2);
        BigDecimal total = movements.stream()
                .reduce(BigDecimal::add)
                .orElseThrow();
        assertThat(total).isEqualTo(BigDecimal.ZERO);
    }
}
