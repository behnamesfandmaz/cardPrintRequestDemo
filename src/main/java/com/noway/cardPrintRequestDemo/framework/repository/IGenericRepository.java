package com.noway.cardPrintRequestDemo.framework.repository;

import com.noway.cardPrintRequestDemo.framework.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IGenericRepository <T extends BaseEntity<?, ?>> extends JpaRepository<T, Long>{
		
}
