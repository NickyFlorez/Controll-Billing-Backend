package com.nicky.controlBilling.domain.model;

import java.util.UUID;

public record AccountFilter(
        UUID userId,
        String bank
) {

}
