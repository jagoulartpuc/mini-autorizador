package vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vr.miniautorizador.domain.Card;
import vr.miniautorizador.domain.Transaction;
import vr.miniautorizador.repository.CardRepository;

@Service
public class TransactionService {

    @Autowired
    private CardRepository cardRepository;

    public synchronized String doTransaction(Transaction transaction) {
        Card card = cardRepository.findById(transaction.getCardNumber()).orElseThrow(() -> new RuntimeException("CARTAO_INEXISTENTE"));
        if (transaction.getValue() > card.getBalance()) {
            throw new RuntimeException("SALDO_INSUFICIENTE");
        }

        if (!transaction.getPassword().equals(card.getPassword())) {
            throw new RuntimeException("SENHA_INVALIDA");
        }

        card.setBalance(card.getBalance() - transaction.getValue());
        cardRepository.save(card);
        return "OK";
    }
}
