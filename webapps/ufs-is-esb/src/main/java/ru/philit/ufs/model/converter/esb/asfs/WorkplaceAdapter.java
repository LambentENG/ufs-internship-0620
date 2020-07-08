package ru.philit.ufs.model.converter.esb.asfs;

import java.math.BigInteger;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRq.SrvGetWorkPlaceInfoRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage.WorkPlaceOperationTypeLimit.OperationTypeLimitItem;
import ru.philit.ufs.model.entity.oper.OperationTypeLimit;
import ru.philit.ufs.model.entity.user.Workplace;
import ru.philit.ufs.model.entity.user.WorkplaceType;

public class WorkplaceAdapter extends AsfsAdapter {

  //******** Converters *******

  private static WorkplaceType workplaceType(BigInteger workPlaceType) {
    return (workPlaceType != null) ? WorkplaceType.getByCode(workPlaceType.intValue()) : null;
  }

  //******** Mappers *******

  private static void map(SrvGetWorkPlaceInfoRsMessage message,
      Workplace workplace) {
    workplace.setType(workplaceType(message.getWorkPlaceType()));
    workplace.setCashboxOnBoard(message.isCashboxOnBoard());
    workplace.setSubbranchCode(message.getSubbranchCode());
    workplace.setCashboxDeviceId(message.getCashboxOnBoardDevice());
    workplace.setCashboxDeviceType(message.getCashboxDeviceType());
    workplace.setCurrencyType(message.getCurrentCurrencyType());
    workplace.setAmount(message.getAmount());
    workplace.setLimit(message.getWorkPlaceLimit());
    for (OperationTypeLimitItem limitItem
        : message.getWorkPlaceOperationTypeLimit().getOperationTypeLimitItem()) {
      OperationTypeLimit operationTypeLimit = new OperationTypeLimit();
      operationTypeLimit.setCategoryId(String.valueOf(limitItem.getOperationCategory()));
      operationTypeLimit.setLimit(limitItem.getOperationLimit());
      workplace.getCategoryLimits().add(operationTypeLimit);
    }
  }

  private static void map(Workplace workplace,
      SrvGetWorkPlaceInfoRqMessage message) {
    message.setWorkPlaceUId(workplace.getId());
  }

  //******** Methods *******

  /**
   * Возвращает объект запроса информации о рабочем месте.
   */
  public static SrvGetWorkPlaceInfoRq srvGetWorkPlaceInfoRq(Workplace workplace) {
    SrvGetWorkPlaceInfoRq request = new SrvGetWorkPlaceInfoRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvGetWorkPlaceInfoRqMessage(new SrvGetWorkPlaceInfoRqMessage());
    map(workplace, request.getSrvGetWorkPlaceInfoRqMessage());
    return request;
  }

  /**
   * Преобразует транспортный объект запроса информации о рабочем месте во внутреннюю сущность.
   */
  public static Workplace convert(SrvGetWorkPlaceInfoRs response) {
    Workplace workplace = new Workplace();
    map(response.getHeaderInfo(), workplace);
    map(response.getSrvGetWorkPlaceInfoRsMessage(), workplace);
    return workplace;
  }
}

