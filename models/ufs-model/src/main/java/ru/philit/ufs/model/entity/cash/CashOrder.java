package ru.philit.ufs.model.entity.cash;

import lombok.*;
import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.user.Subbranch;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

/**
 * Кассовый ордер операции.
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
@Getter
@Setter
public class CashOrder extends ExternalEntity {

    private String cashOrderId;
    private String cashOrderINum;
    private String accountId;
    private CashOrderStatusType cashOrderStatus;
    private BigDecimal amount;

    //SrvCreateCashOrderRqMessage
    //SrvUpdCashOrderRqMessage
    private OperTypeLabel operationType;
    private String amountInWords;
    private String currencyType;
    private String workPlaceUId;

    //SrvCreateCashOrderRsMessage (ko1)
    //SrvUpdCashOrderRsMessage
    private String responseCode;
    private String responseMsg;
    private CashOrderType cashOrderType;
    private XMLGregorianCalendar createdDttm;
    private String operationId;
    private String repFIO;
    private String legalEntityShortName;

    private String senderBank;
    private String senderBankBIC;
    private String recipientBank;
    private String recipientBankBIC;
    private Boolean clientTypeFK;
    private String fDestLEName;
    private String operatorPosition;
    private String userFullName;
    private String userPosition;

    //AdditionalInfo
    private String comment;
    private String account20202Num;
    private String userLogin;
    private Subbranch subbranch;
}
