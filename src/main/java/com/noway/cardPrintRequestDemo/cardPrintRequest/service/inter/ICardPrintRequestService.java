package com.noway.cardPrintRequestDemo.cardPrintRequest.service.inter;

import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;


import java.util.Map;

public interface ICardPrintRequestService {

    CardPrintRequestDTO saveCardPrintRequest(CardPrintRequestDTO cardPrintRequestDTO);

    CardPrintRequestWithPagginationResp findByIdBranchCode(String branchCode,Integer pageNo,Integer pageSize);

    CardPrintRequestDTO findByIdAndUser(CardPrintRequestIdDTO cardPrintRequestIdDTO);

    Boolean deleteCardPrintRequestById(CardPrintRequestIdDTO cardPrintRequestIdDTO);

    CardPrintRequestDTO updateCardPrintRequest(CardPrintRequestDTO cardPrintRequestDTO);

    CardPrintRequestWithPagginationResp findAllByPaggination(Integer pageNo, Integer pageSize);

    CardPrintRequestDTO  updateCardPrintRequestIfNeeded(CardPrintRequestDTO cardPrintRequestDTO,CardPrintRequestIdDTO cardPrintRequestIdDTO);


}
