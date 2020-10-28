package be.switchfully.itemgroup.business.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ItemGroup {
    public static final long SHIPPING_ITEM_OUT_OF_STOCK = 7L;
    public static final long SHIPPING_ITEM_IN_STOCK = 1L;

    private final String groupId;
    private final String itemId;
    private int amount;
    private LocalDate shippingDate;

    public ItemGroup(String itemId, int amount) {
        this.groupId = UUID.randomUUID().toString();
        this.itemId = itemId;
        this.amount = amount;
    }

    public void itemOutOfStockSetShipping(){
        this.shippingDate = LocalDate.now().plusDays(SHIPPING_ITEM_OUT_OF_STOCK);
    }

    public void itemInStockSetShipping(){
        this.shippingDate = LocalDate.now().plusDays(SHIPPING_ITEM_IN_STOCK);
    }
}
