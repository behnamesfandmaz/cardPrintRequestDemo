package com.noway.cardPrintRequestDemo.framework.entity.role;

import com.noway.cardPrintRequestDemo.framework.dto.role.RoleDTO;
import com.noway.cardPrintRequestDemo.framework.entity.BaseEntity;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role extends BaseEntity<Role, RoleDTO> {
    @Column(name = "c_name", unique = true, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;


    @Override
    public Role convertDataTransferObjectToEntity(RoleDTO dto) {
        this.setId(dto.getId());
        this.setEnabled(dto.getEnabled());
        this.setPublishingDate(dto.getPublishingDate());
        this.setName(dto.getName());
        this.setVersion(dto.getVersion());
        return this;
    }

    @Override
    public RoleDTO getDataTransferObject() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(this.getId());
        roleDTO.setName(this.getName());
        roleDTO.setEnabled(this.getEnabled());
        roleDTO.setPublishingDate(this.getPublishingDate());
        roleDTO.setVersion(this.getVersion());
        return roleDTO;
    }
}
