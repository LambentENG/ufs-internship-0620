package ru.philit.ufs.model.converter.esb.asfs;

import ru.philit.ufs.model.entity.account.IdentityDocument;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.cash.CashOrderStatus;
import ru.philit.ufs.model.entity.cash.CashOrderType;
import ru.philit.ufs.model.entity.common.OperationTypeCode;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.IDDtype;
import ru.philit.ufs.model.entity.esb.asfs.OperTypeLabel;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.AdditionalInfo;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.RepData;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.RepData.IdentityDocumentType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1.CashSymbols;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq.SrvUpdCashOrderRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs.SrvUpdCashOrderRsMessage;
import ru.philit.ufs.model.entity.oper.CashSymbol;

public class CashOrderAdapter extends AsfsAdapter {

  //******** Converters ********

  private static CashOrderStatus cashOrderStatus(CashOrderStatusType cashOrderStatus) {
    return (cashOrderStatus != null) ? CashOrderStatus.fromValue(cashOrderStatus.value()) : null;
  }

  private static CashOrderStatusType cashOrderStatusType(String cashOrderStatus) {
    return (cashOrderStatus != null) ? CashOrderStatusType.fromValue(cashOrderStatus) : null;
  }

  private static CashOrderType cashOrderType(String cashOrder) {
    return (cashOrder != null) ? CashOrderType.fromValue(cashOrder) : null;
  }

  private static OperTypeLabel operTypeLabel(OperationTypeCode operationTypeCode) {
    return (operationTypeCode != null) ? OperTypeLabel.fromValue(operationTypeCode.code()) : null;
  }

  protected static IDDtype iddType(
      ru.philit.ufs.model.entity.account.IdentityDocumentType identityDocumentType) {
    return (identityDocumentType != null) ? IDDtype.fromValue(identityDocumentType.code()) : null;
  }

  //******** Mappers *******

  private static void map(KO1 message,
      CashOrder cashOrder) {
    cashOrder.setResponseCode(message.getResponseCode());
    cashOrder.setResponseMsg(message.getResponseMsg());
    cashOrder.setCashOrderId(message.getCashOrderId());
    cashOrder.setCashOrderINum(message.getCashOrderINum());
    cashOrder.setCashOrderStatus(cashOrderStatus(message.getCashOrderStatus()));
    cashOrder.setCashOrderType(cashOrderType(message.getCashOrderType().value()));
    cashOrder.setCreatedDttm(date(message.getCreatedDttm()));
    cashOrder.setOperationId(message.getOperationId());
    cashOrder.setRepFio(message.getRepFIO());
    cashOrder.setLegalEntityShortName(message.getLegalEntityShortName());
    cashOrder.setAmount(message.getAmount());
    cashOrder.setAccountId(message.getAccountId());
    cashOrder.setSenderBank(message.getSenderBank());
    cashOrder.setSenderBankBic(message.getSenderBankBIC());
    cashOrder.setRecipientBank(message.getRecipientBank());
    cashOrder.setRecipientBankBic(message.getRecipientBankBIC());
    cashOrder.setClientTypeFk(message.isClientTypeFK());
    cashOrder.setFdestLeName(message.getFDestLEName());
    cashOrder.setOperatorPosition(message.getOperatorPosition());
    cashOrder.setUserFullName(message.getUserFullName());
    cashOrder.setUserPosition(message.getUserPosition());
    if (message.getCashSymbols() != null) {
      for (CashSymbols.CashSymbolItem cashSymbolItem:
          message.getCashSymbols().getCashSymbolItem()) {
        CashSymbol cashSymbol = new CashSymbol();
        cashSymbol.setCode(cashSymbolItem.getCashSymbol());
        cashSymbol.setAmount(cashSymbolItem.getCashSymbolAmount());
        cashOrder.getCashSymbols().add(cashSymbol);
      }
    }
  }

