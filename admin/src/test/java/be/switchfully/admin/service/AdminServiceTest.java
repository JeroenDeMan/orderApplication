package be.switchfully.admin.service;

import be.switchfully.admin.business.entity.Admin;
import be.switchfully.admin.business.repository.AdminRepository;
import be.switchfully.admin.exceptions.AdminNotFoundException;
import be.switchfully.admin.service.dto.AdminDTO;
import be.switchfully.admin.service.mapper.AdminMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    @Test
    public void onCreationOfRepository_oneAdminIsAdded(){
        AdminService adminService = new AdminService(new AdminRepository(), new AdminMapper());

        Assertions.assertEquals(1, adminService.getAllAdmins().size());
    }

    @Test
    public void whenAdminIsNotFound_systemThrowsNotFoundException(){
        AdminService adminService = new AdminService(new AdminRepository(), new AdminMapper());
        Assertions.assertThrows(AdminNotFoundException.class, () -> adminService.getAdminById("12345"));
    }

    @Test
    public void adminAddedOnStart_equalsAdminReceivedById() {
        AdminService adminService = new AdminService(new AdminRepository(), new AdminMapper());
        AdminDTO adminDTO = adminService.getAllAdmins().get(0);

        assertEquals(adminDTO, adminService.getAdminById(adminDTO.getId()));
    }

}