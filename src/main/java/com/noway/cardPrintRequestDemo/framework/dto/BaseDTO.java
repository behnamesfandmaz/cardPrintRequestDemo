package com.noway.cardPrintRequestDemo.framework.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private LocalDateTime publishingDate;
	
	private Integer version;
	
	private Boolean enabled;
}
