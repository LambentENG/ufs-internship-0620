package ru.philit.ufs.model.converter.esb.asfs;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.philit.ufs.model.TestData;
import ru.philit.ufs.model.converter.ConverterBaseTest;
import ru.philit.ufs.model.converter.esb.multi.MultiAdapter;
import ru.philit.ufs.model.entity.cash.CashOrder;
import ru.philit.ufs.model.entity.cash.CashOrderStatus;
import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq.SrvCreateCashOrderRqMessage.AdditionalInfo.CashSymbols.CashSymbolItem;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1.CashSymbols;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs.SrvUpdCashOrderRsMessage;

public class CashOrderAdapterTest extends AsfsAdapterBaseTest {

  private CashOrder cashOrder;
  private SrvCreateCashOrderRs response1;
  private SrvUpdStCashOrderRs response2;

  /**
   * Set up test data.
   */
  @Before
  public void setUp() {
    cashOrder = new CashOrder();

    cashOrder.setCashOrderId(TestData.CASH_ORDER_ID);
    cashOrder.setCashOrderStatus(CashOrderStatus.CREATED);

    response1 = new SrvCreateCashOrderRs();
    response1.setHeaderInfo(AsfsAdapterBaseTest.headerInfo(TestData.ACCOUNT_REQUEST_FIX_UUID));
    response1.getHeaderInfo().setRqTm(ConverterBaseTest.xmlCalendar(2017, 5, 12, 17, 57));
    response1.setSrvCreateCashOrderRsMessage(new SrvCreateCashOrderRsMessage());
    response1.getSrvCreateCashOrderRsMessage().setKO1(new KO1());
    response1.getSrvCreateCashOrderRsMessage().getKO1().setResponseCode(TestData.RESPONSE_CODE);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setResponseMsg(TestData.RESPONSE_MSG);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setCashOrderId(TestData.CASH_ORDER_ID);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setCashOrderINum(TestData.CASH_ORDER_INUM);
    response1.getSrvCreateCashOrderRsMessage().getKO1()
        .setCashOrderStatus(CashOrderStatusType.CREATED);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setCashOrderType(CashOrderType.KO_1);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setCreatedDttm(
        ConverterBaseTest.xmlCalendar(2020, 7, 03, 22, 35));
    response1.getSrvCreateCashOrderRsMessage().getKO1().setOperationId(TestData.OPERATION_ID);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setRepFIO(TestData.REP_FIO);
    response1.getSrvCreateCashOrderRsMessage().getKO1()
        .setLegalEntityShortName(TestData.LEGAL_ENTITY_SHORT_NAME);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setINN(TestData.BANK_INN);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setAmount(BigDecimal.valueOf(66.66));
    response1.getSrvCreateCashOrderRsMessage().getKO1().setAccountId(TestData.ACCOUNT_ID);
    CashSymbols cashSymbols = new CashSymbols();
    response1.getSrvCreateCashOrderRsMessage().getKO1().setCashSymbols(cashSymbols);
    CashSymbolItem cashSymbolItem = new CashSymbolItem();
    cashSymbolItem.setCashSymbol("4444");
    cashSymbolItem.setCashSymbolAmount(BigDecimal.valueOf(44.44));
    response1.getSrvCreateCashOrderRsMessage().getKO1().getCashSymbols()
        .getCashSymbolItem();
    response1.getSrvCreateCashOrderRsMessage().getKO1().setSenderBank(TestData.BANK_NAME);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setSenderBankBIC(TestData.BANK_BIC);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setRecipientBank(TestData.BANK_NAME);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setRecipientBankBIC(TestData.BANK_BIC);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setClientTypeFK(true);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setFDestLEName(TestData.FDESTLENAME);
    response1.getSrvCreateCashOrderRsMessage().getKO1()
        .setOperatorPosition(TestData.OPERATOR_POSITION);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setUserFullName(TestData.USER_FULL_NAME);
    response1.getSrvCreateCashOrderRsMessage().getKO1().setUserPosition(TestData.USER_POSITION);

    response2 = new SrvUpdStCashOrderRs();
    response2.setHeaderInfo(headerInfo());
    response2.setSrvUpdCashOrderRsMessage(new SrvUpdCashOrderRsMessage());
    response2.getSrvUpdCashOrderRsMessage().setResponseCode(TestData.RESPONSE_CODE);
    response2.getSrvUpdCashOrderRsMessage().setResponseMsg(TestData.RESPONSE_MSG);
    response2.getSrvUpdCashOrderRsMessage().setCashOrderId(TestData.CASH_ORDER_ID);
    response2.getSrvUpdCashOrderRsMessage().setCashOrderINum(TestData.CASH_ORDER_INUM);
    response2.getSrvUpdCashOrderRsMessage().setCashOrderStatus(CashOrderStatusType.CREATED);
    response2.getSrvUpdCashOrderRsMessage().setCashOrderType(CashOrderType.KO_2);
  }

