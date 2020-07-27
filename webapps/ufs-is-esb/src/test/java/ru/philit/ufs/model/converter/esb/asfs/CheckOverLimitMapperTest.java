package ru.philit.ufs.model.converter.esb.asfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.philit.ufs.model.TestData;
import ru.philit.ufs.model.converter.esb.multi.MultiAdapter;
import ru.philit.ufs.model.entity.cash.CheckOverLimit;
import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.common.ExternalEntityContainer;
import ru.philit.ufs.model.entity.esb.asfs.LimitStatusType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs.SrvCheckOverLimitRsMessage;

public class CheckOverLimitMapperTest extends AsfsAdapterBaseTest {

  private CheckOverLimit checkOverLimit;
  private SrvCheckOverLimitRs response;

  /**
   * Set up test data.
   */
  @Before
  public void setUp() {
    checkOverLimit = new CheckOverLimit();
    checkOverLimit.setUserLogin(TestData.USER_LOGIN);
    checkOverLimit.setTobeIncreased(true);
    checkOverLimit.setAmount(TestData.AMOUNT);

    response = new SrvCheckOverLimitRs();
    response.setHeaderInfo(headerInfo());
    response.setSrvCheckOverLimitRsMessage(new SrvCheckOverLimitRsMessage());
    response.getSrvCheckOverLimitRsMessage().setResponseCode(TestData.RESPONSE_CODE);
    response.getSrvCheckOverLimitRsMessage().setStatus(LimitStatusType.LIMIT_PASSED);
  }

  private void testWorkplace(CheckOverLimit checkOverLimit) {
    assertHeaderInfo(checkOverLimit);
    Assert.assertNotNull(checkOverLimit);
    Assert.assertTrue(checkOverLimit.getResponseCode(), true);
    Assert.assertEquals(checkOverLimit.getStatus(), checkOverLimit.getStatus());
  }

  @Test
  public void srvCheckOverLimitRq() {
    SrvCheckOverLimitRq request = CheckOverLimitMapper.INSTANCE.srvCheckOverLimitRq(checkOverLimit);
    assertHeaderInfo(request.getHeaderInfo());
    Assert.assertNotNull(request.getSrvCheckOverLimitRqMessage());
    Assert.assertEquals(checkOverLimit.getUserLogin(),
        request.getSrvCheckOverLimitRqMessage().getUserLogin());
    Assert.assertTrue(request.getSrvCheckOverLimitRqMessage().isTobeIncreased());
    Assert.assertEquals(checkOverLimit.getAmount(),
        request.getSrvCheckOverLimitRqMessage().getAmount());
  }

  @Test
  public void testConvertSrvCheckOverLimitRs() {
    CheckOverLimit checkOverLimit = CheckOverLimitMapper.INSTANCE.convert(response);
    testWorkplace(checkOverLimit);
  }

  @Test
  public void testMultiAdapter() {
    ExternalEntity externalEntity = MultiAdapter.convert(response);
    Assert.assertNotNull(externalEntity);
    Assert.assertEquals(externalEntity.getClass(), ExternalEntityContainer.class);
  }
}