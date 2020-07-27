package ru.philit.ufs.model.converter.esb.asfs;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.philit.ufs.model.TestData;
import ru.philit.ufs.model.converter.ConverterBaseTest;
import ru.philit.ufs.model.converter.esb.multi.MultiAdapter;
import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage.WorkPlaceOperationTypeLimit;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage.WorkPlaceOperationTypeLimit.OperationTypeLimitItem;
import ru.philit.ufs.model.entity.user.Workplace;

public class WorkplaceMapperTest extends AsfsAdapterBaseTest {

  private Workplace workplace;
  private SrvGetWorkPlaceInfoRs response;

  /**
   * Set up test data.
   */
  @Before
  public void setUp() {
    workplace = new Workplace();

    response = new SrvGetWorkPlaceInfoRs();
    response.setHeaderInfo(AsfsAdapterBaseTest.headerInfo(TestData.ACCOUNT_REQUEST_FIX_UUID));
    response.getHeaderInfo().setRqTm(ConverterBaseTest.xmlCalendar(2017, 5, 12, 17, 57));
    response.setSrvGetWorkPlaceInfoRsMessage(new SrvGetWorkPlaceInfoRsMessage());
    response.getSrvGetWorkPlaceInfoRsMessage().setWorkPlaceType(TestData.WORKPLACE_TYPE);
    response.getSrvGetWorkPlaceInfoRsMessage().setCashboxOnBoard(true);
    response.getSrvGetWorkPlaceInfoRsMessage().setSubbranchCode(TestData.BANK_SUBBRANCH_CODE);
    response.getSrvGetWorkPlaceInfoRsMessage()
      .setCashboxOnBoardDevice(TestData.CASHBOX_ON_BOARD_DEVICE);
    response.getSrvGetWorkPlaceInfoRsMessage().setCashboxDeviceType(TestData.CASHBOX_DEVICE_TYPE);
    response.getSrvGetWorkPlaceInfoRsMessage().setCurrentCurrencyType(TestData.CURRENCY_TYPE);
    response.getSrvGetWorkPlaceInfoRsMessage().setAmount(TestData.AMOUNT);
    response.getSrvGetWorkPlaceInfoRsMessage().setWorkPlaceLimit(TestData.WORKPLACE_LIMIT);
    WorkPlaceOperationTypeLimit operationTypeLimit = new WorkPlaceOperationTypeLimit();
    response.getSrvGetWorkPlaceInfoRsMessage().setWorkPlaceOperationTypeLimit(operationTypeLimit);
    OperationTypeLimitItem operationTypeLimitItem = new OperationTypeLimitItem();
    operationTypeLimitItem.setOperationCategory(BigInteger.valueOf(4444));
    operationTypeLimitItem.setOperationLimit(BigDecimal.valueOf(44.44));
    response.getSrvGetWorkPlaceInfoRsMessage().getWorkPlaceOperationTypeLimit()
      .getOperationTypeLimitItem().add(operationTypeLimitItem);
  }

  private void testWorkplace(Workplace workplace) {
    assertHeaderInfo(workplace);
    Assert.assertNotNull(workplace);
    Assert.assertTrue(workplace.isCashboxOnBoard());
    Assert.assertEquals(workplace.getSubbranchCode(), TestData.BANK_SUBBRANCH_CODE);
    Assert.assertEquals(workplace.getCashboxDeviceId(), TestData.CASHBOX_ON_BOARD_DEVICE);
    Assert.assertEquals(workplace.getCashboxDeviceType(), TestData.CASHBOX_DEVICE_TYPE);
    Assert.assertEquals(workplace.getCurrencyType(), TestData.CURRENCY_TYPE);
    Assert.assertEquals(workplace.getAmount(), TestData.AMOUNT);
    Assert.assertEquals(workplace.getLimit(), TestData.WORKPLACE_LIMIT);
    Assert.assertEquals(workplace.getCategoryLimits().get(0).getCategoryId(), "4444");
    Assert.assertEquals(workplace.getCategoryLimits().get(0).getLimit(), BigDecimal.valueOf(44.44));
  }

  @Test
  public void srvGetWorkPlaceInfoRq() {
    SrvGetWorkPlaceInfoRq request = WorkplaceMapper.INSTANCE.srvGetWorkPlaceInfoRq(workplace);
    assertHeaderInfo(request.getHeaderInfo());
    Assert.assertNotNull(request.getSrvGetWorkPlaceInfoRqMessage());
    Assert.assertEquals(workplace.getId(),
        request.getSrvGetWorkPlaceInfoRqMessage().getWorkPlaceUId());
  }

  @Test
  public void convert() {
    Workplace workplace = WorkplaceMapper.INSTANCE.convert(response);
    testWorkplace(workplace);
  }

  @Test
  public void testMultiAdapter() {
    ExternalEntity externalEntity = MultiAdapter.convert(response);
    Assert.assertNotNull(externalEntity);
    Assert.assertEquals(externalEntity.getClass(), Workplace.class);
  }
}