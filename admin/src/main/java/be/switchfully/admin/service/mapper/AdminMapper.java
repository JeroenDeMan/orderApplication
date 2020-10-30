package be.switchfully.admin.service.mapper;

import be.switchfully.admin.business.entity.Admin;
import be.switchfully.admin.service.dto.AdminDTO;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public Admin toEntity(AdminDTO adminDTO) {
        return new Admin(adminDTO.getName());
    }

    public AdminDTO toDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setName(admin.getName());

        return adminDTO;
    }
}
