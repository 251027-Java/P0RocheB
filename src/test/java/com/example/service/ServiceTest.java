package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.repository.PostgreSQLRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    PostgreSQLRepository mockRepo;

    @InjectMocks
    Service service;

    @Test
    public void testIsNotation_ValidNotation_ReturnsTrue() {
        when(mockRepo.isNotation("e4")).thenReturn(true);
        assertTrue(service.isNotation("e4"));
    }

    @Test
    public void testIsNotation_InvalidNotation_ReturnsFalse() {
        when(mockRepo.isNotation("invalid")).thenReturn(false);
        assertFalse(service.isNotation("invalid"));
    }

    @Test
    public void testShowBestMove_CallsRepoMethod() {
        List<String> moves = List.of("e4", "e5");
        service.showBestMove(moves);
        verify(mockRepo, times(1)).getBestMove(moves);
    }

    @Test
    public void testHelp_PrintsHelpMessage() {
        assertDoesNotThrow(() -> service.help());
    }

    @Test
    public void testLoadNotations_CallsRepoCreateNotations() {
        doNothing().when(mockRepo).createNotations(any());
        service.loadNotations();
        verify(mockRepo, atLeastOnce()).createNotations(any());
    }

    @Test
    public void testLoadGames_CallsRepoCreateGames() {
        doNothing().when(mockRepo).createGame(any());
        service.loadGames("./src/main/resources/testGames.pgn");
        verify(mockRepo, atLeastOnce()).createGame(any());
    }
}

