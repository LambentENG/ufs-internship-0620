package ru.philit.ufs.web.view;

import java.util.List;
import ru.philit.ufs.web.dto.BaseResponse;
import ru.philit.ufs.web.dto.CashOrderJournalDto;

/**
 * Ответ для кассового ордера
 * {@link ru.philit.ufs.web.controller.ReportController#getCashOrderJournal}
 */
@SuppressWarnings("serial")
public class GetCashOrderJournalResp extends BaseResponse<List<CashOrderJournalDto>> {

}
