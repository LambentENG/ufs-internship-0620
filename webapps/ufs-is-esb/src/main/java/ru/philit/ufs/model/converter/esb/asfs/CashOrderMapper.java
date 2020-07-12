package ru.philit.ufs.model.converter.esb.asfs;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderType;
import ru.philit.ufs.model.entity.esb.asfs.OperTypeLabel;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.AdditionalInfo.CashSymbols;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.RepData;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs;

@Mapper
public interface CashOrderMapper {

  CashOrderMapper INSTANCE = Mappers.getMapper(CashOrderMapper.class);

  CashOrderStatusType map(ru.philit.ufs.model.entity.cash.CashOrderStatus value);

  CashOrderType map(ru.philit.ufs.model.entity.cash.CashOrderType value);

  CashSymbols map(ru.philit.ufs.model.entity.oper.CashSymbol value);

  // Can't generate mapping
  // method from iterable type to non-iterable type
  //
  // CashSymbols map(java.util.List<ru.philit.ufs.model.entity.oper.CashSymbol> value); // :(

  OperTypeLabel map(ru.philit.ufs.model.entity.common.OperationTypeCode value);

  RepData map(ru.philit.ufs.model.entity.account.Representative value);

  //Mapping to "createCashOrder" DTO entities and sub-entities

  @Mappings({
      @Mapping(source = "responseCode",
          target = "srvCreateCashOrderRsMessage.KO1.responseCode"),
      @Mapping(source = "responseMsg",
          target = "srvCreateCashOrderRsMessage.KO1.responseMsg"),
      @Mapping(source = "cashOrderId",
          target = "srvCreateCashOrderRsMessage.KO1.cashOrderId"),
      @Mapping(source = "cashOrderINum",
          target = "srvCreateCashOrderRsMessage.KO1.cashOrderINum"),
      @Mapping(source = "cashOrderStatus",
          target = "srvCreateCashOrderRsMessage.KO1.cashOrderStatus"),
      @Mapping(source = "cashOrderType",
          target = "srvCreateCashOrderRsMessage.KO1.cashOrderType"),
      @Mapping(source = "createdDttm",
          target = "srvCreateCashOrderRsMessage.KO1.createdDttm"),
      @Mapping(source = "operationId",
          target = "srvCreateCashOrderRsMessage.KO1.operationId"),
      @Mapping(source = "repFio",
          target = "srvCreateCashOrderRsMessage.KO1.repFIO"),
      @Mapping(source = "legalEntityShortName",
          target = "srvCreateCashOrderRsMessage.KO1.legalEntityShortName"),
      @Mapping(source = "amount",
          target = "srvCreateCashOrderRsMessage.KO1.amount"),
      @Mapping(source = "accountId",
          target = "srvCreateCashOrderRsMessage.KO1.accountId"),
      @Mapping(source = "senderBank",
          target = "srvCreateCashOrderRsMessage.KO1.senderBank"),
      @Mapping(source = "senderBankBic",
          target = "srvCreateCashOrderRsMessage.KO1.senderBankBIC"),
      @Mapping(source = "recipientBank",
          target = "srvCreateCashOrderRsMessage.KO1.recipientBank"),
      @Mapping(source = "recipientBankBic",
          target = "srvCreateCashOrderRsMessage.KO1.recipientBankBIC"),
      @Mapping(source = "clientTypeFk",
          target = "srvCreateCashOrderRsMessage.KO1.clientTypeFK"),
      @Mapping(source = "fdestLeName",
        target = "srvCreateCashOrderRsMessage.KO1.FDestLEName"),
      @Mapping(source = "operatorPosition",
          target = "srvCreateCashOrderRsMessage.KO1.operatorPosition"),
      @Mapping(source = "userFullName",
          target = "srvCreateCashOrderRsMessage.KO1.userFullName"),
      @Mapping(source = "userPosition",
          target = "srvCreateCashOrderRsMessage.KO1.userPosition"),
          //  @Mapping(source = "cashSymbols",
          //  target = "srvCreateCashOrderRsMessage.KO1.cashSymbols"),
  })
  SrvCreateCashOrderRs createCashOrderRs(CashOrder cashOrder);

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
      //  @Mapping(source = "repData.identityDocuments",
      //  target = "srvCreateCashOrderRqMessage.repData.identityDocumentType"),
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
          //  @Mapping(source = "cashSymbols",
          //  target = "srvCreateCashOrderRqMessage.additionalInfo.cashSymbols")
  })
  SrvCreateCashOrderRq createCashOrderRq(CashOrder cashOrder);

  //Mapping to "updateStCashOrder" DTO entities

  @Mappings({
      @Mapping(source = "responseCode",
          target = "srvUpdCashOrderRsMessage.responseCode"),
      @Mapping(source = "responseMsg",
          target = "srvUpdCashOrderRsMessage.responseMsg"),
      @Mapping(source = "cashOrderId",
          target = "srvUpdCashOrderRsMessage.cashOrderId"),
      @Mapping(source = "cashOrderINum",
          target = "srvUpdCashOrderRsMessage.cashOrderINum"),
      @Mapping(source = "cashOrderStatus",
          target = "srvUpdCashOrderRsMessage.cashOrderStatus"),
      @Mapping(source = "cashOrderType",
          target = "srvUpdCashOrderRsMessage.cashOrderType")
  })
  SrvUpdStCashOrderRs updStCashOrderRs(CashOrder cashOrder);

  @Mappings({
      @Mapping(source = "cashOrderId",
          target = "srvUpdCashOrderRqMessage.cashOrderId"),
      @Mapping(source = "cashOrderStatus",
          target = "srvUpdCashOrderRqMessage.cashOrderStatus")
  })
  SrvUpdStCashOrderRq updStCashOrderRq(CashOrder cashOrder);
}