package com.noway.cardPrintRequestDemo.framework.service.impl.user;

import com.noway.cardPrintRequestDemo.framework.dto.user.UserDTO;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import com.noway.cardPrintRequestDemo.framework.repository.role.RoleRepository;
import com.noway.cardPrintRequestDemo.framework.repository.user.UserRepository;
import com.noway.cardPrintRequestDemo.framework.service.impl.GenericServiceImpl;
import com.noway.cardPrintRequestDemo.framework.service.inter.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserDTO> implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void save(UserDTO user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.getRoles().add(roleRepository.findByName("user").getDataTransferObject());
        super.save(new User().convertDataTransferObjectToEntity(user));
    }

    @Override
//    @Cacheable(value = "UserDTO", key = "#username")
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user.getDataTransferObject() : null;
    }

}
