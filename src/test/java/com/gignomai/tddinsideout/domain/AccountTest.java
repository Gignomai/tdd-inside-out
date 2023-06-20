package com.gignomai.tddinsideout.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final BigDecimal TEST_FUND_AMOUNT = BigDecimal.TEN;

    @Test
    void shouldNotChangeTheAccountBalanceWhenFundingZeroToAnEmptyAccount() {
        Account account = new Account();

        account.fund(BigDecimal.ZERO);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void shouldAddMoneyToAnEmptyAccountAndBalanceHasTheSameAmount() {
        Account account = new Account();

        account.fund(TEST_FUND_AMOUNT);

        assertThat(account.getBalance()).isEqualTo(TEST_FUND_AMOUNT);
    }

    @Test
    void shouldAddMoneyMultipleTimesToAnAccountAndBalanceEqualsTotalAdds() {
        Account account = new Account();

        account.fund(TEST_FUND_AMOUNT);
        account.fund(TEST_FUND_AMOUNT);
        account.fund(TEST_FUND_AMOUNT);

        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(30L));
    }
}
