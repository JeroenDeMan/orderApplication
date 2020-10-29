package be.switchfully.itemgroup.api;

import be.switchfully.itemgroup.service.ItemGroupService;
import be.switchfully.itemgroup.service.dto.ItemGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = "/api/itemGroups")
public class ItemGroupController {

    private ItemGroupService itemGroupService;

    @Autowired
    public ItemGroupController(ItemGroupService itemGroupService) {
        this.itemGroupService = itemGroupService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ItemGroupDTO creatItemGroup(@RequestBody ItemGroupDTO itemGroupDTO) {
        return itemGroupService.addItemGroup(itemGroupDTO);
    }

    @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ItemGroupDTO getItemGroupById(@PathVariable String id) {
        return itemGroupService.getItemGroupById(id);
    }
}
