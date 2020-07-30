package ru.philit.ufs.model.cache;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.LimitStatusType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage;
import ru.philit.ufs.model.entity.esb.eks.PkgTaskStatusType;
import ru.philit.ufs.model.entity.esb.eks.SrvGetTaskClOperPkgRs.SrvGetTaskClOperPkgRsMessage;
import ru.philit.ufs.model.entity.oper.OperationPackageInfo;

/**
 * Кеш данных Mock приложения.
 */
public interface MockCache {

  Long saveTaskCardDeposit(Long packageId, Long taskId, Object taskBody);

  Long saveTaskCardWithdraw(Long packageId, Long taskId, Object taskBody);

  Long saveTaskAccountDeposit(Long packageId, Long taskId, Object taskBody);

  Long saveTaskAccountWithdraw(Long packageId, Long taskId, Object taskBody);

  Long saveTaskCheckbookIssuing(Long packageId, Long taskId, Object taskBody);

  void saveTaskStatus(Long taskId, PkgTaskStatusType status);

  Long checkPackage(String inn);

  OperationPackageInfo createPackage(String inn, String workplaceId, String userLogin);

  OperationPackageInfo getPackageInfo(Long packageId);

  Map<Long, List<SrvGetTaskClOperPkgRsMessage.PkgItem.ToCardDeposit.TaskItem>>
        searchTasksCardDeposit(Long packageId, PkgTaskStatusType taskStatus, Date fromDate,
        Date toDate, List<Long> taskIds);

  void createCashOrder(SrvCreateCashOrderRqMessage cashOrderBody);

  void updateStatusCashOrder(String cashOrderId, CashOrderStatusType cashOrderStatus);

  LimitStatusType checkOverLimit(String userLogin, boolean isTobeIncreased, BigDecimal amount);
}
