package com.noway.cardPrintRequestDemo.cardPrintRequest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.service.impl.CardPrintRequestServiceImpl;
import com.noway.cardPrintRequestDemo.framework.filter.JwtFilter;
import com.noway.cardPrintRequestDemo.framework.log.impl.RequestHeaderLogImpl;
import com.noway.cardPrintRequestDemo.framework.utility.JWTUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = {CardPrintRequestRestController.class},excludeAutoConfiguration = SecurityAutoConfiguration.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
class CardPrintRequestRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardPrintRequestServiceImpl cardPrintRequestService;

    @MockBean
    private RequestHeaderLogImpl requestHeaderLog;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private JwtFilter jwtFilter;

    @Autowired
    private WebApplicationContext webApplicationContext;




    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveCardPrintRequest() throws Exception {
        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("123456");

        when(cardPrintRequestService.saveCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTO);

        mockMvc.perform(post("/api/v1/saveCardPrintRequest")
                .content(asJsonString(cardPrintRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ipAddress").value("10.10.10.10"))
                .andExpect(jsonPath("$.branchCode").value("123456"))
                .andExpect(jsonPath("$.personnelCode").value("123456"))
                .andExpect(jsonPath("$.cardPan").value("50222910"))
                .andReturn();

    }

    @Test
    void deleteCardPrintRequestById() throws Exception {
        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("123456");

        when(cardPrintRequestService.saveCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTO);

        CardPrintRequestIdDTO cardPrintRequestIdDTO=new CardPrintRequestIdDTO();
        cardPrintRequestIdDTO.setIpAddress(cardPrintRequestDTO.getIpAddress());
        cardPrintRequestDTO.setBranchCode(cardPrintRequestDTO.getBranchCode());

        mockMvc.perform(delete("/api/v1/deleteCardPrintRequestById")
                .content(asJsonString(cardPrintRequestIdDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllCardPrintRequest() throws Exception {

        CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp=new CardPrintRequestWithPagginationResp();
        cardPrintRequestWithPagginationResp.setCurrentPage(1);
        cardPrintRequestWithPagginationResp.setTotalPages(1);
        cardPrintRequestWithPagginationResp.setTotalItems(1L);
        List<CardPrintRequestDTO> cardPrintRequestDTOS=new ArrayList<>();
        CardPrintRequestDTO cardPrintRequestDTO=new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("123456");
        cardPrintRequestDTOS.add(cardPrintRequestDTO);

        String listOfCardPrintRequest=asJsonString(cardPrintRequestDTOS);

        cardPrintRequestWithPagginationResp.setCardPrintRequestDTOs(listOfCardPrintRequest);

        when(cardPrintRequestService.findAllByPaggination(1,5)).thenReturn(cardPrintRequestWithPagginationResp);



        mockMvc.perform(get("/api/v1/getAllCardPrintRequest")
                .param("pageNo", "1")
                .param("pageSize","5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalItems").value(1))
                .andExpect(jsonPath("$.cardPrintRequestDTOs").value(listOfCardPrintRequest))
                .andReturn();

    }


    @Test
    void getAllCardPrintRequestWithBrachCode() throws Exception {
        CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp=new CardPrintRequestWithPagginationResp();
        cardPrintRequestWithPagginationResp.setCurrentPage(1);
        cardPrintRequestWithPagginationResp.setTotalPages(1);
        cardPrintRequestWithPagginationResp.setTotalItems(1L);
        List<CardPrintRequestDTO> cardPrintRequestDTOS=new ArrayList<>();
        CardPrintRequestDTO cardPrintRequestDTO=new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("777890");
        cardPrintRequestDTOS.add(cardPrintRequestDTO);

        String listOfCardPrintRequest=asJsonString(cardPrintRequestDTOS);

        cardPrintRequestWithPagginationResp.setCardPrintRequestDTOs(listOfCardPrintRequest);


        when(cardPrintRequestService.findByIdBranchCode(cardPrintRequestDTO.getBranchCode(),1,5)).thenReturn(cardPrintRequestWithPagginationResp);

        mockMvc.perform(get("/api/v1/getAllCardPrintRequestWithBranchCode")
                .param("branchCode","777890").param("pageNo","1")
                .param("pageSize","5")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPage").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalItems").value(1))
                .andExpect(jsonPath("$.cardPrintRequestDTOs").value(listOfCardPrintRequest))
                .andReturn();
    }

    @Test
    void updateCardPrintRequestPut() throws Exception {
        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("123456");

        CardPrintRequestDTO cardPrintRequestDTOUpdated = new CardPrintRequestDTO();
        cardPrintRequestDTOUpdated.setCardPan("50222910Updated");
        cardPrintRequestDTOUpdated.setIpAddress("10.10.10.10");
        cardPrintRequestDTOUpdated.setPersonnelCode("123456");
        cardPrintRequestDTOUpdated.setBranchCode("123456");

        when(cardPrintRequestService.saveCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTO);
        when(cardPrintRequestService.updateCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTOUpdated);


        mockMvc.perform(put("/api/v1/updateCardPrintRequestPut")
                .content(asJsonString(cardPrintRequestDTOUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ipAddress").value("10.10.10.10"))
                .andExpect(jsonPath("$.branchCode").value("123456"))
                .andExpect(jsonPath("$.personnelCode").value("123456"))
                .andExpect(jsonPath("$.cardPan").value("50222910Updated"))
                .andReturn();
    }

    @Test
    void updateCardPrintRequestPatch() throws Exception {
        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("123456");

        CardPrintRequestDTO cardPrintRequestDTOUpdated = new CardPrintRequestDTO();
        cardPrintRequestDTOUpdated.setCardPan("502229101234");
        cardPrintRequestDTOUpdated.setIpAddress("10.10.10.10");
        cardPrintRequestDTOUpdated.setPersonnelCode("123456");
        cardPrintRequestDTOUpdated.setBranchCode("123456");

        when(cardPrintRequestService.saveCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTO);
        when(cardPrintRequestService.updateCardPrintRequestIfNeeded(any(CardPrintRequestDTO.class),any(CardPrintRequestIdDTO.class))).thenReturn(cardPrintRequestDTOUpdated);


        mockMvc.perform(patch("/api/v1/updateCardPrintRequestPatch?ipAddress="+cardPrintRequestDTO.getIpAddress()+"&branchCode="+cardPrintRequestDTO.getBranchCode())
                .content(asJsonString(cardPrintRequestDTOUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ipAddress").value("10.10.10.10"))
                .andExpect(jsonPath("$.branchCode").value("123456"))
                .andExpect(jsonPath("$.personnelCode").value("123456"))
                .andExpect(jsonPath("$.cardPan").value("502229101234"))
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}