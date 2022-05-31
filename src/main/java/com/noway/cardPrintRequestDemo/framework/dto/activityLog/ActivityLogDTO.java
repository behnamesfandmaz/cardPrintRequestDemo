package com.noway.cardPrintRequestDemo.framework.dto.activityLog;

import com.noway.cardPrintRequestDemo.framework.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLogDTO extends BaseDTO {

    private Date issuedDateAndTime;
    private String functionName;
    private String personnelCode;
    private String cardNumber;
    private String applicationType;

}
