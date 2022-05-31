package com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardPrintRequestWithPagginationResp {
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
    private Object cardPrintRequestDTOs;
}
