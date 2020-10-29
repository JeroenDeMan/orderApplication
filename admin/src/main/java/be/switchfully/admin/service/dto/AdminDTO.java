package be.switchfully.admin.service.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class AdminDTO {

    private String id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(id, adminDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
