package com.noway.cardPrintRequestDemo.cardPrintRequest.repository;

import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequest;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequestId;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardPrintRequestRepository extends JpaRepository<CardPrintRequest, CardPrintRequestId> {

    Page<CardPrintRequest> findByIdBranchCodeAndUser(String branchCode, User user, Pageable pageable);

    Page<CardPrintRequest> findByUser(User user, Pageable pageable);

    CardPrintRequest findByIdAndUser(CardPrintRequestId cardPrintRequestId, User user);

    Boolean deleteByIdAndUser(CardPrintRequestId cardPrintRequestId, User user);

}
