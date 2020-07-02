package ru.philit.ufs.model.entity.cash;

/**
 * Статус кассового ордера.
 */
public enum CashOrderStatus {
  CREATED("Created"),
  COMMITTED("Committed");
  private final String value;

  CashOrderStatus(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  /**
   * Метод возвращающий статус кассового ордера.
   */
  public static CashOrderStatus fromValue(String v) {
    for (CashOrderStatus c : CashOrderStatus.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}