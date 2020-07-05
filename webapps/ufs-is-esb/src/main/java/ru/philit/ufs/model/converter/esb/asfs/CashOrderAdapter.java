package ru.philit.ufs.model.converter.esb.asfs;

import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.cash.CashOrderStatus;
import ru.philit.ufs.model.entity.cash.CashOrderType;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq.SrvUpdCashOrderRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs;

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

  //******** Mappers *******

  private static void map(SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1 message,
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
    map(message.getCashSymbols(), cashOrder);
  }

  private static void map(
      SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1.CashSymbols cashSymbols,
      CashOrder cashOrder) {
    cashOrder.getCashSymbol().setCode(cashSymbols.getCashSymbolItem().toString());
  }

  protected static void map(
      SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1
          .CashSymbols.CashSymbolItem cashSymbolItem,
      CashOrder cashOrder) {
    cashOrder.getCashSymbol().setCode(cashSymbolItem.getCashSymbol());
    cashOrder.getCashSymbol().setAmount(cashSymbolItem.getCashSymbolAmount());
    cashOrder.getCashSymbol().setDescription(cashSymbolItem.getCashSymbol());
  }

  private static void map(
      SrvUpdStCashOrderRs.SrvUpdCashOrderRsMessage message,
      CashOrder cashOrder) {
    cashOrder.setResponseCode(message.getResponseCode());
    cashOrder.setResponseMsg(message.getResponseMsg());
    cashOrder.setCashOrderId(message.getCashOrderId());
    cashOrder.setCashOrderINum(message.getCashOrderINum());
    cashOrder.setCashOrderStatus(cashOrderStatus(message.getCashOrderStatus()));
    cashOrder.setCashOrderType(cashOrderType(message.getCashOrderType().value()));
  }

  private static void map(CashOrder cashOrder, SrvCreateCashOrderRqMessage message) {
    message.setCashOrderId(cashOrder.getCashOrderId());
    message.setCashOrderINum(cashOrder.getCashOrderINum());
    message.setAccountId(cashOrder.getAccountId());
    message.setAmount(cashOrder.getAmount());
    message.setAmountInWords(cashOrder.getAmountInWords());
    message.setCurrencyType(cashOrder.getCurrencyType());
    message.setCashOrderStatus(cashOrderStatusType(cashOrder.getCashOrderStatus().value()));
    message.setWorkPlaceUId(cashOrder.getWorkPlaceUId());
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
