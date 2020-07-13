package ru.philit.ufs.esb.mock.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.philit.ufs.esb.MessageProcessor;
import ru.philit.ufs.esb.mock.client.EsbClient;
import ru.philit.ufs.model.cache.MockCache;
import ru.philit.ufs.model.converter.esb.JaxbConverter;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderStatusType;
import ru.philit.ufs.model.entity.esb.asfs.CashOrderType;
import ru.philit.ufs.model.entity.esb.asfs.HeaderInfoType;
import ru.philit.ufs.model.entity.esb.asfs.LimitStatusType;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCheckOverLimitRs.SrvCheckOverLimitRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateCashOrderRs.SrvCreateCashOrderRsMessage.KO1.CashSymbols;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage.WorkPlaceOperationTypeLimit;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetWorkPlaceInfoRs.SrvGetWorkPlaceInfoRsMessage.WorkPlaceOperationTypeLimit.OperationTypeLimitItem;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdStCashOrderRs.SrvUpdCashOrderRsMessage;

/**
 * Сервис на обработку запросов к АСФС.
 */
@Service
public class AsfsMockService extends CommonMockService implements MessageProcessor {

  private static final String CONTEXT_PATH = "ru.philit.ufs.model.entity.esb.asfs";

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final EsbClient esbClient;
  private final MockCache mockCache;

  private final JaxbConverter jaxbConverter = new JaxbConverter(CONTEXT_PATH);

  @Autowired
  public AsfsMockService(EsbClient esbClient, MockCache mockCache) {
    this.esbClient = esbClient;
    this.mockCache = mockCache;
  }

  @PostConstruct
  public void init() {
    esbClient.addMessageProcessor(this);
    logger.info("{} started", this.getClass().getSimpleName());
  }

  @Override
  public boolean processMessage(String requestMessage) {
    try {
      Object request = jaxbConverter.getObject(requestMessage);
      logger.debug("Received message: {}", request);
      if (request != null) {
        if (request instanceof SrvCreateCashOrderRq) {
          sendResponse(getResponse((SrvCreateCashOrderRq) request));
        } else if (request instanceof SrvUpdStCashOrderRq) {
          sendResponse(getResponse((SrvUpdStCashOrderRq) request));
        } else if (request instanceof SrvCheckOverLimitRq) {
          sendResponse(getResponse((SrvCheckOverLimitRq) request));
        } else if (request instanceof SrvGetWorkPlaceInfoRq) {
          sendResponse(getResponse((SrvGetWorkPlaceInfoRq) request));
        }

        return true;
      }
    } catch (JAXBException e) {
      // this message can not be processed this processor
      logger.trace("this message can not be processed this processor", e);
    }
    return false;
  }

  private void sendResponse(Object responseObject) throws JAXBException {
    String responseMessage = jaxbConverter.getXml(responseObject);
    esbClient.sendMessage(responseMessage);
  }

  private SrvCreateCashOrderRs getResponse(SrvCreateCashOrderRq request) {
    SrvCreateCashOrderRs response = new SrvCreateCashOrderRs();
    response.setHeaderInfo(copyHeaderInfo(request.getHeaderInfo()));
    response.setSrvCreateCashOrderRsMessage(new SrvCreateCashOrderRsMessage());

    KO1 ko1 = new KO1();
    ko1.setResponseCode("ResponseCode");
    ko1.setResponseMsg("ResponseMsg");
    ko1.setCashOrderId("CashOrderId");
    ko1.setCashOrderINum("CashOrderINum");
    ko1.setCashOrderStatus(CashOrderStatusType.CREATED);
    ko1.setCashOrderType(CashOrderType.KO_1);
    ko1.setCreatedDttm(xmlCalendar(2020, 7, 7, 07, 7));
    ko1.setOperationId("OperationId");
    ko1.setRepFIO("RepFIO");
    ko1.setLegalEntityShortName("LegalEntityShortName");
    ko1.setINN("INN");
    ko1.setAmount(BigDecimal.valueOf(777L));
    ko1.setAccountId("AccountId");
    ko1.setCashSymbols(new CashSymbols());
    ko1.setSenderBank("SenderBank");
    ko1.setSenderBankBIC("SenderBankBIC");
    ko1.setRecipientBank("RecipientBank");
    ko1.setRecipientBankBIC("RecipientBankBIC");
    ko1.setClientTypeFK(true);
    ko1.setFDestLEName("FDestLEName");
    ko1.setOperatorPosition("OperatorPosition");
    ko1.setUserFullName("UserFullName");
    ko1.setUserPosition("UserPosition");
    response.getSrvCreateCashOrderRsMessage().setKO1(ko1);
    return response;
  }

