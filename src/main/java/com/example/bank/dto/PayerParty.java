package com.example.bank.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayerParty {
    @Getter
    @Setter
    private String accountNumber;
    @Getter
    @Setter
    private String bankCode;
}
