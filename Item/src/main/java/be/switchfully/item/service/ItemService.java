package be.switchfully.item.service;

import be.switchfully.item.business.entity.Item;
import be.switchfully.item.business.repository.ItemRepository;
import be.switchfully.item.exceptions.NotAuthorizedException;
import be.switchfully.item.service.dto.AdminDTO;
import be.switchfully.item.service.dto.ItemDTO;
import be.switchfully.item.service.mapper.ItemMapper;
import be.switchfully.item.util.UrgencyIndicator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Data
public class ItemService {
    public static final String ADMIN_URL = "http://localhost:7080/api/admins/";
    private AdminDTO admin;
    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    private boolean isAdminLoggedIn() {
        return admin != null;

    }

    public String logIn(String id) {
        RestTemplate rtAdmin = new RestTemplate();
        AdminDTO adminDTO = rtAdmin.getForObject(ADMIN_URL + id, AdminDTO.class);
        if (adminDTO == null) return "login failed";
        admin = adminDTO;
        return "logged in";
    }

    public ItemDTO addNewItem(ItemDTO itemDTO) {
        if (!isAdminLoggedIn()) throw new NotAuthorizedException("add a new item");
        Item item = itemMapper.toEntity(itemDTO);

        itemRepository.getItems().put(item.getId(), item);

        return itemMapper.toDto(item);
    }

    public ItemDTO getItemById(String id) {
        return itemMapper.toDto(itemRepository.getItems().get(id));
    }


    public ItemDTO updateItem(String id, ItemDTO itemDTO){
        Item result = itemRepository.getItems().get(id);

        result.setName(itemDTO.getName());
        result.setDescription(itemDTO.getDescription());
        result.setPrice(itemDTO.getPrice());
        result.setAmount(itemDTO.getAmount());
        return itemMapper.toDto(result);
    }

    public List<ItemDTO> getItemsSortedByStockUrgency () {
        return itemRepository.getItems().values().stream().sorted().map(item -> itemMapper.toDto(item)).collect(Collectors.toList());
    }

    public List<ItemDTO> getItemsFilterdByResupplyUrgency(UrgencyIndicator value) {
        return itemRepository.getItems()
                .values()
                .stream()
                .filter(item -> item.getResupplyEmergency().equals(value))
                .map(item -> itemMapper.toDto(item))
                .collect(Collectors.toList());
    }
}
