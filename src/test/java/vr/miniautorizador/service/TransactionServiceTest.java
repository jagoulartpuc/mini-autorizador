package vr.miniautorizador.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vr.miniautorizador.domain.Card;
import vr.miniautorizador.domain.Transaction;
import vr.miniautorizador.repository.CardRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void doTransaction_shouldReturnOK_whenTransactionIsSuccessful() {
        Card card = new Card("12345678", "password");
        Transaction transaction = new Transaction("12345678", "password", 50);
        when(cardRepository.findById("12345678")).thenReturn(Optional.of(card));

        String result = transactionService.doTransaction(transaction);

        assertEquals("OK", result);
        assertEquals(450, card.getBalance());
        verify(cardRepository).save(card);
    }

    @Test
    void doTransaction_shouldThrowException_whenCardDoesNotExist() {
        Transaction transaction = new Transaction("12345678", "password", 50);
        when(cardRepository.findById("12345678")).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(RuntimeException.class, () -> transactionService.doTransaction(transaction));

        assertTrue(thrown.getMessage().contentEquals("CARTAO_INEXISTENTE"));
        verify(cardRepository, never()).save(any());
    }

    @Test
    void doTransaction_shouldThrowException_whenInsufficientBalance() {
        Card card = new Card("12345678", "password");
        Transaction transaction = new Transaction("12345678", "password", 600);
        when(cardRepository.findById("12345678")).thenReturn(Optional.of(card));

        Throwable thrown = assertThrows(RuntimeException.class, () -> transactionService.doTransaction(transaction));

        assertTrue(thrown.getMessage().contentEquals("SALDO_INSUFICIENTE"));
        verify(cardRepository, never()).save(any());
    }

    @Test
    void doTransaction_shouldThrowException_whenInvalidPassword() {
        Card card = new Card("12345678", "password");
        Transaction transaction = new Transaction("12345678", "wrongpassword", 50);
        when(cardRepository.findById("12345678")).thenReturn(Optional.of(card));

        Throwable thrown = assertThrows(RuntimeException.class, () -> transactionService.doTransaction(transaction));

        assertTrue(thrown.getMessage().contentEquals("SENHA_INVALIDA"));
        verify(cardRepository, never()).save(any());
    }
}

