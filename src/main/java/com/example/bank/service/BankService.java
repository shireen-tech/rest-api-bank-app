package com.example.bank.service;

import com.example.bank.dto.PayerParty;
import com.example.bank.dto.ReceiverParty;
import com.example.bank.dto.TradeResponse;
import com.example.bank.model.SettlementMsg;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankService {

    TradeResponse tradeResponse;

    Map<String, SettlementMsg> data = new HashMap<>();
    Map<String, SettlementMsg> someMoreData = new HashMap<>();
    String messageId = "e8a57dc0-2119-49a4-85fe-5e94415b2cad";

    public void createData() {
        data.put("DBS_OCBC_1",new SettlementMsg("DBS_OCBC_1","05461368","DBSSGB2LXXX","438421","OCBCSGSGXXX","BNF:PAY CLIENT"));
        data.put("OCBC_DBS_1", new SettlementMsg("OCBC_DBS_1","438421","OCBCSGSGXXX","05461368","DBSSGB2LXXX","BNF:FFC-4697132"));
        data.put("OCBC_DBS_2", new SettlementMsg("OCBC_DBS_2","438421","OCBCSGSGXXX","05461369","DBSSSGSGXXX","BNF:FFC-482315"));
        data.put("DBS_SCB", new SettlementMsg("DBS_SCB","185586","DBSSSGSGXXX","1868422","SCBLAU2SXXX","RFB:Test payment"));
        data.put("CITI_GS", new SettlementMsg("CITI_GS","00454983","CITIGB2LXXX","48486414","GSCMUS33XXX","48486414"));
    }
    public TradeResponse getTrade(int tradeId) {
        for (Map.Entry<String,SettlementMsg> entry : someMoreData.entrySet()) {
            int id = entry.getValue().getTradeId();
            if(id == tradeId) {
                for(Map.Entry<String,SettlementMsg> values : data.entrySet()) {
                    if (values.getValue().getSsiCode().equals(entry.getValue().getSsiCode())){
                        tradeResponse = new TradeResponse(entry.getValue().getTradeId(), messageId, entry.getValue().getAmount(),
                                entry.getValue().getValueDate(),entry.getValue().getCurrency(),
                                new PayerParty(values.getValue().getPayerAcc(), values.getValue().getPayerBank()),
                                new ReceiverParty(values.getValue().getReceiverAcc(),values.getValue().getReceiverBank()),
                                values.getValue().getInfo());
                    }

                }

            }
        }
       return tradeResponse;
    }

    public TradeResponse createTrade(SettlementMsg msg) {
        createData();

        for (Map.Entry<String,SettlementMsg> entry : data.entrySet()) {
            if (data.containsKey(msg.getSsiCode())) {
                someMoreData.put(msg.getSsiCode(), new SettlementMsg(msg.getTradeId(), msg.getSsiCode(), msg.getAmount(), msg.getCurrency(),
                        msg.getValueDate()));
                tradeResponse = new TradeResponse(msg.getTradeId(), messageId, msg.getAmount(), msg.getValueDate(), msg.getCurrency(),
                         new PayerParty(entry.getValue().getPayerAcc(), entry.getValue().getPayerBank()),
                         new ReceiverParty(entry.getValue().getReceiverAcc(), entry.getValue().getReceiverBank()),
                         entry.getValue().getInfo());
                break;
            }
        }

        return tradeResponse ;
    }
}
