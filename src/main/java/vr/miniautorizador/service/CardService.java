package vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vr.miniautorizador.domain.Card;
import vr.miniautorizador.repository.CardRepository;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createCard(Card card) {
        if (!cardRepository.findAll().contains(card)) {
            return cardRepository.insert(card);
        }
        throw new RuntimeException("CARTAO_EXISTENTE");
    }

    public Card getCardByNumber(String cardNumber) {
        return cardRepository.findById(cardNumber).orElseThrow(() -> new RuntimeException("CARTAO_INEXISTENTE"));
    }

}