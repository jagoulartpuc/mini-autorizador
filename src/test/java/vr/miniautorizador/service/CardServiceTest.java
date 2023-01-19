package vr.miniautorizador.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vr.miniautorizador.domain.Card;
import vr.miniautorizador.repository.CardRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    void createCard_shouldReturnCard_whenCardIsCreatedSuccessfully() {
        Card card = new Card("12345678","password");
        when(cardRepository.findAll()).thenReturn(Collections.emptyList());
        when(cardRepository.insert(card)).thenReturn(card);

        Card result = cardService.createCard(card);

        assertEquals(card, result);
        verify(cardRepository).insert(card);
    }

    @Test
    void createCard_shouldThrowException_whenCardAlreadyExists() {
        Card card = new Card("12345678","password");
        when(cardRepository.findAll()).thenReturn(Collections.singletonList(card));

        Throwable thrown = assertThrows(RuntimeException.class, () -> cardService.createCard(card));
        assertTrue(thrown.getMessage().contentEquals("CARTAO_EXISTENTE"));

        verify(cardRepository, never()).insert(anyCollection());
    }

    @Test
    void getCardByNumber_shouldReturnCard_whenCardExists() {
        Card card = new Card("12345678","password");
        when(cardRepository.findById("12345678")).thenReturn(Optional.of(card));

        Card result = cardService.getCardByNumber("12345678");

        assertEquals(card, result);
    }

    @Test
    void getCardByNumber_shouldThrowException_whenCardDoesNotExist() {
        when(cardRepository.findById("12345678")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> cardService.getCardByNumber("12345678"));
    }
}

