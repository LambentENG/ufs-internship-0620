package ru.philit.ufs.web.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Объект пользователя.
 */
@EqualsAndHashCode(of = {"id"})
@ToString
@Getter
@Setter
@SuppressWarnings("serial")
public class CashOrderDto implements Serializable {
  /**
   * Идентификатор касового ордера.
   */
  private String id;
  /**
   * Тип операции.
   */
  private String cashOrderType;
  /**
   * Статус.
   */
  private String cashOrderStatus;
  /**
   * Тип опрерации.
   */
  private String operationType;
  /**
   * Расположение оператора.
   */
  private String operatorPosition;
  /**
   * Тип валюты.
   */
  private String currencyType;
  /**
   * Сумма.
   */
  private BigDecimal amount;
  /**
   * Логин пользователя.
   */
  private String userLogin;
  /**
   * Полное имя пользователя.
   */
  private String userFullName;
  /**
   * Дата создания.
   */
  private String createdDttm;
  /**
   * Банк отправитель.
   */
  private String senderBank;
  /**
   * Принимающий банк.
   */
  private String recipientBank;

}
