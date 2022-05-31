package com.noway.cardPrintRequestDemo.framework.entity.user;

import com.noway.cardPrintRequestDemo.framework.dto.user.UserDTO;
import com.noway.cardPrintRequestDemo.framework.entity.BaseEntity;
import com.noway.cardPrintRequestDemo.framework.entity.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends BaseEntity<User, UserDTO> {

    @Column(name = "c_username")
    private String username;
    @Column(name = "c_password")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "t_user_c_id"), inverseJoinColumns = @JoinColumn(name = "t_role_c_id"))
    private Set<Role> roles;


    @Override
    public User convertDataTransferObjectToEntity(UserDTO dto) {
        this.setId(dto.getId());
        this.setUsername(dto.getUsername());
        this.setPassword(dto.getPassword());
        this.setEnabled(dto.getEnabled());
        this.setPublishingDate(dto.getPublishingDate());
        this.setVersion(dto.getVersion());
        this.roles = new HashSet<>();
        this.setRoles(dto.getRoles().stream()
                .map(roleDTO -> new Role().convertDataTransferObjectToEntity(roleDTO)).collect(Collectors.toSet()));
        return this;
    }

    @Override
    public UserDTO getDataTransferObject() {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(this.getId());
        userDTO.setUsername(this.getUsername());
        userDTO.setPassword(this.getPassword());
        userDTO.setEnabled(this.getEnabled());
        userDTO.setPublishingDate(this.getPublishingDate());
        userDTO.setVersion(this.getVersion());
        userDTO.setRoles(this.roles.stream()
                .map(Role::getDataTransferObject).collect(Collectors.toSet()));
        return userDTO;
    }
}