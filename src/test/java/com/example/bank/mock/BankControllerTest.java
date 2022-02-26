package com.example.bank.mock;

import com.example.bank.controller.BankController;
import com.example.bank.dto.PayerParty;
import com.example.bank.dto.ReceiverParty;
import com.example.bank.dto.TradeResponse;
import com.example.bank.model.SettlementMsg;
import com.example.bank.service.BankService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@WebMvcTest(BankController.class)
public class BankControllerTest {

    @MockBean
    BankService bankService;

    @Test
    public void testCreateTrade() {
        SettlementMsg data1 = new SettlementMsg(16846548,"OCBC_DBS_1",12894.65,"USD",
                "20022020");
        TradeResponse mockResponse = new TradeResponse(16846548, "e8a57dc0-2119-49a4-85fe-5e94415b2cad",
                12894.65, "20022020", "USD",
                new PayerParty("438421", "OCBCSGSGXXX"),
                new ReceiverParty("05461368", "DBSSGB2LXXX"), "/BNF:FFC-4697132");

        Mockito.when(bankService.createTrade(data1)).thenReturn(mockResponse);
        String expected = "{\"tradeId\":16846548,\"messageId\":\"e8a57dc0-2119-49a4-85fe-5e94415b2cad\"," +
               "\"amount\":12894.65,\"valueDate\":\"20022020\",\"currency\":\"USD\"," +
                "\"payerParty\":{\"accountNumber\":\"438421\",\"bankCode\":\"OCBCSGSGXXX\"}," +
        "\"receiverParty\":{\"accountNumber\":\"05461368\",\"bankCode\":\"DBSSGB2LXXX\"}," +
        "\"info\":\"/BNF:FFC-4697132\"}";
        BankController underTest = new BankController();
        underTest.setBankService(bankService);
       ResponseEntity responseData = underTest.createTrade(data1);
        Gson gson = new Gson();
       assertEquals(HttpStatus.OK.toString(),expected,gson.toJson(responseData.getBody()));

    }

    @Test
    public void statusCreateTrade() {
        SettlementMsg data1 = new SettlementMsg("OCBC_DBS_1",12894.65,"USD",
                "20022020");
        BankController controller = new BankController();
        ResponseEntity responseEntity = controller.createTrade(data1);
        assertEquals("trade id required ",HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetTrade() {
        TradeResponse mockResponse = new TradeResponse(16846548, "e8a57dc0-2119-49a4-85fe-5e94415b2cad",
                12894.65, "20022020", "USD",
                new PayerParty("438421", "OCBCSGSGXXX"),
                new ReceiverParty("05461368", "DBSSGB2LXXX"), "/BNF:FFC-4697132");
        String expected = "{\"tradeId\":16846548,\"messageId\":\"e8a57dc0-2119-49a4-85fe-5e94415b2cad\"," +
                "\"amount\":12894.65,\"valueDate\":\"20022020\",\"currency\":\"USD\"," +
                "\"payerParty\":{\"accountNumber\":\"438421\",\"bankCode\":\"OCBCSGSGXXX\"}," +
                "\"receiverParty\":{\"accountNumber\":\"05461368\",\"bankCode\":\"DBSSGB2LXXX\"}," +
                "\"info\":\"/BNF:FFC-4697132\"}";
        BankController controller = new BankController();
        controller.setBankService(bankService);
        Mockito.when(bankService.getTrade(16846548)).thenReturn(mockResponse);
        ResponseEntity responseEntity = controller.getTrade(16846548);
        Gson gson = new Gson();
        assertEquals("true", expected, gson.toJson(responseEntity.getBody()));
        assertEquals("Status ok", HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testInvalidTradeId () {
        BankController controller = new BankController();
        controller.setBankService(bankService);
        Mockito.when(bankService.getTrade(1)).thenReturn(new TradeResponse());
        ResponseEntity responseEntity = controller.getTrade(1);
        assertEquals("not found",HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }
}
