package vr.miniautorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vr.miniautorizador.domain.Transaction;
import vr.miniautorizador.service.TransactionService;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> postTransaction(@RequestBody Transaction transaction) {
        try {
            return ResponseEntity.status(201).body(transactionService.doTransaction(transaction));
        } catch (RuntimeException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }
}
