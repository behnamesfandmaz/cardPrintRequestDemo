package com.noway.cardPrintRequestDemo.framework.entity;

import com.noway.cardPrintRequestDemo.framework.dto.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity <T extends BaseEntity<?, ?>,D extends BaseDTO> extends Mapper<T,D> implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "c_id")
	private Long id;
	
	@Column(name = "c_PUBLISHING_DATE",updatable = false,nullable = false)
	private LocalDateTime publishingDate;
			
	@Version
	@Column(name = "c_version")
	private Integer version;

	@Column(name = "c_enabled",nullable = false)
	private Boolean enabled;
}
