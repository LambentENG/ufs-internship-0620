package ru.philit.ufs.model.entity.cash;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Объект запроса проверки превышения лимита.
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class CheckOverLimit implements Serializable {

  //SrvCheckOverLimitRqMessage
  private String userLogin;
  private boolean tobeIncreased;
  private BigDecimal amount;
  //SrvCheckOverLimitRsMessage
  private String responseCode;
  private LimitStatus status;
}
