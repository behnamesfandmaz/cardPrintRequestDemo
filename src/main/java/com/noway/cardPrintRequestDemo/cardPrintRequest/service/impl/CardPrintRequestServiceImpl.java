package com.noway.cardPrintRequestDemo.cardPrintRequest.service.impl;

import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequest;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequestId;
import com.noway.cardPrintRequestDemo.cardPrintRequest.repository.CardPrintRequestRepository;
import com.noway.cardPrintRequestDemo.cardPrintRequest.service.inter.ICardPrintRequestService;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import com.noway.cardPrintRequestDemo.framework.exception.BusinessException;
import com.noway.cardPrintRequestDemo.framework.repository.user.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;


@Service
public class CardPrintRequestServiceImpl implements ICardPrintRequestService {

    private static final Logger LOG = Logger.getLogger(CardPrintRequestServiceImpl.class.getName());

    @Autowired
    CardPrintRequestRepository cardPrintRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public CardPrintRequestDTO saveCardPrintRequest(CardPrintRequestDTO cardPrintRequestDTO) {
        LOG.info("Call saveCardPrintRequest Service");

        CardPrintRequest cardPrintRequest = new CardPrintRequest().convertDataTransferObjectToEntity(cardPrintRequestDTO);
        cardPrintRequest.setUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        cardPrintRequest.setPersonnelCode("1000" + cardPrintRequest.getUser().getId());
        return cardPrintRequestRepository.save(cardPrintRequest).getDataTransferObject();
    }

    @Override
    public CardPrintRequestWithPagginationResp findByIdBranchCode(String branchCode, Integer pageNo, Integer pageSize) {
        LOG.info("Call findByIdBranchCode Service");

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("issuedDate").descending());
        Page<CardPrintRequest> page = cardPrintRequestRepository.findByIdBranchCodeAndUser(branchCode
                , userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                , pageable);
        if (page.getSize() != 0) {
            CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = new CardPrintRequestWithPagginationResp();
            cardPrintRequestWithPagginationResp.setCardPrintRequestDTOs(page.getContent().stream()
                    .map(CardPrintRequest::getDataTransferObject).collect(Collectors.toList()));
            cardPrintRequestWithPagginationResp.setTotalPages(page.getTotalPages());
            cardPrintRequestWithPagginationResp.setCurrentPage(pageNo);
            cardPrintRequestWithPagginationResp.setTotalItems(page.getTotalElements());
            return cardPrintRequestWithPagginationResp;
        }
        return null;
    }

    @Override
    public CardPrintRequestDTO findByIdAndUser(CardPrintRequestIdDTO cardPrintRequestIdDTO) {
        LOG.info("Call findByIdAndUser Service");

        return cardPrintRequestRepository
                .findByIdAndUser(new CardPrintRequestId().convertDataTransferObjectToEntity(cardPrintRequestIdDTO),
                        userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()))
                .getDataTransferObject();
    }

    @Override
    public CardPrintRequestWithPagginationResp findAllByPaggination(Integer pageNo, Integer pageSize) {
        LOG.info("Call findAllByPaggination Service");

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("issuedDate").descending());
        Page<CardPrintRequest> page = cardPrintRequestRepository.findByUser(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()), pageable);
        if (page.getSize() != 0) {
            CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = new CardPrintRequestWithPagginationResp();
            cardPrintRequestWithPagginationResp.setCardPrintRequestDTOs(page.getContent().stream()
                    .map(CardPrintRequest::getDataTransferObject).collect(Collectors.toList()));
            cardPrintRequestWithPagginationResp.setTotalPages(page.getTotalPages());
            cardPrintRequestWithPagginationResp.setCurrentPage(pageNo);
            cardPrintRequestWithPagginationResp.setTotalItems(page.getTotalElements());
            return cardPrintRequestWithPagginationResp;
        }
        return null;
    }

    @Transactional
    @Override
    public CardPrintRequestDTO updateCardPrintRequestIfNeeded(CardPrintRequestDTO cardPrintRequestDTO, CardPrintRequestIdDTO cardPrintRequestIdDTO) {
        LOG.info("Call updateCardPrintRequestIfNeeded Service");

        CardPrintRequest dbCardPrintRequest = cardPrintRequestRepository
                .findByIdAndUser(new CardPrintRequestId().convertDataTransferObjectToEntity(cardPrintRequestIdDTO),
                        userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        boolean needUpdate = false;

        if (StringUtils.hasLength(cardPrintRequestDTO.getCardPan())) {
            dbCardPrintRequest.setCardPan(cardPrintRequestDTO.getCardPan());
            needUpdate = true;
        }

        if (needUpdate) {
            return cardPrintRequestRepository.save(dbCardPrintRequest).getDataTransferObject();
        }
        return cardPrintRequestDTO;
    }

    @Transactional
    @Override
    public Integer deleteCardPrintRequestById(CardPrintRequestIdDTO cardPrintRequestIdDTO) {
        LOG.info("Call deleteCardPrintRequestById Service");

        return cardPrintRequestRepository.deleteByIdAndUser(
                new CardPrintRequestId().convertDataTransferObjectToEntity(cardPrintRequestIdDTO),
                userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @Transactional
    @Override
    public CardPrintRequestDTO updateCardPrintRequest(CardPrintRequestDTO cardPrintRequestDTO) {
        LOG.info("Call updateCardPrintRequest Service");

        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId(cardPrintRequestDTO.getIpAddress()
                ,cardPrintRequestDTO.getBranchCode());

        CardPrintRequest dbCardPrintRequest = cardPrintRequestRepository
                .findByIdAndUser(cardPrintRequestId,
                        userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

        dbCardPrintRequest.setCardPan(cardPrintRequestDTO.getCardPan());
        return cardPrintRequestRepository.save(dbCardPrintRequest).getDataTransferObject();
    }


}