  private static void map(CashOrder cashOrder, SrvCreateCashOrderRqMessage message) {
    message.setCashOrderId(cashOrder.getCashOrderId());
    message.setOperationType(operTypeLabel(cashOrder.getOperationType()));
    message.setCashOrderINum(cashOrder.getCashOrderINum());
    message.setAccountId(cashOrder.getAccountId());
    message.setAmount(cashOrder.getAmount());
    message.setAmountInWords(cashOrder.getAmountInWords());
    message.setCurrencyType(cashOrder.getCurrencyType());
    message.setCashOrderStatus(cashOrderStatusType(cashOrder.getCashOrderStatus().value()));
    message.setWorkPlaceUId(cashOrder.getWorkPlaceUId());

    message.setRepData(new RepData());
    message.getRepData().setRepID(cashOrder.getCashOrderId());
    message.getRepData().setRepFIO(cashOrder.getRepFio());
    message.getRepData().setAddress(cashOrder.getRepData().getAddress());
    message.getRepData().setDateOfBirth(xmlCalendar(cashOrder.getRepData().getBirthDate()));
    message.getRepData().setPlaceOfBirth(cashOrder.getRepData().getPlaceOfBirth());
    message.getRepData().setResident(cashOrder.getRepData().isResident());
    message.getRepData().setINN(cashOrder.getRepData().getInn());
    if (cashOrder.getRepData().getIdentityDocuments() != null) {
      for (IdentityDocument document: cashOrder.getRepData().getIdentityDocuments()) {
        IdentityDocumentType documentType = new IdentityDocumentType();
        documentType.setValue(iddType(document.getType()));
        documentType.setSeries(document.getSeries());
        documentType.setNumber(document.getNumber());
        documentType.setIssuedBy(document.getIssuedBy());
        documentType.setIssuedDate(xmlCalendar(document.getIssuedDate()));
        message.getRepData().getIdentityDocumentType().add(documentType);
      }
    }

    message.setAdditionalInfo(new AdditionalInfo());
    message.getAdditionalInfo().setComment(cashOrder.getComment());
    message.getAdditionalInfo().setAccount20202Num(cashOrder.getAccount20202Num());
    message.getAdditionalInfo().setUserLogin(cashOrder.getUserLogin());
    message.getAdditionalInfo().setSubbranchCode(cashOrder.getSubbranch().getSubbranchCode());
    message.getAdditionalInfo().setVSPCode(cashOrder.getSubbranch().getVspCode());
    message.getAdditionalInfo().setOSBCode(cashOrder.getSubbranch().getOsbCode());
    message.getAdditionalInfo().setGOSBCode(cashOrder.getSubbranch().getGosbCode());
    message.getAdditionalInfo().setTBCode(cashOrder.getSubbranch().getTbCode());
    if (cashOrder.getCashSymbols() != null) {
      AdditionalInfo.CashSymbols cashSymbols = new AdditionalInfo.CashSymbols();
      for (CashSymbol cashSymbol: cashOrder.getCashSymbols()) {
        SrvCreateCashOrderRqMessage.AdditionalInfo.CashSymbols.CashSymbolItem cashSymbolItem =
            new AdditionalInfo.CashSymbols.CashSymbolItem();
        cashSymbolItem.setCashSymbol(cashSymbol.getCode());
        cashSymbolItem.setCashSymbolAmount(cashSymbol.getAmount());
        cashSymbols.getCashSymbolItem().add(cashSymbolItem);
        message.getAdditionalInfo().setCashSymbols(cashSymbols);
      }
    }
  }

  private static void map(SrvUpdCashOrderRsMessage message,
      CashOrder cashOrder) {
    cashOrder.setResponseCode(message.getResponseCode());
    cashOrder.setResponseMsg(message.getResponseMsg());
    cashOrder.setCashOrderId(message.getCashOrderId());
    cashOrder.setCashOrderINum(message.getCashOrderINum());
    cashOrder.setCashOrderStatus(cashOrderStatus(message.getCashOrderStatus()));
    cashOrder.setCashOrderType(cashOrderType(message.getCashOrderType().value()));
  }

  private static void map(CashOrder cashOrder, SrvUpdCashOrderRqMessage message) {
    message.setCashOrderId(cashOrder.getCashOrderId());
    message.setCashOrderStatus(cashOrderStatusType(cashOrder.getCashOrderStatus().value()));
  }

  //******** Methods *******

  /**
   * Возвращает объект запроса создания кассового ордера.
   */
  public static SrvCreateCashOrderRq createCashOrderRq(CashOrder cashOrder) {
    SrvCreateCashOrderRq request = new SrvCreateCashOrderRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvCreateCashOrderRqMessage(new SrvCreateCashOrderRqMessage());
    map(cashOrder, request.getSrvCreateCashOrderRqMessage());
    return request;
  }

  /**
   * Возвращает объект запроса обновления кассового ордера.
   */
  public static SrvUpdStCashOrderRq updStCashOrderRq(CashOrder cashOrder) {
    SrvUpdStCashOrderRq request = new SrvUpdStCashOrderRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvUpdCashOrderRqMessage(new SrvUpdCashOrderRqMessage());
    map(cashOrder, request.getSrvUpdCashOrderRqMessage());
    return request;
  }

  /**
   * Преобразует транспортный объект кассового ордера во внутреннюю сущность.
   */
  public static CashOrder convert(SrvCreateCashOrderRs response) {
    CashOrder cashOrder = new CashOrder();
    map(response.getHeaderInfo(), cashOrder);
    map(response.getSrvCreateCashOrderRsMessage().getKO1(), cashOrder);
    return cashOrder;
  }

  /**
   * Преобразует транспортный объект кассового ордера во внутреннюю сущность.
   */
  public static CashOrder convert(SrvUpdStCashOrderRs response) {
    CashOrder cashOrder = new CashOrder();
    map(response.getHeaderInfo(), cashOrder);
    map(response.getSrvUpdCashOrderRsMessage(), cashOrder);
    return cashOrder;
  }
}
