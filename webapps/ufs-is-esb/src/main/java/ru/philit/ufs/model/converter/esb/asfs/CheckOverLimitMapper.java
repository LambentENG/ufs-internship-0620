package ru.philit.ufs.model.converter.esb.asfs;

import java.util.Date;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ru.philit.ufs.model.entity.cash.CheckOverLimit;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;

@Mapper(componentModel = "spring", imports = {Date.class})
public abstract class CheckOverLimitMapper extends AsfsAdapter {

  public static final CheckOverLimitMapper INSTANCE = Mappers.getMapper(CheckOverLimitMapper.class);

  public  abstract ru.philit.ufs.model.entity.cash.LimitStatus
      map(ru.philit.ufs.model.entity.esb.asfs.LimitStatusType value);

  @Mappings({
      @Mapping(target = "requestUid", source = "headerInfo.rqUID"),
      @Mapping(target = "receiveDate", expression = "java(new Date())"),
      @Mapping(target = "responseCode",
      source = "srvCheckOverLimitRsMessage.responseCode"),
      @Mapping(target = "status",
      source = "srvCheckOverLimitRsMessage.status")
  })
  public abstract CheckOverLimit convert(SrvCheckOverLimitRs response);

  @Mappings({
      @Mapping(target = "headerInfo", expression = "java(headerInfo())"),
      @Mapping(target = "srvCheckOverLimitRqMessage.userLogin", source = "userLogin"),
      @Mapping(target = "srvCheckOverLimitRqMessage.tobeIncreased", source = "tobeIncreased"),
      @Mapping(target = "srvCheckOverLimitRqMessage.amount", source = "amount")
  })
  public abstract SrvCheckOverLimitRq srvCheckOverLimitRq(CheckOverLimit checkOverLimit);
}

