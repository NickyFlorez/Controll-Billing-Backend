package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter;

import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.AccountDbo;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class AccountJpaSpecifications {

    private AccountJpaSpecifications() {
    }

    static Specification<@NonNull AccountDbo> byUserId(UUID userId){
        return (root, query, cb) ->
                cb.equal(root.get("user").get("id"), userId);
    }

    static Specification<@NonNull AccountDbo> byBank(String bank){
        return (root, query, cb) ->
                bank == null ? null : cb.equal(root.get("bank"), bank);
    }
}
