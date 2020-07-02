package ru.philit.ufs.model.converter.esb.asfs;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.cash.CashOrderStatus;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;

@Mapper
public abstract class CashOrderByMapStructAdapter extends AsfsAdapter {

  public static final CashOrderStatus cashOrderStatus = Mappers
      .getMapper(CashOrderStatus.class);

  public static final CashOrderByMapStructAdapter cashOrderByMapStructAdapter = Mappers
      .getMapper(CashOrderByMapStructAdapter.class);

  //******** Mappers *******
  @Mappings({
      @Mapping(expression = "java(headerInfo())",
          target = "headerInfo"),
      @Mapping(source = "cashOrderId",
          target = "srvCreateCashOrderRqMessage.cashOrderId")
  })

  public abstract SrvCreateCashOrderRq createCashOrderRq(CashOrder cashOrder);

  @Mappings({
      @Mapping(source = "headerInfo.rqUID",
          target = "requestUid"),
      @Mapping(expression = "java(new java.util.Date())",
          target = "receiveDate"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.responseCode",
          target = "responseCode"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.responseMsg",
          target = "responseMsg"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.cashOrderId",
          target = "cashOrderId"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.cashOrderINum",
          target = "cashOrderINum"),
      //  @Mapping(source = "srvCreateCashOrderRsMessage.KO1.cashOrderStatus",
      //      target = "cashOrderStatus"),
      //  @Mapping(source = "srvCreateCashOrderRsMessage.KO1.cashOrderType",
      //      target = "cashOrderType"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.createdDttm",
          target = "createdDttm"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.operationId",
          target = "operationId"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.repFIO",
          target = "repFio"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.legalEntityShortName",
          target = "legalEntityShortName"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.amount",
          target = "amount"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.accountId",
          target = "accountId"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.senderBank",
          target = "senderBank"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.senderBankBIC",
          target = "senderBankBic"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.recipientBank",
          target = "recipientBank"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.recipientBankBIC",
          target = "recipientBankBic"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.clientTypeFK",
          target = "clientTypeFk"),
      //  @Mapping(source = "srvCreateCashOrderRsMessage.KO1.fDestLEName",
      //     target = "fdestLeName"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.operatorPosition",
          target = "operatorPosition"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.userFullName",
          target = "userFullName"),
      @Mapping(source = "srvCreateCashOrderRsMessage.KO1.userPosition",
          target = "userPosition"),
      //   @Mapping(source = "srvCreateCashOrderRsMessage.KO1.cashSymbols",
      //      target = "cashSymbol"),
      })

  public abstract CashOrder convert(SrvCreateCashOrderRs response);
  /*
  public abstract SrvUpdStCashOrderRq srvUpdStCashOrderRq(CashOrder cashOrder);

  @Mappings({
      @Mapping(source = "headerInfo.rqUID",
          target = "requestUid"),
      @Mapping(expression = "java(new java.util.Date())",
          target = "receiveDate"),
      @Mapping(source = "srvUpdCashOrderRsMessage.responseCode",
          target = "responseCode"),
      @Mapping(source = "srvUpdCashOrderRsMessage.responseMsg",
          target = "responseMsg"),
      @Mapping(source = "srvUpdCashOrderRsMessage.cashOrderId",
          target = "cashOrderId"),
      @Mapping(source = "srvUpdCashOrderRsMessage.cashOrderINum",
          target = "cashOrderINum"),
      //  @Mapping(source = "srvUpdCashOrderRsMessage.cashOrderStatus",
      //      target = "cashOrderStatus"),
      //  @Mapping(source = "srvUpdCashOrderRsMessage.cashOrderType",
      //      target = "cashOrderType"),
      })

  public abstract CashOrder convert(SrvUpdStCashOrderRs response);

 */
}