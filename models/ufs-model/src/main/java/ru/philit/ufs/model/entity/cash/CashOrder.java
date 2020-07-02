package ru.philit.ufs.model.entity.cash;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.oper.CashSymbol;
import ru.philit.ufs.model.entity.user.Subbranch;

/**
 * Кассовый ордер операции.
 */
@ToString
@Getter
@Setter
public class CashOrder extends ExternalEntity {

  private String cashOrderId;
  private String cashOrderINum;
  private String accountId;
  private CashOrderStatus cashOrderStatus;
  private BigDecimal amount;

  //SrvCreateCashOrderRqMessage
  //SrvUpdCashOrderRqMessage
  private String amountInWords;
  private String currencyType;
  private String workPlaceUId;

  //SrvCreateCashOrderRsMessage (ko1)
  //SrvUpdCashOrderRsMessage
  private String responseCode;
  private String responseMsg;
  private CashOrderType cashOrderType;
  private Date createdDttm;
  private String operationId;
  private String repFio;
  private String legalEntityShortName;
  private String senderBank;
  private String senderBankBic;
  private String recipientBank;
  private String recipientBankBic;
  private Boolean clientTypeFk;
  private String fdestLeName;
  private String operatorPosition;
  private String userFullName;
  private String userPosition;
  private CashSymbol cashSymbol;

  //AdditionalInfo
  private String comment;
  private String account20202Num;
  private String userLogin;
  private Subbranch subbranch;

  /**
   * Конструктор Кассового ордера.
   */
  public CashOrder() {
    this.subbranch = new Subbranch();
    this.cashSymbol = new CashSymbol();
  }
}
