package com.noway.cardPrintRequestDemo.framework.entity.activityLog;


import com.noway.cardPrintRequestDemo.framework.dto.activityLog.ActivityLogDTO;
import com.noway.cardPrintRequestDemo.framework.entity.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "t_activityLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityLog extends BaseEntity<ActivityLog, ActivityLogDTO> {


    @DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    @Column(name = "c_issuedDateAndTime",nullable = false)
    private Date issuedDateAndTime;

    @Column(name = "c_functionname")
    private String functionName;

    @Column(name = "c_personnelCode")
    private String personnelCode;

    @Column(name = "c_cardNumber")
    private String cardNumber;

    @Column(name = "c_applicationType")
    private String applicationType;


    @Override
    public ActivityLog convertDataTransferObjectToEntity(ActivityLogDTO dto) {
        this.setId(dto.getId());
        this.setApplicationType(dto.getApplicationType());
        this.setCardNumber(dto.getCardNumber());
        this.setIssuedDateAndTime(dto.getIssuedDateAndTime());
        this.setFunctionName(dto.getFunctionName());
        this.setPublishingDate(dto.getPublishingDate());
        this.setEnabled(dto.getEnabled());
        this.setPersonnelCode(dto.getPersonnelCode());
        return this;
    }

    @Override
    public ActivityLogDTO getDataTransferObject() {
        ActivityLogDTO activityLogDTO=new ActivityLogDTO();
        activityLogDTO.setId(this.getId());
        activityLogDTO.setApplicationType(this.getApplicationType());
        activityLogDTO.setCardNumber(this.getCardNumber());
        activityLogDTO.setPublishingDate(this.getPublishingDate());
        activityLogDTO.setIssuedDateAndTime(this.getIssuedDateAndTime());
        activityLogDTO.setEnabled(this.getEnabled());
        activityLogDTO.setPersonnelCode(this.getPersonnelCode());
        activityLogDTO.setFunctionName(this.getFunctionName());
        return null;
    }
}
