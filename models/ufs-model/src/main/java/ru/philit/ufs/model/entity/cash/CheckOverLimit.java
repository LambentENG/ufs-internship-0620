package ru.philit.ufs.model.entity.cash;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
