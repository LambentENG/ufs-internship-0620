package ru.philit.ufs.model.entity.cash;

/**
 * Типы операций.
 */
public enum OperTypeLabel {
  TO_CARD_DEPOSIT("ToCardDeposit"),
  FROM_CARD_WITHDRAW("FromCardWithdraw"),
  TO_ACCOUNT_DEPOSIT_RUB("ToAccountDepositRub"),
  FROM_ACCOUNT_WITHDRAW_RUB("FromAccountWithdrawRub"),
  CHECKBOOK_ISSUING("CheckbookIssuing");
  private final String value;

  OperTypeLabel(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  /**
   * Метод возвращающий типы операций.
   */
  public static OperTypeLabel fromValue(String v) {
    for (OperTypeLabel c : OperTypeLabel.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
