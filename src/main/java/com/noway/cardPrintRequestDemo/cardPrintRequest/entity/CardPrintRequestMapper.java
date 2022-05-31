package com.noway.cardPrintRequestDemo.cardPrintRequest.entity;


import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequest;

public abstract class CardPrintRequestMapper{
	
	public abstract CardPrintRequest convertDataTransferObjectToEntity (CardPrintRequestDTO DTO);
	
	public abstract CardPrintRequestDTO getDataTransferObject();

}
