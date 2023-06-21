package com.gignomai.tddinsideout.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final BigDecimal TEST_FUND_AMOUNT_TEN = BigDecimal.TEN;

    @Test
    void balanceHasValueOfZeroWhenAccountIsNew() {
        Account account = new Account();

        assertThat(account.getBalance()).isZero();
    }

    @Test
    void balanceHasValueOfZeroWhenAccountIsNewAndFundedZero() {
        Account account = new Account();

        account.fund(BigDecimal.ZERO);

        assertThat(account.getBalance()).isZero();
    }

    @Test
    void balanceHasValueOfTenWhenAccountIsFundedTen() {
        Account account = new Account();

        account.fund(TEST_FUND_AMOUNT_TEN);

        assertThat(account.getBalance()).isEqualTo(TEST_FUND_AMOUNT_TEN);
    }

    @Test
    void balanceHasValueOfThirtyWhenAccountIsFoundedThreeTimesTen() {
        Account account = new Account();

        account.fund(TEST_FUND_AMOUNT_TEN);
        account.fund(TEST_FUND_AMOUNT_TEN);
        account.fund(TEST_FUND_AMOUNT_TEN);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(30L));
    }
}
