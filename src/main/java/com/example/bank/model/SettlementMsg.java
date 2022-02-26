package com.example.bank.model;

import lombok.*;

@NoArgsConstructor
@ToString
public class SettlementMsg {
    @Getter
    @Setter
    private int tradeId;
    @Getter
    @Setter
    private String ssiCode;
    @Getter
    @Setter
    private String payerAcc;
    @Getter
    @Setter
    private String payerBank;
    @Getter
    @Setter
    private String receiverAcc;
    @Getter
    @Setter
    private String receiverBank;
    @Getter
    @Setter
    private double amount;
    @Getter
    @Setter
    private String currency;
    @Getter
    @Setter
    private String valueDate;
    @Getter
    @Setter
    private String info;

    public SettlementMsg(String ssiCode, String payerAcc, String payerBank, String receiverAcc, String receiverBank, String info) {
        this.ssiCode = ssiCode;
        this.payerAcc = payerAcc;
        this.payerBank = payerBank;
        this.receiverAcc = receiverAcc;
        this.receiverBank = receiverBank;
        this.info = info;
    }

    public SettlementMsg(int tradeId, String ssiCode, double amount, String currency, String date) {
        this.ssiCode = ssiCode;
        this.tradeId = tradeId;
        this.amount = amount;
        this.currency = currency;
        this.valueDate = date;
    }

    public SettlementMsg(String ssiCode, double amount, String currency, String date) {
        this.ssiCode = ssiCode;
        this.amount = amount;
        this.currency = currency;
        this.valueDate = date;
    }

//    public void addAll(SettlementMsg data) {
//        setAmount(data.getAmount());
//        setCurrency(data.getCurrency());
//        setValueDate(data.getValueDate());
//        setInfo(data.getInfo());
//    }
}
