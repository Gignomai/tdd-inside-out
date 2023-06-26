package com.gignomai.tddinsideout.domain.port.secondary;

import com.gignomai.tddinsideout.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AccountPersistenceServiceTest {

    @Test
    void givenAnAccountIbanShouldReturnItsBalance() {
        String iban = "TEST";

        Account account = new Account();
        AccountPersistenceService accountPersistenceService = mock(AccountPersistenceService.class);

        BigDecimal balance = accountPersistenceService.getAccountFromIban(iban);

        assertThat(balance).isEqualTo(account.getBalance());
    }
}