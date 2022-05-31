package com.noway.cardPrintRequestDemo.cardPrintRequest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPrintRequestIdDTO {
    private String ipAddress;
    private String branchCode;
}
