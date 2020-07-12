package ru.philit.ufs.model.converter.esb.asfs;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs;
import ru.philit.ufs.model.entity.user.Workplace;

@Mapper
public interface WorkplaceMapper {
  WorkplaceMapper INSTANCE = Mappers.getMapper(WorkplaceMapper.class);

  @Mappings({
      //  @Mapping(source = "type",
      //  target = "srvGetWorkPlaceInfoRsMessage.workPlaceType"),
      @Mapping(source = "cashboxOnBoard",
          target = "srvGetWorkPlaceInfoRsMessage.cashboxOnBoard"),
      @Mapping(source = "subbranchCode",
          target = "srvGetWorkPlaceInfoRsMessage.subbranchCode"),
      @Mapping(source = "cashboxDeviceId",
          target = "srvGetWorkPlaceInfoRsMessage.cashboxOnBoardDevice"),
      @Mapping(source = "cashboxDeviceType",
          target = "srvGetWorkPlaceInfoRsMessage.cashboxDeviceType"),
      @Mapping(source = "currencyType",
          target = "srvGetWorkPlaceInfoRsMessage.currentCurrencyType"),
      @Mapping(source = "amount",
          target = "srvGetWorkPlaceInfoRsMessage.amount"),
      @Mapping(source = "limit",
          target = "srvGetWorkPlaceInfoRsMessage.workPlaceLimit"),
          //  @Mapping(source = "categoryLimits",
          //  target =
          //  "srvGetWorkPlaceInfoRsMessage.
          //  workPlaceOperationTypeLimit.workPlaceOperationTypeLimit")
  })
  SrvGetWorkPlaceInfoRs workPlaceInfoRs(Workplace workplace);

  @Mappings({
      @Mapping(source = "id",
          target = "srvGetWorkPlaceInfoRqMessage.workPlaceUId")
  })
  SrvGetWorkPlaceInfoRq workPlaceInfoRq(Workplace workplace);
}
