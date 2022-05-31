package com.noway.cardPrintRequestDemo.framework.service.impl;


import com.noway.cardPrintRequestDemo.framework.dto.BaseDTO;
import com.noway.cardPrintRequestDemo.framework.entity.BaseEntity;
import com.noway.cardPrintRequestDemo.framework.repository.IGenericRepository;
import com.noway.cardPrintRequestDemo.framework.service.inter.IGenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenericServiceImpl<T extends BaseEntity<T, D>, D extends BaseDTO> implements IGenericService<T, D> {

	@Autowired
	private IGenericRepository<T> iGenericRepository;

	@Override
	@Transactional
	public D save(T entity) {
		entity.setPublishingDate(LocalDateTime.now());
		return iGenericRepository.save(entity).getDataTransferObject();
	}

	@Override
	public List<D> findAll() {
		List<D> listD = new ArrayList<>();
		(iGenericRepository.findAll()).stream().forEach(n -> {
			listD.add(((T) n).getDataTransferObject());
		});
		return listD;
	}

	@Override
	public D findById(Long entityId) {
		Optional<T> optional = iGenericRepository.findById(entityId);
		return optional.isPresent() ? (optional.get()).getDataTransferObject() : null;
	}

	@Override
	@Transactional
	public D update(T entity) {
		return iGenericRepository.save(entity).getDataTransferObject();
	}

	@Override
	@Transactional
	public D updateById(T entity, Long entityId) {
		return iGenericRepository.findById(entityId).isPresent()
				? iGenericRepository.save(entity).getDataTransferObject()
				: null;
	}

	@Override
	@Transactional
	public void delete(T entity) {
		iGenericRepository.delete(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long entityId) {
		iGenericRepository.deleteById(entityId);

	}
}
