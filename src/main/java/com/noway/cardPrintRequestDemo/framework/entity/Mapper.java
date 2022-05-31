package com.noway.cardPrintRequestDemo.framework.entity;


import com.noway.cardPrintRequestDemo.framework.dto.BaseDTO;

public abstract class Mapper <T extends BaseEntity<?, ?>, D extends BaseDTO>{
	
	public abstract T convertDataTransferObjectToEntity (D dto);
	
	public abstract D getDataTransferObject();

}
