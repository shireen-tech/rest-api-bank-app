package com.example.bank.controller;

import com.example.bank.dto.TradeResponse;
import com.example.bank.model.SettlementMsg;
import com.example.bank.service.BankService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping(path = {"/bank"})
@OpenAPIDefinition(info = @Info(title = "Bank App"))
public class BankController {

    @Autowired
    @Getter
    @Setter
    private BankService bankService;

    @Operation(summary = "insert the trade detail")
    @PostMapping(path = {"/create"})
    public ResponseEntity<?> createTrade(@RequestBody SettlementMsg data) {
        if(data.getSsiCode() == null || data.getSsiCode().isEmpty()) {
            return ResponseEntity.badRequest().body("ssi code field is required");
        }
        if(data.getTradeId() == 0) {
            return ResponseEntity.badRequest().body("trade id field is required");
        }
        if(data.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("amount field is required");
        }

        if(data.getCurrency().isEmpty()) {
            return ResponseEntity.badRequest().body("currency field is required");
        }
       TradeResponse tradeResponse = bankService.createTrade(data);

       return ResponseEntity.ok().body(tradeResponse);
    }

    @Operation(summary = "fetch the trade detail using trade_id")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTrade(@PathVariable("id") int tradeId) {
        if(tradeId < 1) {
            return ResponseEntity.badRequest().body("invalid trade id");
        }
       Optional<TradeResponse> data = Optional.ofNullable(bankService.getTrade(tradeId));
       if (data.get().getTradeId() != tradeId) {
           return ResponseEntity.notFound().build();
       }else {
           return ResponseEntity.ok().body(data.get());
       }
    }
}
