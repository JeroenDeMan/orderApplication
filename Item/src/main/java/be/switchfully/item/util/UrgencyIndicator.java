package be.switchfully.item.util;

public enum UrgencyIndicator {
    STOCK_LOW("Stock is low"),
    STOCK_MEDIUM("Stock is medium"),
    STOCK_HIGH("Stock is high");

    String stockLevel;

    UrgencyIndicator(String stockLevel) {
        this.stockLevel = stockLevel;
    }

    public static UrgencyIndicator setUrgency(int amount) {
        if (amount < 5) return STOCK_LOW;
        if (amount < 10) return STOCK_MEDIUM;
        return STOCK_HIGH;
    }

    @Override
    public String toString() {
        return stockLevel;
    }
}
