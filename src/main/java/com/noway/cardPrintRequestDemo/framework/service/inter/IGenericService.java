package com.noway.cardPrintRequestDemo.framework.service.inter;

import com.noway.cardPrintRequestDemo.framework.dto.BaseDTO;
import com.noway.cardPrintRequestDemo.framework.entity.BaseEntity;

import java.util.List;

public interface IGenericService<T extends BaseEntity<T, D>, D extends BaseDTO> {

	D save(T entity);

	List<D> findAll();

	D findById(Long entityId);

	D update(T entity);

	D updateById(T entity, Long entityId);

	void delete(T entity);

	void deleteById(Long entityId);
}
