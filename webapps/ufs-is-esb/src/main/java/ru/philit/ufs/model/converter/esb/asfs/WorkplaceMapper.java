package ru.philit.ufs.model.converter.esb.asfs;

import java.math.BigInteger;
import java.util.Date;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage;
import ru.philit.ufs.model.entity.oper.OperationTypeLimit;
import ru.philit.ufs.model.entity.user.Workplace;
import ru.philit.ufs.model.entity.user.WorkplaceType;

@Mapper(componentModel = "spring", imports = {Date.class})
public abstract class WorkplaceMapper extends AsfsAdapter {

  public static final WorkplaceMapper INSTANCE = Mappers.getMapper(WorkplaceMapper.class);

  protected static WorkplaceType workplaceType(BigInteger workPlaceType) {
    return (workPlaceType != null) ? WorkplaceType.getByCode(workPlaceType.intValue()) : null;
  }

  @Mappings({
      @Mapping(target = "requestUid", source = "headerInfo.rqUID"),
      @Mapping(target = "receiveDate", expression = "java(new Date())"),
      @Mapping(target = "type",
          source = "srvGetWorkPlaceInfoRsMessage.workPlaceType"),
      @Mapping(target = "cashboxOnBoard",
          source = "srvGetWorkPlaceInfoRsMessage.cashboxOnBoard"),
      @Mapping(target = "subbranchCode",
          source = "srvGetWorkPlaceInfoRsMessage.subbranchCode"),
      @Mapping(target = "cashboxDeviceId",
          source = "srvGetWorkPlaceInfoRsMessage.cashboxOnBoardDevice"),
      @Mapping(target = "cashboxDeviceType",
          source = "srvGetWorkPlaceInfoRsMessage.cashboxDeviceType"),
      @Mapping(target = "currencyType",
          source = "srvGetWorkPlaceInfoRsMessage.currentCurrencyType"),
      @Mapping(target = "amount",
          source = "srvGetWorkPlaceInfoRsMessage.amount"),
      @Mapping(target = "limit",
          source = "srvGetWorkPlaceInfoRsMessage.workPlaceLimit"),
      @Mapping(target = "categoryLimits",
          source =
          "srvGetWorkPlaceInfoRsMessage.workPlaceOperationTypeLimit"
              + ".operationTypeLimitItem")
  })
  public abstract Workplace convert(SrvGetWorkPlaceInfoRs response);

  @Mappings({
      @Mapping(target = "categoryId", source = "operationTypeItem.operationCategory"),
      @Mapping(target = "limit", source = "operationTypeItem.operationLimit")
  })
  public abstract OperationTypeLimit operationTypeLimit(
      SrvGetWorkPlaceInfoRsMessage.WorkPlaceOperationTypeLimit.OperationTypeLimitItem
          operationTypeItem);

  @Mappings({
      @Mapping(target = "headerInfo", expression = "java(headerInfo())"),
      @Mapping(target = "srvGetWorkPlaceInfoRqMessage.workPlaceUId", source = "id")
  })
  public abstract SrvGetWorkPlaceInfoRq srvGetWorkPlaceInfoRq(Workplace workplace);
}
