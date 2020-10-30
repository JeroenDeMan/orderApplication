package be.switchfully.admin.service;

import be.switchfully.admin.business.repository.AdminRepository;
import be.switchfully.admin.exceptions.AdminNotFoundException;
import be.switchfully.admin.service.dto.AdminDTO;
import be.switchfully.admin.service.mapper.AdminMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class AdminService {

    private AdminRepository adminRepository;
    private AdminMapper adminMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public List<AdminDTO> getAllAdmins() {
        return adminRepository.getAdmins()
                .values()
                .stream()
                .map(admin -> adminMapper.toDTO(admin))
                .collect(Collectors.toList());
    }

    public AdminDTO getAdminById(String id) {
        if (!adminRepository.getAdmins().containsKey(id)) throw new AdminNotFoundException(id);
        return adminMapper.toDTO(adminRepository.getAdmins().get(id));
    }
}
