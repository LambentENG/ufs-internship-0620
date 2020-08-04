package ru.philit.ufs.web.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.philit.ufs.web.dto.BaseRequest;

/**
 * Запрос для метода {@link ru.philit.ufs.web.controller.ReportController#getCashOrderJournal}
 */
@ToString
@Getter
@Setter
@SuppressWarnings("serial")
public class GetCashOrderJournalReq extends BaseRequest {

  /**
   * Журнал кассового ордера с даты.
   */
  private String fromDate;
  /**
   * Журнал кассового ордера по дату.
   */
  private String toDate;

}
