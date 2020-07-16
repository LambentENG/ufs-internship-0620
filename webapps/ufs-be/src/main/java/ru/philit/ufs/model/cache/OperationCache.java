package ru.philit.ufs.model.cache;

import java.util.List;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.cash.CheckOverLimit;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.model.entity.oper.OperationPackage;
import ru.philit.ufs.model.entity.oper.OperationPackageRequest;
import ru.philit.ufs.model.entity.oper.OperationTasksRequest;
import ru.philit.ufs.model.entity.user.ClientInfo;

/**
 * Интерфейс доступа к кешу данных для операций.
 */
public interface OperationCache {

  CashOrder createCashOrder(CashOrder cashOrder, ClientInfo clientInfo);

  CashOrder updateStatusCashOrder(CashOrder cashOrder, ClientInfo clientInfo);

  Boolean checkOverLimit(CheckOverLimit request, ClientInfo clientInfo);

  Operation getOperation(Long taskId);

  void addOperation(Long taskId, Operation operation);

  OperationPackage getPackage(OperationPackageRequest request, ClientInfo clientInfo);

  OperationPackage createPackage(OperationPackageRequest request, ClientInfo clientInfo);

  OperationPackage getTasksInPackage(OperationTasksRequest request, ClientInfo clientInfo);

  OperationPackage addTasksInPackage(OperationPackage request, ClientInfo clientInfo);

  OperationPackage updateTasksInPackage(OperationPackage request, ClientInfo clientInfo);

  List<OperationPackage> getTasksInPackages(OperationTasksRequest request, ClientInfo clientInfo);

}
