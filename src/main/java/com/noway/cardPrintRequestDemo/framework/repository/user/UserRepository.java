package com.noway.cardPrintRequestDemo.framework.repository.user;

import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import com.noway.cardPrintRequestDemo.framework.repository.IGenericRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends IGenericRepository<User> {

    User findByUsername(String username);
}
