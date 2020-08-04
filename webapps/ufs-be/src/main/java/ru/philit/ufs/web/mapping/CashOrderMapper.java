package ru.philit.ufs.web.mapping;

import java.math.BigDecimal;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.web.dto.CashOrderDto;

/**
 * Конвертер для кассового ордера.
 */
public interface CashOrderMapper {

  CashOrderDto asDto(CashOrder in);

  CashOrderDto asDto(Operation cashOrder);

  String asDto(BigDecimal in);

  Long asEntity(String in);

}
