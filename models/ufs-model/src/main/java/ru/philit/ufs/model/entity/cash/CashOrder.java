package ru.philit.ufs.model.entity.cash;

import lombok.*;
import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.user.Subbranch;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.List;

/**
 * Кассовый ордер операции.
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
@Getter
@Setter
public class CashOrder extends ExternalEntity {
    private String workPlaceUId;
    private String cashOrderId;
    private String userLogin;
    private HeaderInfoType headerInfo;
    private BigDecimal amount;
    private CashOrderStatusType cashOrderStatus;
    private CashOrder additionalInfo;
    private CashOrder cashSymbols;
    private Subbranch subbranch;

    //SrvCheckOverLimitRq
    private SrvCheckOverLimitRq.SrvCheckOverLimitRqMessage srvCheckOverLimitRqMessage;
    private boolean tobeIncreased;

    //SrvCreateCashOrderRq.. some String types changed to Subbranch ones
    private SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage srvCreateCashOrderRqMessage;
    private OperTypeLabel operationType;
    private String cashOrderINum;
    private String accountId;
    private String amountInWords;
    private String currencyType;
    private SrvCreateCashOrderRq repData;
    private String comment;
    private Subbranch subbranchCode;
    private Subbranch vspCode;
    private Subbranch osbCode;
    private Subbranch gosbCode;
    private Subbranch tbCode;
    private String account20202Num;
    private List<CashOrder> cashSymbolItem;
    private String cashSymbol;
    private BigDecimal cashSymbolAmount;
    private String repID;
    private String repFIO;
    private String address;
    private XMLGregorianCalendar dateOfBirth;
    private List<SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.RepData.IdentityDocumentType> identityDocumentType;
    private String placeOfBirth;
    private boolean resident;
    private Subbranch inn;
    private IDDtype value;
    private String series;
    private String number;
    private String issuedBy;
    private XMLGregorianCalendar issuedDate;

    //SrvGetWorkPlaceInfoRq
    private SrvGetWorkPlaceInfoRq.SrvGetWorkPlaceInfoRqMessage srvGetWorkPlaceInfoRqMessage;

    //SrvUpdStCashOrderRq
    private SrvUpdStCashOrderRq.SrvUpdCashOrderRqMessage srvUpdCashOrderRqMessage;
}