  private SrvUpdStCashOrderRs getResponse(SrvUpdStCashOrderRq request) {
    SrvUpdStCashOrderRs response = new SrvUpdStCashOrderRs();
    response.setHeaderInfo(copyHeaderInfo(request.getHeaderInfo()));
    response.setSrvUpdCashOrderRsMessage(new SrvUpdCashOrderRsMessage());
    response.getSrvUpdCashOrderRsMessage().setResponseCode("ResponseCode");
    response.getSrvUpdCashOrderRsMessage().setResponseMsg("responseMsg");
    response.getSrvUpdCashOrderRsMessage().setCashOrderId("cashOrderId");
    response.getSrvUpdCashOrderRsMessage().setCashOrderINum("cashOrderINum");
    response.getSrvUpdCashOrderRsMessage().setCashOrderStatus(CashOrderStatusType.CREATED);
    response.getSrvUpdCashOrderRsMessage().setCashOrderType(CashOrderType.KO_1);
    return response;
  }

  private SrvCheckOverLimitRs getResponse(SrvCheckOverLimitRq request) {
    SrvCheckOverLimitRs response = new SrvCheckOverLimitRs();
    response.setHeaderInfo(copyHeaderInfo(request.getHeaderInfo()));
    response.setSrvCheckOverLimitRsMessage(new SrvCheckOverLimitRsMessage());

    response.getSrvCheckOverLimitRsMessage().setResponseCode("ResponseCode");
    response.getSrvCheckOverLimitRsMessage().setStatus(LimitStatusType.LIMIT_PASSED);
    return response;
  }

  private SrvGetWorkPlaceInfoRs getResponse(SrvGetWorkPlaceInfoRq request) {
    SrvGetWorkPlaceInfoRs response = new SrvGetWorkPlaceInfoRs();
    response.setHeaderInfo(copyHeaderInfo(request.getHeaderInfo()));
    response.setSrvGetWorkPlaceInfoRsMessage(new SrvGetWorkPlaceInfoRsMessage());

    response.getSrvGetWorkPlaceInfoRsMessage().setWorkPlaceType(BigInteger.valueOf(4444));
    response.getSrvGetWorkPlaceInfoRsMessage().setCashboxOnBoard(true);
    response.getSrvGetWorkPlaceInfoRsMessage().setSubbranchCode("SubbranchCode");
    response.getSrvGetWorkPlaceInfoRsMessage().setCashboxOnBoardDevice("CashboxOnBoardDevice");
    response.getSrvGetWorkPlaceInfoRsMessage().setCashboxDeviceType("CashboxDeviceType");
    response.getSrvGetWorkPlaceInfoRsMessage().setCurrentCurrencyType("CurrentCurrencyType");
    response.getSrvGetWorkPlaceInfoRsMessage().setAmount(BigDecimal.valueOf(44.44));
    response.getSrvGetWorkPlaceInfoRsMessage().setWorkPlaceLimit(BigDecimal.valueOf(44444.4));
    response.getSrvGetWorkPlaceInfoRsMessage().setWorkPlaceOperationTypeLimit(
        new WorkPlaceOperationTypeLimit());
    OperationTypeLimitItem operationTypeLimitItem = new OperationTypeLimitItem();
    operationTypeLimitItem.setOperationCategory(BigInteger.valueOf(4444));
    operationTypeLimitItem.setOperationLimit(BigDecimal.valueOf(44.44));
    response.getSrvGetWorkPlaceInfoRsMessage().getWorkPlaceOperationTypeLimit()
        .getOperationTypeLimitItem().add(operationTypeLimitItem);
    return response;
  }

  private HeaderInfoType copyHeaderInfo(HeaderInfoType headerInfo0) {
    HeaderInfoType headerInfo = new HeaderInfoType();
    headerInfo.setRqUID(headerInfo0.getRqUID());
    headerInfo.setRqTm(headerInfo0.getRqTm());
    headerInfo.setSpName(headerInfo0.getSystemId());
    headerInfo.setSystemId(headerInfo0.getSpName());
    return headerInfo;
  }
}
