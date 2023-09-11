package org.delivery.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {
    REGISTERED("Registered"),
    UNREGISTERED("Unregistered"),

    ;
    private String description;
}
