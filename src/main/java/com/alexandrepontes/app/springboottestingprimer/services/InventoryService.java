package com.alexandrepontes.app.springboottestingprimer.services;

public class InventoryService {
    public boolean isCurrentlyInStockOfCompetitor(String productName) {
// Determine if product is in stock of competitor, e.g. HTTP call
        return false;
    }

    public boolean isAvailable(String productName) {
// Determine if product is available, e.g. database access
        return true;
    }
}