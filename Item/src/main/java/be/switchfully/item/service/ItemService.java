package be.switchfully.item.service;

import be.switchfully.item.business.entity.Item;
import be.switchfully.item.business.repository.ItemRepository;
import be.switchfully.item.service.dto.AdminDTO;
import be.switchfully.item.service.dto.ItemDTO;
import be.switchfully.item.service.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;

@Service
public class ItemService {

    private AdminDTO admin;
    private ItemRepository itemRepository;
    private ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    private boolean isAdminLoggedIn(){
        //return admin != null;
        return true;
    }

    public String logIn(String id) {
        //admin = restcontroller;
        return "logged in";
    }

    public ItemDTO addNewItem(ItemDTO itemDTO) {
        if(!isAdminLoggedIn()) throw new IllegalArgumentException();
        Item item = itemMapper.toEntity(itemDTO);
        itemRepository.getItems().put(item.getId(), item);

        return itemMapper.toDto(item);
    }

}
