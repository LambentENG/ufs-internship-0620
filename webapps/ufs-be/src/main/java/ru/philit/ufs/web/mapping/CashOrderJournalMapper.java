package ru.philit.ufs.web.mapping;

import java.math.BigDecimal;
import java.util.Date;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.web.dto.CashOrderDto;

/**
 * Конвертер для записей журнала кассового ордера.
 */
public interface CashOrderJournalMapper {

  CashOrderDto asDto(CashOrder in);

  CashOrderDto asDto(Operation cashOrder);

  String asDto(BigDecimal in);

  Date asEntity(String in);

}
