package ru.philit.ufs.model.converter.esb.asfs;

import java.util.Date;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import org.mapstruct.factory.Mappers;
import ru.philit.ufs.model.entity.account.IdentityDocument;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderType;
import ru.philit.ufs.model.entity.esb.asfs.IDDtype;
import ru.philit.ufs.model.entity.esb.asfs.OperTypeLabel;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.AdditionalInfo.CashSymbols;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.RepData;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.RepData.IdentityDocumentType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs;
import ru.philit.ufs.model.entity.oper.CashSymbol;

@Mapper(componentModel = "spring", imports = {Date.class})
public abstract class CashOrderMapper extends AsfsAdapter {

  public static final CashOrderMapper INSTANCE = Mappers.getMapper(CashOrderMapper.class);

  public abstract CashOrderStatusType
      map(ru.philit.ufs.model.entity.cash.CashOrderStatus value);

  public abstract CashOrderType
      map(ru.philit.ufs.model.entity.cash.CashOrderType value);

  public abstract CashSymbols
      map(ru.philit.ufs.model.entity.oper.CashSymbol value);

  public abstract OperTypeLabel
      map(ru.philit.ufs.model.entity.common.OperationTypeCode value);

  public abstract RepData
      map(ru.philit.ufs.model.entity.account.Representative value);

  public abstract ru.philit.ufs.model.entity.cash.CashOrderStatus
      map(ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType value);

  public abstract ru.philit.ufs.model.entity.cash.CashOrderType
      map(ru.philit.ufs.model.entity.esb.asfs.CashOrderType value);

  protected static IDDtype iddType(
      ru.philit.ufs.model.entity.account.IdentityDocumentType identityDocumentType) {
    return (identityDocumentType != null) ? IDDtype.fromValue(identityDocumentType.code()) : null;
  }

  //Mapping to "createCashOrder" DTO entities and sub-entities

  @Mappings({
      @Mapping(target = "requestUid", source = "headerInfo.rqUID"),
      @Mapping(target = "receiveDate", expression = "java(new Date())"),
      @Mapping(target = "responseCode",
          source = "srvCreateCashOrderRsMessage.KO1.responseCode"),
      @Mapping(target = "responseMsg",
          source = "srvCreateCashOrderRsMessage.KO1.responseMsg"),
      @Mapping(target = "cashOrderId",
          source = "srvCreateCashOrderRsMessage.KO1.cashOrderId"),
      @Mapping(target = "cashOrderINum",
          source = "srvCreateCashOrderRsMessage.KO1.cashOrderINum"),
      @Mapping(target = "cashOrderStatus",
          source = "srvCreateCashOrderRsMessage.KO1.cashOrderStatus"),
      @Mapping(target = "cashOrderType",
          source = "srvCreateCashOrderRsMessage.KO1.cashOrderType"),
      @Mapping(target = "createdDttm",
          source = "srvCreateCashOrderRsMessage.KO1.createdDttm"),
      @Mapping(target = "operationId",
          source = "srvCreateCashOrderRsMessage.KO1.operationId"),
      @Mapping(target = "repFio",
          source = "srvCreateCashOrderRsMessage.KO1.repFIO"),
      @Mapping(target = "legalEntityShortName",
          source = "srvCreateCashOrderRsMessage.KO1.legalEntityShortName"),
      @Mapping(target = "amount",
          source = "srvCreateCashOrderRsMessage.KO1.amount"),
      @Mapping(target = "accountId",
          source = "srvCreateCashOrderRsMessage.KO1.accountId"),
      @Mapping(target = "senderBank",
          source = "srvCreateCashOrderRsMessage.KO1.senderBank"),
      @Mapping(target = "senderBankBic",
          source = "srvCreateCashOrderRsMessage.KO1.senderBankBIC"),
      @Mapping(target = "recipientBank",
          source = "srvCreateCashOrderRsMessage.KO1.recipientBank"),
      @Mapping(target = "recipientBankBic",
          source = "srvCreateCashOrderRsMessage.KO1.recipientBankBIC"),
      @Mapping(target = "clientTypeFk",
          source = "srvCreateCashOrderRsMessage.KO1.clientTypeFK"),
      @Mapping(target = "fdestLeName",
          source = "srvCreateCashOrderRsMessage.KO1.FDestLEName"),
      @Mapping(target = "operatorPosition",
          source = "srvCreateCashOrderRsMessage.KO1.operatorPosition"),
      @Mapping(target = "userFullName",
          source = "srvCreateCashOrderRsMessage.KO1.userFullName"),
      @Mapping(target = "userPosition",
          source = "srvCreateCashOrderRsMessage.KO1.userPosition"),
      @Mapping(target = "cashSymbols",
          source = "srvCreateCashOrderRsMessage.KO1.cashSymbols.cashSymbolItem"),
  })
  public abstract CashOrder convert(SrvCreateCashOrderRs response);

  //Mapping to "updateStCashOrder" DTO entities

