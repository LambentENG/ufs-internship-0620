package ru.philit.ufs.model.entity.cash;

/**
 * Тип кассового ордера.
 */
public enum CashOrderType {
  KO_1("KO1"),
  KO_2("KO2");
  private final String value;

  CashOrderType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  /**
   * Метод возвращающий тип кассового ордера.
   */
  public static CashOrderType fromValue(String v) {
    for (CashOrderType c : CashOrderType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
