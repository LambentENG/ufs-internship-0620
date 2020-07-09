package ru.philit.ufs.model.converter.esb.asfs;

import ru.philit.ufs.model.entity.cash.CheckOverLimit;
import ru.philit.ufs.model.entity.cash.LimitStatus;
import ru.philit.ufs.model.entity.common.ExternalEntityContainer;
import ru.philit.ufs.model.entity.esb.asfs.LimitStatusType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq.SrvCheckOverLimitRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs.SrvCheckOverLimitRsMessage;

public class CheckOverLimitAdapter extends AsfsAdapter {

  //******** Converters ********

  private static Boolean limitStatus(LimitStatusType limitStatus) {
    return limitStatus.name().equals(LimitStatus.LIMIT_PASSED.toString());
  }

  //******** Mappers *******

  private static void map(CheckOverLimit checkOverLimit,
      SrvCheckOverLimitRqMessage message) {
    message.setUserLogin(checkOverLimit.getUserLogin());
    message.setTobeIncreased(checkOverLimit.isTobeIncreased());
    message.setAmount(checkOverLimit.getAmount());
  }

  private static void map(SrvCheckOverLimitRsMessage message,
      ExternalEntityContainer<Boolean> container) {
    container.setResponseCode(message.getResponseCode());
    container.setData(limitStatus(message.getStatus()));
  }

  //******** Methods *******

  /**
   * Возвращает объект запроса на проверку лимита.
   */
  public static SrvCheckOverLimitRq srvCheckOverLimitRq(CheckOverLimit checkOverLimit) {
    SrvCheckOverLimitRq request = new SrvCheckOverLimitRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvCheckOverLimitRqMessage(new SrvCheckOverLimitRqMessage());
    map(checkOverLimit, request.getSrvCheckOverLimitRqMessage());
    return request;
  }

  /**
   * Преобразует транспортный объект проверки лимита во внутреннюю сущность.
   */
  public static ExternalEntityContainer<Boolean> convert(SrvCheckOverLimitRs response) {
    ExternalEntityContainer<Boolean> container = new ExternalEntityContainer<>();
    map(response.getHeaderInfo(), container);
    map(response.getSrvCheckOverLimitRsMessage(), container);
    return container;
  }
}


