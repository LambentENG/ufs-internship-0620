package ru.philit.ufs.web.mapping.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import org.springframework.stereotype.Component;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.web.dto.CashOrderDto;
import ru.philit.ufs.web.mapping.CashOrderJournalMapper;

@Component
public class CashOrderJournalMapperImpl
    extends CommonMapperImpl implements CashOrderJournalMapper {

  @Override
  public CashOrderDto asDto(CashOrder in) {
    if (in == null) {
      return null;
    }
    CashOrderDto out = new CashOrderDto();

    out.setId(in.getCashOrderId());
    if (in.getCashOrderType() != null) {
      out.setCashOrderType(in.getCashOrderType().value());
    }
    if (in.getCashOrderStatus() != null) {
      out.setCashOrderStatus(in.getCashOrderStatus().value());
    }
    if (in.getOperationType() != null) {
      out.setOperationType(in.getOperationType().value());
    }
    out.setOperatorPosition(in.getOperatorPosition());
    out.setCurrencyType(in.getCurrencyType());
    out.setAmount(in.getAmount());
    out.setUserLogin(in.getUserLogin());
    out.setUserFullName(in.getUserFullName());
    out.setCreatedDttm(asLongDateDto(in.getCreatedDttm()));
    out.setSenderBank(in.getSenderBank());
    out.setRecipientBank(in.getRecipientBank());

    return out;
  }

  @Override
  public CashOrderDto asDto(Operation cashOrder) {
    if (cashOrder == null) {
      return null;
    }
    CashOrderDto out = new CashOrderDto();

    out.setId(cashOrder.getCashOrderId());

    return out;
  }

  @Override
  public String asDto(BigDecimal in) {
    return super.asDto(in);
  }

  @Override
  public Date asEntity(String in) {
    try {
      return asShortDateEntity(in);
    } catch (ParseException e) {
      return null;
    }
  }
}
