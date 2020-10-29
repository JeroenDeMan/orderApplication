package be.switchfully.order.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemGroupKeysDTO {
    private List<String> itemGroupKeys;

    public ItemGroupKeysDTO(){
        itemGroupKeys = new ArrayList<>();
    }
}