  @Test
  public void testCreateCashOrder() {
    SrvCreateCashOrderRq request = CashOrderAdapter.createCashOrderRq(cashOrder);
    assertHeaderInfo(request.getHeaderInfo());
    Assert.assertNotNull(request.getSrvCreateCashOrderRqMessage());
    Assert.assertEquals(request.getSrvCreateCashOrderRqMessage().getCashOrderId(),
        TestData.CASH_ORDER_ID);
  }

  @Test
  public void testUpdStCashOrder() {
    SrvUpdStCashOrderRq request = CashOrderAdapter.updStCashOrderRq(cashOrder);
    assertHeaderInfo(request.getHeaderInfo());
    Assert.assertNotNull(request.getSrvUpdCashOrderRqMessage());
    Assert.assertEquals(request.getSrvUpdCashOrderRqMessage().getCashOrderId(),
        TestData.CASH_ORDER_ID);
    Assert.assertEquals(request.getSrvUpdCashOrderRqMessage().getCashOrderStatus(),
        CashOrderStatusType.CREATED);
  }

  private void testCreate(CashOrder cashOrder) {
    assertHeaderInfo(cashOrder);
    Assert.assertNotNull(cashOrder);
    Assert.assertEquals(cashOrder.getCashOrderId(), TestData.CASH_ORDER_ID);
    Assert.assertEquals(cashOrder.getCashOrderINum(), TestData.CASH_ORDER_INUM);
    Assert.assertEquals(cashOrder.getAccountId(), TestData.ACCOUNT_ID);
    Assert.assertEquals(cashOrder.getCashOrderStatus(), TestData.CASH_ORDER_STATUS);
    Assert.assertEquals(cashOrder.getAmount(), TestData.AMOUNT);
    Assert.assertEquals(cashOrder.getResponseCode(), TestData.RESPONSE_CODE);
    Assert.assertEquals(cashOrder.getResponseMsg(), TestData.RESPONSE_MSG);
    Assert.assertEquals(cashOrder.getCashOrderType(),
        ru.philit.ufs.model.entity.cash.CashOrderType.KO_1);
    Assert.assertEquals(cashOrder.getOperationId(), TestData.OPERATION_ID);
    Assert.assertEquals(cashOrder.getRepFio(), TestData.REP_FIO);
    Assert.assertEquals(cashOrder.getLegalEntityShortName(), TestData.LEGAL_ENTITY_SHORT_NAME);
    Assert.assertEquals(cashOrder.getSenderBank(), TestData.BANK_NAME);
    Assert.assertEquals(cashOrder.getSenderBankBic(), TestData.BANK_BIC);
    Assert.assertEquals(cashOrder.getRecipientBank(), TestData.BANK_NAME);
    Assert.assertEquals(cashOrder.getRecipientBankBic(), TestData.BANK_BIC);
    Assert.assertEquals(cashOrder.getClientTypeFk(), true);
    Assert.assertEquals(cashOrder.getFdestLeName(), TestData.FDESTLENAME);
    Assert.assertEquals(cashOrder.getOperatorPosition(), TestData.OPERATOR_POSITION);
    Assert.assertEquals(cashOrder.getUserFullName(), TestData.USER_FULL_NAME);
    Assert.assertEquals(cashOrder.getUserPosition(), TestData.USER_POSITION);
    Assert.assertEquals(cashOrder.getCashSymbols(), cashOrder.getCashSymbols());
  }

  private void testUpdateState(CashOrder cashOrder) {
    assertHeaderInfo(cashOrder);
    Assert.assertNotNull(cashOrder);
    Assert.assertEquals(cashOrder.getCashOrderId(), TestData.CASH_ORDER_ID);
    Assert.assertEquals(cashOrder.getCashOrderINum(), TestData.CASH_ORDER_INUM);
    Assert.assertEquals(cashOrder.getCashOrderStatus(), TestData.CASH_ORDER_STATUS);
    Assert.assertEquals(cashOrder.getResponseCode(), TestData.RESPONSE_CODE);
    Assert.assertEquals(cashOrder.getResponseMsg(), TestData.RESPONSE_MSG);
  }

  @Test
  public void testConvertSrvCreateCashOrderRs() {
    CashOrder cashOrder = CashOrderAdapter.convert(response1);
    testCreate(cashOrder);
  }

  @Test
  public void testConvertSrvUpdStCashOrderRs() {
    CashOrder cashOrder = CashOrderAdapter.convert(response2);
    testUpdateState(cashOrder);
  }

  @Test
  public void testMultiAdapter() {
    ExternalEntity externalEntity1 = MultiAdapter.convert(response1);
    Assert.assertNotNull(externalEntity1);
    Assert.assertEquals(externalEntity1.getClass(), CashOrder.class);

    ExternalEntity externalEntity2 = MultiAdapter.convert(response2);
    Assert.assertNotNull(externalEntity2);
    Assert.assertEquals(externalEntity2.getClass(), CashOrder.class);
  }
}