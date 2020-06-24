package ru.philit.ufs.model.entity.cash;

public enum CashOrderStatusType {
        CREATED("Created"),
        COMMITTED("Committed");
        private final String value;

        CashOrderStatusType(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static CashOrderStatusType fromValue(String v) {
            for (CashOrderStatusType c: CashOrderStatusType.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
}
