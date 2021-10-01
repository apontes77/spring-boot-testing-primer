package com.alexandrepontes.app.springboottestingprimer.services;

import java.math.BigDecimal;

public class PricingService {
    private final InventoryService inventoryService;

    public PricingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public BigDecimal calculatePrice(String productName) {
        if (inventoryService.isCurrentlyInStockOfCompetitor(productName)) {
            return new BigDecimal("99.99");
        }
        return new BigDecimal("149.99");
    }
}