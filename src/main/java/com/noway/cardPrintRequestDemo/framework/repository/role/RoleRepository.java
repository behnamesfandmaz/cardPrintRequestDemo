package com.noway.cardPrintRequestDemo.framework.repository.role;

import com.noway.cardPrintRequestDemo.framework.entity.role.Role;
import com.noway.cardPrintRequestDemo.framework.repository.IGenericRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends IGenericRepository<Role> {
    Role findByName(String name);
}
