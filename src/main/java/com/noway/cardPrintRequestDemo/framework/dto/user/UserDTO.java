package com.noway.cardPrintRequestDemo.framework.dto.user;

import com.noway.cardPrintRequestDemo.framework.dto.BaseDTO;
import com.noway.cardPrintRequestDemo.framework.dto.role.RoleDTO;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO extends BaseDTO {
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<RoleDTO> roles=new HashSet<>();

}
