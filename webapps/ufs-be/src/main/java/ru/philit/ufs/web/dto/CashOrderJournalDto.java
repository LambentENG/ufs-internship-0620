package ru.philit.ufs.web.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Объект записи журнала кассового ордера.
 */
@ToString
@Getter
@Setter
@SuppressWarnings("serial")
public class CashOrderJournalDto implements Serializable {

  /**
   * Кассовый ордер.
   */
  private CashOrderDto cashOrder;

  /**
   * Добавление информации о кассовом ордере.
   */
  public CashOrderJournalDto withCashOrder(CashOrderDto cashOrder) {
    setCashOrder(cashOrder);
    return this;
  }
}
