package com.gignomai.tddinsideout.domain.port.secondary;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountPersistenceService {
    BigDecimal getAccountFromIban(String iban);
}
