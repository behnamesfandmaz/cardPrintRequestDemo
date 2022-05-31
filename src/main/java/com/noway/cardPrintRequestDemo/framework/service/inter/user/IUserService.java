package com.noway.cardPrintRequestDemo.framework.service.inter.user;

import com.noway.cardPrintRequestDemo.framework.dto.user.UserDTO;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import com.noway.cardPrintRequestDemo.framework.service.inter.IGenericService;
import org.springframework.ui.ModelMap;

public interface IUserService extends IGenericService<User, UserDTO> {

    void save(UserDTO user);

    UserDTO findByUsername(String username);
}
