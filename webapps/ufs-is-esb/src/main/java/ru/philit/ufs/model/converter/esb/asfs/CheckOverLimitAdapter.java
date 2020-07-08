package ru.philit.ufs.model.converter.esb.asfs;

import ru.philit.ufs.model.entity.cash.CheckOverLimit;
import ru.philit.ufs.model.entity.cash.LimitStatus;
import ru.philit.ufs.model.entity.esb.asfs.LimitStatusType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq.SrvCheckOverLimitRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs.SrvCheckOverLimitRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs;
import ru.philit.ufs.model.entity.user.Workplace;

public class CheckOverLimitAdapter extends AsfsAdapter {

  //******** Converters ********

  private static LimitStatus limitStatus(LimitStatusType limitStatus) {
    return (limitStatus != null) ? LimitStatus.fromValue(limitStatus.value()) : null;
  }

  //******** Mappers *******

  private static void map(SrvCheckOverLimitRsMessage message,
      CheckOverLimit checkOverLimit) {
    checkOverLimit.setResponseCode(message.getResponseCode());
    checkOverLimit.setStatus(limitStatus(message.getStatus()));
  }

  private static void map(CheckOverLimit checkOverLimit,
      SrvCheckOverLimitRqMessage message) {
    message.setUserLogin(checkOverLimit.getUserLogin());
    message.setTobeIncreased(checkOverLimit.isTobeIncreased());
    message.setAmount(checkOverLimit.getAmount());
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
  public static CheckOverLimit convert(SrvCheckOverLimitRs response) {
    CheckOverLimit checkOverLimit = new CheckOverLimit();
    //map(response.getHeaderInfo(), checkOverLimit);
    map(response.getSrvCheckOverLimitRsMessage(), checkOverLimit);
    return checkOverLimit;
  }
}


