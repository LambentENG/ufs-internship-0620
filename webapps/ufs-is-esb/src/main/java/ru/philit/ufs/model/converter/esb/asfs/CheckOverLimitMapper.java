package ru.philit.ufs.model.converter.esb.asfs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ru.philit.ufs.model.entity.cash.CheckOverLimit;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;

@Mapper
public interface CheckOverLimitMapper {
  CheckOverLimitMapper INSTANCE = Mappers.getMapper(CheckOverLimitMapper.class);

  CheckOverLimit checkOverLimit = new CheckOverLimit();

  SrvCheckOverLimitRs toDtoResponse(CheckOverLimit checkOverLimit);

  SrvCheckOverLimitRs srvCheckOverLimitRs = CheckOverLimitMapper
      .INSTANCE.toDtoResponse(checkOverLimit);

  SrvCheckOverLimitRq toDtoRequest(CheckOverLimit workplace);

  SrvCheckOverLimitRq srvCheckOverLimitRq = CheckOverLimitMapper
      .INSTANCE.toDtoRequest(checkOverLimit);
}


