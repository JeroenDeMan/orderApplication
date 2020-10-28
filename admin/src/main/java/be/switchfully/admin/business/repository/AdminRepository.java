package be.switchfully.admin.business.repository;

import be.switchfully.admin.business.entity.Admin;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
public class AdminRepository {

    private Map<String, Admin> admins;

    public AdminRepository() {
        this.admins = new HashMap<>();
        addFirstAdmin();
    }

    private void addFirstAdmin() {
        Admin admin = new Admin("Jeroen De Man");
        admins.put(admin.getId(), admin);
    }
}
