package vr.miniautorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vr.miniautorizador.domain.Card;
import vr.miniautorizador.service.CardService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cartoes")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> postCard(@RequestBody Card card) {
        try {
            return ResponseEntity.status(201).body(cardService.createCard(card));
        } catch (RuntimeException e) {
            return ResponseEntity.status(422).body(card);
        }
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<Double> getCard(@PathVariable String cardNumber) {
        try {
            return ResponseEntity.ok().body(cardService.getCardByNumber(cardNumber).getBalance());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}