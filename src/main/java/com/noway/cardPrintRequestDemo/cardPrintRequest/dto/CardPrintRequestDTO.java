package com.noway.cardPrintRequestDemo.cardPrintRequest.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPrintRequestDTO implements Serializable {


    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$",
            message = "Wrong IpAddress Pattern.")
    private String ipAddress;

    @Size(min = 3, message= "branchCode must be at least 3 characters.")
    @NotEmpty(message = "branchCode should not be empty")
    private String branchCode;

    private LocalDateTime publishingDate;

    private Boolean enabled;

    @Size(min = 5, max = 10, message
            = "personnelCode must be between 5 and 10 characters")
    @NotEmpty(message = "personnelCode should not be empty")
    private String personnelCode;

    private String cardPan;

    private Date issuedDate;

    private String username;

    private Long userid;

    private Boolean userEnabled;

    private LocalDateTime issDate;

    private Integer version;

}
