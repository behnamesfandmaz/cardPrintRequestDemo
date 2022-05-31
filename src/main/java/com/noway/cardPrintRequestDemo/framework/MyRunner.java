package com.noway.cardPrintRequestDemo.framework;

import com.noway.cardPrintRequestDemo.cardPrintRequest.service.impl.CardPrintRequestServiceImpl;
import com.noway.cardPrintRequestDemo.framework.entity.role.Role;
import com.noway.cardPrintRequestDemo.framework.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class MyRunner implements CommandLineRunner {


//    @Autowired
//    RoleRepository roleRepository;

    @Override
    public void run(String... args)throws Exception {

//        Role user= new Role();
//        user.setEnabled(Boolean.TRUE);
//        user.setName("user");
//        user.setPublishingDate(LocalDateTime.now());
//        roleRepository.save(user);
//        Role admin= new Role();
//        admin.setEnabled(Boolean.TRUE);
//        admin.setName("admin");
//        admin.setPublishingDate(LocalDateTime.now());
//        roleRepository.save(admin);

    }
}
