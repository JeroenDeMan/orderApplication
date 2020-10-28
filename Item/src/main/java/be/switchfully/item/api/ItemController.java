package be.switchfully.item.api;

import be.switchfully.item.business.entity.Item;
import be.switchfully.item.service.ItemService;
import be.switchfully.item.service.dto.AdminDTO;
import be.switchfully.item.service.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/login/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String logIn(@PathVariable String id){
        return itemService.logIn(id);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDTO getItemById(@PathVariable String id){
        return itemService.getItemById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO addNewItem(@RequestBody ItemDTO itemDTO) {
        return itemService.addNewItem(itemDTO);
    }
}
