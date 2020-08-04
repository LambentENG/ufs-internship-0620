package ru.philit.ufs.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.philit.ufs.model.entity.account.Representative;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.model.entity.oper.OperationPackage;
import ru.philit.ufs.model.entity.oper.OperationTask;
import ru.philit.ufs.model.entity.oper.OperationTaskCardDeposit;
import ru.philit.ufs.model.entity.user.ClientInfo;
import ru.philit.ufs.model.entity.user.Operator;
import ru.philit.ufs.model.entity.user.User;
import ru.philit.ufs.web.dto.CashOrderJournalDto;
import ru.philit.ufs.web.dto.OperationJournalDto;
import ru.philit.ufs.web.mapping.CashOrderJournalMapper;
import ru.philit.ufs.web.mapping.OperationJournalMapper;
import ru.philit.ufs.web.provider.ReportProvider;
import ru.philit.ufs.web.provider.RepresentativeProvider;
import ru.philit.ufs.web.view.GetCashOrderJournalReq;
import ru.philit.ufs.web.view.GetCashOrderJournalResp;
import ru.philit.ufs.web.view.GetOperationJournalReq;
import ru.philit.ufs.web.view.GetOperationJournalResp;

/**
 * Контроллер запроса данных отчётов.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

  private final ReportProvider reportProvider;
  private final RepresentativeProvider representativeProvider;
  private final OperationJournalMapper operationJournalMapper;
  private final CashOrderJournalMapper cashOrderJournalMapper;

  /**
   * Конструктор бина.
   */
  @Autowired
  public ReportController(
      ReportProvider reportProvider,
      RepresentativeProvider representativeProvider,
      OperationJournalMapper operationJournalMapper,
      CashOrderJournalMapper cashOrderJournalMapper
  ) {
    this.reportProvider = reportProvider;
    this.representativeProvider = representativeProvider;
    this.operationJournalMapper = operationJournalMapper;
    this.cashOrderJournalMapper = cashOrderJournalMapper;
  }

  /**
   * Получение списка записей журнала операций.
   *
   * @param request параметры запроса списка
   * @param clientInfo информация о клиенте
   * @return список записей
   */
  @RequestMapping(value = "/operationJournal", method = RequestMethod.POST)
  public GetOperationJournalResp getOperationJournal(
      @RequestBody GetOperationJournalReq request, ClientInfo clientInfo
  ) {
    List<OperationJournalDto> items = new ArrayList<>();

    for (OperationPackage opPackage : reportProvider.getOperationPackages(
        operationJournalMapper.asEntity(request.getFromDate()),
        operationJournalMapper.asEntity(request.getToDate()),
        clientInfo
    )) {
      for (OperationTask task : opPackage.getToCardDeposits()) {
        OperationTaskCardDeposit taskCardDeposit = (OperationTaskCardDeposit) task;

        Operation operation = reportProvider.getOperation(task);
        if (operation == null) {
          continue; // задачи без операции не попадают в журнал
        }

        Operator operator = reportProvider.getOperator(opPackage.getUserLogin(), clientInfo);
        User taskUser = reportProvider.getUser(opPackage.getUserLogin());
        BigDecimal commissionAmount =  reportProvider.getCommission(
            taskCardDeposit.getAccountId(), taskCardDeposit.getAmount(), operation, clientInfo
        );
        Representative representative = representativeProvider.getRepresentativeById(
            taskCardDeposit.getRepresentativeId(), clientInfo
        );

        items.add(new OperationJournalDto()
            .withOperator(operationJournalMapper.asDto(operator))
            .withUser(operationJournalMapper.asDto(taskUser))
            .withRepresentative(operationJournalMapper.asDto(representative))
            .withOperation(operationJournalMapper.asDto(operation))
            .withDeposit(operationJournalMapper.asDto(taskCardDeposit))
            .withCommission(operationJournalMapper.asDto(commissionAmount))
        );
      }
    }

    return new GetOperationJournalResp().withSuccess(items);
  }

  /**
   * Получение списка записей журнала кассового ордера.
   *
   * @param request параметры запроса списка
   * @param clientInfo информация о клиенте
   * @return список записей
   */
  @RequestMapping(value = "/cashOrderJournal", method = RequestMethod.POST)
  public GetCashOrderJournalResp getCashOrderJournal(
      @RequestBody GetCashOrderJournalReq request, ClientInfo clientInfo
  ) {
    List<CashOrderJournalDto> items = new ArrayList<>();

    for (OperationPackage opPackage : reportProvider.getOperationPackages(
        cashOrderJournalMapper.asEntity(request.getFromDate()),
        cashOrderJournalMapper.asEntity(request.getToDate()),
        clientInfo
    )) {

      for (CashOrder cashOrders : opPackage.getCashOrderList()) {

        CashOrder cashOrderId = reportProvider.getCashOrderId(cashOrders);
        if (cashOrderId == null) {
          continue; // кассовые ордера без идентификатора не попадают в журнал
        }

        items.add(new CashOrderJournalDto()
            .withCashOrder(cashOrderJournalMapper.asDto(cashOrderId))
        );
      }
    }

    return new GetCashOrderJournalResp().withSuccess(items);
  }
}
