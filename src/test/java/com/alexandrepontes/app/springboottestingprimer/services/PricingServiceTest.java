package com.alexandrepontes.app.springboottestingprimer.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private PricingService pricingService;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void shouldReturnHigherPriceIfProductIsInStockOfCompetitor() {
        String productName = "MacBook";

        //aqui você declara o cenário que você deseja que o teste valide!
        when(inventoryService.isCurrentlyInStockOfCompetitor(anyString()))
                .thenReturn(false);

        //AGORA você implementa de fato o cenário que você quer que seja validado!
        final BigDecimal bigDecimal = pricingService.calculatePrice(productName);

        verify(inventoryService).isCurrentlyInStockOfCompetitor(stringArgumentCaptor.capture());

        assertEquals(new BigDecimal("149,99"), bigDecimal);
        assertEquals(new BigDecimal("149.99"), bigDecimal);
    }

    @Test
    void shouldThrowExceptionWhenCheckingForAvailability() {
        when(inventoryService.isCurrentlyInStockOfCompetitor("MacBook"))
                .thenThrow(new RuntimeException("Network error"));

        assertThrows(RuntimeException.class, () -> pricingService.calculatePrice("MacBook"));
    }

    @Test
    void mockWithThenAnswer() {
        when(inventoryService.isCurrentlyInStockOfCompetitor(any(String.class))).thenAnswer(invocationOnMock -> {
            final String productName = invocationOnMock.getArgument(0);
            return productName.contains("Mac");
        });

        final BigDecimal result = pricingService.calculatePrice("MacBook");
        assertEquals(new BigDecimal("99.90"), result);
    }
}