  @Mappings({
      @Mapping(target = "requestUid", source = "headerInfo.rqUID"),
      @Mapping(target = "receiveDate", expression = "java(new Date())"),
      @Mapping(target = "responseCode",
          source = "srvUpdCashOrderRsMessage.responseCode"),
      @Mapping(target = "responseMsg",
          source = "srvUpdCashOrderRsMessage.responseMsg"),
      @Mapping(target = "cashOrderId",
          source = "srvUpdCashOrderRsMessage.cashOrderId"),
      @Mapping(target = "cashOrderINum",
          source = "srvUpdCashOrderRsMessage.cashOrderINum"),
      @Mapping(target = "cashOrderStatus",
          source = "srvUpdCashOrderRsMessage.cashOrderStatus"),
      @Mapping(target = "cashOrderType",
          source = "srvUpdCashOrderRsMessage.cashOrderType")
  })
  public abstract CashOrder convert(SrvUpdStCashOrderRs response);

  @Mappings({
      @Mapping(source = "cashOrderId",
          target = "srvCreateCashOrderRqMessage.cashOrderId"),
      @Mapping(source = "operationType",
          target = "srvCreateCashOrderRqMessage.operationType"),
      @Mapping(source = "cashOrderINum",
          target = "srvCreateCashOrderRqMessage.cashOrderINum"),
      @Mapping(source = "accountId",
          target = "srvCreateCashOrderRqMessage.accountId"),
      @Mapping(source = "amount",
          target = "srvCreateCashOrderRqMessage.amount"),
      @Mapping(source = "amountInWords",
          target = "srvCreateCashOrderRqMessage.amountInWords"),
      @Mapping(source = "currencyType",
          target = "srvCreateCashOrderRqMessage.currencyType"),
      @Mapping(source = "cashOrderStatus",
          target = "srvCreateCashOrderRqMessage.cashOrderStatus"),
      @Mapping(source = "workPlaceUId",
          target = "srvCreateCashOrderRqMessage.workPlaceUId"),
      @Mapping(source = "repData.id",
          target = "srvCreateCashOrderRqMessage.repData.repID"),
      @Mapping(source = "repFio",
          target = "srvCreateCashOrderRqMessage.repData.repFIO"),
      @Mapping(source = "repData.address",
          target = "srvCreateCashOrderRqMessage.repData.address"),
      @Mapping(source = "repData.birthDate",
          target = "srvCreateCashOrderRqMessage.repData.dateOfBirth"),
      @Mapping(source = "repData.identityDocuments",
          target = "srvCreateCashOrderRqMessage.repData.identityDocumentType"),
      @Mapping(source = "repData.placeOfBirth",
          target = "srvCreateCashOrderRqMessage.repData.placeOfBirth"),
      @Mapping(source = "repData.resident",
          target = "srvCreateCashOrderRqMessage.repData.resident"),
      @Mapping(source = "repData.inn",
          target = "srvCreateCashOrderRqMessage.repData.INN"),
      @Mapping(source = "comment",
          target = "srvCreateCashOrderRqMessage.additionalInfo.comment"),
      @Mapping(source = "account20202Num",
          target = "srvCreateCashOrderRqMessage.additionalInfo.account20202Num"),
      @Mapping(source = "userLogin",
          target = "srvCreateCashOrderRqMessage.additionalInfo.userLogin"),
      @Mapping(source = "subbranch.subbranchCode",
          target = "srvCreateCashOrderRqMessage.additionalInfo.subbranchCode"),
      @Mapping(source = "subbranch.vspCode",
          target = "srvCreateCashOrderRqMessage.additionalInfo.VSPCode"),
      @Mapping(source = "subbranch.osbCode",
          target = "srvCreateCashOrderRqMessage.additionalInfo.OSBCode"),
      @Mapping(source = "subbranch.gosbCode",
          target = "srvCreateCashOrderRqMessage.additionalInfo.GOSBCode"),
      @Mapping(source = "subbranch.tbCode",
          target = "srvCreateCashOrderRqMessage.additionalInfo.TBCode"),
      @Mapping(source = "cashSymbols",
          target = "srvCreateCashOrderRqMessage.additionalInfo.cashSymbols.cashSymbolItem")
  })
  public abstract SrvCreateCashOrderRq createCashOrderRq(CashOrder cashOrder);

  @Mappings({
      @Mapping(target = "code", source = "cashSymbol"),
      @Mapping(target = "amount", source = "cashSymbolAmount")
  })
  abstract CashSymbol cashSymbol(SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage
      .KO1.CashSymbols.CashSymbolItem cashSymbolItem);

  @Mappings({
      @Mapping(target = "cashSymbol", source = "code"),
      @Mapping(target = "cashSymbolAmount", source = "amount")
  })
  abstract SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.AdditionalInfo.CashSymbols
      .CashSymbolItem cashSymbolItem(CashSymbol cashSymbol);

  @Mappings({
      @Mapping(target = "value", source = "type"),
      @Mapping(target = "series", source = "series"),
      @Mapping(target = "number", source = "number"),
      @Mapping(target = "issuedBy", source = "issuedBy"),
      @Mapping(target = "issuedDate", source = "issuedDate")
  })
  abstract IdentityDocumentType identityDocumentType(IdentityDocument identityDocument);

  @Mappings({
      @Mapping(source = "cashOrderId",
          target = "srvUpdCashOrderRqMessage.cashOrderId"),
      @Mapping(source = "cashOrderStatus",
          target = "srvUpdCashOrderRqMessage.cashOrderStatus")
  })
  public abstract SrvUpdStCashOrderRq updStCashOrderRq(CashOrder cashOrder);
}