package com.example.bank.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiverParty {
    @Getter
    @Setter
    private String accountNumber;
    @Getter
    @Setter
    private String bankCode;
}
