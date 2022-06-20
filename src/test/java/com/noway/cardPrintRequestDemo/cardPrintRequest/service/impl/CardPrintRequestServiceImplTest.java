package com.noway.cardPrintRequestDemo.cardPrintRequest.service.impl;

import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequest;
import com.noway.cardPrintRequestDemo.cardPrintRequest.entity.cardPrintRequestEntity.CardPrintRequestId;
import com.noway.cardPrintRequestDemo.cardPrintRequest.repository.CardPrintRequestRepository;
import com.noway.cardPrintRequestDemo.framework.entity.user.User;
import com.noway.cardPrintRequestDemo.framework.filter.JwtFilter;
import com.noway.cardPrintRequestDemo.framework.repository.activityLog.ActivityLogElasRepository;
import com.noway.cardPrintRequestDemo.framework.repository.user.UserRepository;
import com.noway.cardPrintRequestDemo.framework.service.inter.activityLog.IActivityLogElas;
import com.noway.cardPrintRequestDemo.framework.utility.JWTUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CardPrintRequestServiceImplTest {


    @Mock
    private CardPrintRequestRepository cardPrintRequestRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CardPrintRequestServiceImpl cardPrintRequestService;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private IActivityLogElas activityLogElas;

    @MockBean
    private ActivityLogElasRepository activityLogElasRepository;



    @BeforeEach
    void setUp() {
    }

    @Test
    @WithUserDetails("test")
    void saveCardPrintRequest() {
        CardPrintRequest cardPrintRequest=new CardPrintRequest();
        cardPrintRequest.setCardPan("50222910");
        cardPrintRequest.setPersonnelCode("10001");
        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId();
        cardPrintRequestId.setIpAddress("10.30.20.30");
        cardPrintRequestId.setBranchCode("777890");
        cardPrintRequest.setId(cardPrintRequestId);

        User user=new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles(new HashSet<>());
        cardPrintRequest.setUser(user);

        when(userRepository.findByUsername(any())).thenReturn(user);
        when(cardPrintRequestRepository.save(any())).thenReturn(cardPrintRequest);


        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.30.20.30");
        cardPrintRequestDTO.setPersonnelCode("10001");
        cardPrintRequestDTO.setBranchCode("777890");
        cardPrintRequestDTO.setUsername("test");

        CardPrintRequestDTO serviceData = cardPrintRequestService.saveCardPrintRequest(cardPrintRequestDTO);
        assertEquals("777890", serviceData.getBranchCode());

    }

    @Test
    @WithUserDetails("test")
    void findByIdBranchCode() {

        CardPrintRequest cardPrintRequest=new CardPrintRequest();
        cardPrintRequest.setCardPan("50222910");
        cardPrintRequest.setPersonnelCode("10001");
        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId();
        cardPrintRequestId.setIpAddress("10.30.20.30");
        cardPrintRequestId.setBranchCode("777890");
        cardPrintRequest.setId(cardPrintRequestId);

        User user=new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles(new HashSet<>());

        cardPrintRequest.setUser(user);

        List<CardPrintRequest> cardPrintRequests1= Collections.singletonList(cardPrintRequest);

        Page<CardPrintRequest> cardPrintRequests=new PageImpl<>(cardPrintRequests1);

        when(cardPrintRequestRepository.findByIdBranchCodeAndUser(any(),any(),any())).thenReturn(cardPrintRequests);
        CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = cardPrintRequestService
                .findByIdBranchCode(cardPrintRequest.getId().getBranchCode(),
                1,
                5);
        assertEquals(1L, cardPrintRequestWithPagginationResp.getTotalItems());
    }

    @Test
    @WithUserDetails("test")
    void findByIdAndUser() {

        CardPrintRequest cardPrintRequest=new CardPrintRequest();
        cardPrintRequest.setCardPan("50222910");
        cardPrintRequest.setPersonnelCode("10001");
        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId();
        cardPrintRequestId.setIpAddress("10.30.20.30");
        cardPrintRequestId.setBranchCode("777890");
        cardPrintRequest.setId(cardPrintRequestId);

        User user=new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles(new HashSet<>());

        cardPrintRequest.setUser(user);



        CardPrintRequestIdDTO cardPrintRequestIdDTO=new CardPrintRequestIdDTO();
        cardPrintRequestIdDTO.setBranchCode(cardPrintRequestId.getBranchCode());
        cardPrintRequestIdDTO.setIpAddress(cardPrintRequestId.getIpAddress());

        when(cardPrintRequestRepository.findByIdAndUser(any(),any())).thenReturn(cardPrintRequest);
        CardPrintRequestDTO cardPrintRequestDTO = cardPrintRequestService
                .findByIdAndUser(cardPrintRequestIdDTO);
        assertEquals(cardPrintRequest.getId().getBranchCode(), cardPrintRequestDTO.getBranchCode());

    }

    @Test
    @WithUserDetails("test")
    void findAllByPaggination() {

        CardPrintRequest cardPrintRequest=new CardPrintRequest();
        cardPrintRequest.setCardPan("50222910");
        cardPrintRequest.setPersonnelCode("10001");
        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId();
        cardPrintRequestId.setIpAddress("10.30.20.30");
        cardPrintRequestId.setBranchCode("777890");
        cardPrintRequest.setId(cardPrintRequestId);

        User user=new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles(new HashSet<>());

        cardPrintRequest.setUser(user);

        List<CardPrintRequest> cardPrintRequests1= Collections.singletonList(cardPrintRequest);

        Page<CardPrintRequest> cardPrintRequests=new PageImpl<>(cardPrintRequests1);

        when(cardPrintRequestRepository.findByUser(any(),any())).thenReturn(cardPrintRequests);
        CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = cardPrintRequestService.findAllByPaggination(1,5);
        assertEquals(1L, cardPrintRequestWithPagginationResp.getTotalItems());
    }

    @Test
    @WithUserDetails("test")
    void updateCardPrintRequestIfNeeded() {
        CardPrintRequest cardPrintRequest=new CardPrintRequest();
        cardPrintRequest.setCardPan("50222910");
        cardPrintRequest.setPersonnelCode("10001");
        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId();
        cardPrintRequestId.setIpAddress("10.30.20.30");
        cardPrintRequestId.setBranchCode("777890");
        cardPrintRequest.setId(cardPrintRequestId);

        User user=new User();
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles(new HashSet<>());
        cardPrintRequest.setUser(user);

        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.30.20.30");
        cardPrintRequestDTO.setPersonnelCode("10001");
        cardPrintRequestDTO.setBranchCode("777890");
        cardPrintRequestDTO.setUsername("test");

        when(userRepository.findByUsername(any())).thenReturn(user);
        when(cardPrintRequestRepository.save(any())).thenReturn(cardPrintRequest);

        CardPrintRequestDTO saveServiceData = cardPrintRequestService.saveCardPrintRequest(cardPrintRequestDTO);


        CardPrintRequest updatedCardPrintRequest=new CardPrintRequest();
        updatedCardPrintRequest.setCardPan("502229101234");
        updatedCardPrintRequest.setPersonnelCode("10001");
        updatedCardPrintRequest.setId(cardPrintRequestId);
        updatedCardPrintRequest.setUser(user);

        when(userRepository.findByUsername(any())).thenReturn(user);
        when(cardPrintRequestRepository.findByIdAndUser(any(),any())).thenReturn(cardPrintRequest);
        when(cardPrintRequestRepository.save(any())).thenReturn(updatedCardPrintRequest);


        CardPrintRequestIdDTO cardPrintRequestIdDTO=new CardPrintRequestIdDTO();
        cardPrintRequestIdDTO.setBranchCode(cardPrintRequestId.getBranchCode());
        cardPrintRequestIdDTO.setIpAddress(cardPrintRequestId.getIpAddress());

        CardPrintRequestDTO updateServiceData=cardPrintRequestService.
                updateCardPrintRequestIfNeeded(cardPrintRequestDTO,cardPrintRequestIdDTO);

        assertEquals(saveServiceData.getBranchCode(), updateServiceData.getBranchCode());

    }

    @Test
    @WithUserDetails("test")
    void deleteCardPrintRequestById() {
        CardPrintRequestIdDTO cardPrintRequestIdDTO=new CardPrintRequestIdDTO();
        cardPrintRequestIdDTO.setBranchCode("777890");
        cardPrintRequestIdDTO.setIpAddress("10.30.20.30");

        when(cardPrintRequestRepository.deleteByIdAndUser(any(),any())).thenReturn(1);

        Integer serviceData=cardPrintRequestService.deleteCardPrintRequestById(cardPrintRequestIdDTO);


        assertEquals(1,serviceData);

    }

    @Test
    @WithUserDetails("test")
    void updateCardPrintRequest() {
        CardPrintRequest cardPrintRequest=new CardPrintRequest();
        cardPrintRequest.setCardPan("50222910");
        cardPrintRequest.setPersonnelCode("10001");
        CardPrintRequestId cardPrintRequestId=new CardPrintRequestId();
        cardPrintRequestId.setIpAddress("10.30.20.30");
        cardPrintRequestId.setBranchCode("777890");
        cardPrintRequest.setId(cardPrintRequestId);

        User user=new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("password");
        user.setRoles(new HashSet<>());
        cardPrintRequest.setUser(user);

        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.30.20.30");
        cardPrintRequestDTO.setPersonnelCode("10001");
        cardPrintRequestDTO.setBranchCode("777890");
        cardPrintRequestDTO.setUsername("test");

        when(userRepository.findByUsername(any())).thenReturn(user);
        when(cardPrintRequestRepository.save(any())).thenReturn(cardPrintRequest);

        CardPrintRequestDTO saveServiceData = cardPrintRequestService.saveCardPrintRequest(cardPrintRequestDTO);


        CardPrintRequest cardPrintRequestUpdate=new CardPrintRequest();
        cardPrintRequestUpdate.setCardPan("50222910");
        cardPrintRequestUpdate.setPersonnelCode("10001");
        cardPrintRequestUpdate.setId(cardPrintRequestId);
        cardPrintRequestUpdate.setUser(user);


        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(cardPrintRequestRepository.findByIdAndUser(any(),any())).thenReturn(cardPrintRequest);
        when(cardPrintRequestRepository.save(any())).thenReturn(cardPrintRequestUpdate);

        CardPrintRequestDTO updateData = new CardPrintRequestDTO();
        updateData.setCardPan("502229101234");
        updateData.setIpAddress("10.30.20.30");
        updateData.setPersonnelCode("10001");
        updateData.setBranchCode("777890");
        updateData.setUsername("test");

        CardPrintRequestDTO updatedCardPrintRequest=cardPrintRequestService.updateCardPrintRequest(updateData);

        assertEquals(updatedCardPrintRequest.getCardPan(),saveServiceData.getCardPan());

    }
}