package com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.adapter;

import com.nicky.controlBilling.domain.model.Month;
import com.nicky.controlBilling.domain.model.TransactionType;
import com.nicky.controlBilling.infrastructure.driven_adapters.postgresql.jpa.entity.TransactionDbo;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TransactionJpaSpecifications {

    private TransactionJpaSpecifications() {}

    static Specification<@NonNull TransactionDbo> byUserId(UUID userId) {
        return (root, query, cb) ->
                cb.equal(root.get("user").get("id"), userId);
    }

    static Specification<@NonNull TransactionDbo> byType(TransactionType type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("type"), type);
    }

    static Specification<@NonNull TransactionDbo> byMonth(Month month) {
        return (root, query, cb) -> {
            if (month == null) return null;

            return cb.equal(root.get("month"), month);
        };
    }
}
