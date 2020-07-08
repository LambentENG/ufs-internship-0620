package ru.philit.ufs.model.entity.cash;

import lombok.Getter;

/**
 * Статус лимита Кассового Ордера.
 */
public enum LimitStatus {
  LIMIT_ERROR("Limit error"),
  LIMIT_PASSED("Limit passed");

  @Getter
  private final String value;

  LimitStatus(String v) {
    this.value = v;
  }

  public String value() {
    return name();
  }

  public static LimitStatus fromValue(String v) {
    return valueOf(v);
  }
}
