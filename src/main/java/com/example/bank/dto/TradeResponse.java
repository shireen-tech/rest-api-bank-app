package com.example.bank.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeResponse {
        @Getter
        @Setter
        private int tradeId;
        @Getter
        @Setter
        private String messageId;
        @Getter
        @Setter
        private double amount;
        @Getter
        @Setter
        private String valueDate;
        @Getter
        @Setter
        private String currency;
        @Getter
        @Setter
        private PayerParty payerParty;
        @Getter
        @Setter
        private ReceiverParty receiverParty;
        @Getter
        @Setter
        private String info;

}
