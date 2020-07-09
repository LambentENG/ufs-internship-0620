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
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs.SrvCheckOverLimitRsMessage;

public class CheckOverLimitAdapterTest extends AsfsAdapterBaseTest {

  private CheckOverLimit checkOverLimit;
  private SrvCheckOverLimitRs response;

  /**
   * Set up test data.
   */
  @Before
  public void setUp() {
    checkOverLimit = new CheckOverLimit();

    response = new SrvCheckOverLimitRs();
    response.setHeaderInfo(headerInfo());
    response.setSrvCheckOverLimitRsMessage(new SrvCheckOverLimitRsMessage());
    response.getSrvCheckOverLimitRsMessage().setResponseCode(TestData.RESPONSE_CODE);
    response.getSrvCheckOverLimitRsMessage().setStatus(LimitStatusType.LIMIT_PASSED);
  }

  @Test
  public void testConvertSrvCheckOverLimitRs() {
    ExternalEntityContainer<Boolean> container = CheckOverLimitAdapter.convert(response);
    Assert.assertTrue(container.getData());
  }

  @Test
  public void testMultiAdapter() {
    ExternalEntity externalEntity = MultiAdapter.convert(response);
    Assert.assertNotNull(externalEntity);
    Assert.assertEquals(externalEntity.getClass(), ExternalEntityContainer.class);
  }
}