package ru.philit.ufs.model.entity.cash;

import lombok.*;
import ru.philit.ufs.model.entity.common.ExternalEntity;
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

    protected String cashOrderId;
    protected String cashOrderINum;
    protected String accountId;
    protected CashOrderStatusType cashOrderStatus;
    protected BigDecimal amount;

    //SrvCreateCashOrderRqMessage
    //SrvUpdCashOrderRqMessage
    protected OperTypeLabel operationType;
    protected String amountInWords;
    protected String currencyType;
    protected String workPlaceUId;

    //SrvCreateCashOrderRsMessage (ko1)
    //SrvUpdCashOrderRsMessage
    protected String responseCode;
    protected String responseMsg;
    protected CashOrderType cashOrderType;
    protected XMLGregorianCalendar createdDttm;
    protected String operationId;
    protected String repFIO;
    protected String legalEntityShortName;
    protected String inn;
    protected String senderBank;
    protected String senderBankBIC;
    protected String recipientBank;
    protected String recipientBankBIC;
    protected Boolean clientTypeFK;
    protected String fDestLEName;
    protected String operatorPosition;
    protected String userFullName;
    protected String userPosition;

    //AdditionalInfo
    protected String comment;
    protected String subbranchCode;
    protected String vspCode;
    protected String osbCode;
    protected String gosbCode;
    protected String tbCode;
    protected String account20202Num;
    protected String userLogin;
}
