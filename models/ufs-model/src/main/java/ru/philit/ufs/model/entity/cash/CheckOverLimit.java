package ru.philit.ufs.model.entity.cash;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.philit.ufs.model.entity.common.ExternalEntity;

/**
 * Объект запроса проверки превышения лимита.
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CheckOverLimit extends ExternalEntity {

  //SrvCheckOverLimitRqMessage
  private String userLogin;
  private boolean tobeIncreased;
  private BigDecimal amount;
  //SrvCheckOverLimitRsMessage
  private String responseCode;
  private LimitStatus status;
}
