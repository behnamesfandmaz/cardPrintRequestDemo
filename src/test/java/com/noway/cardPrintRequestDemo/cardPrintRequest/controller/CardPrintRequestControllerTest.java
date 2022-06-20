package com.noway.cardPrintRequestDemo.cardPrintRequest.controller;

import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.service.inter.ICardPrintRequestService;
import com.noway.cardPrintRequestDemo.framework.filter.JwtFilter;
import com.noway.cardPrintRequestDemo.framework.log.inter.IRequestHeaderLog;
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

import static com.noway.cardPrintRequestDemo.cardPrintRequest.controller.CardPrintRequestRestControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = {CardPrintRequestController.class},excludeAutoConfiguration = SecurityAutoConfiguration.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
class CardPrintRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICardPrintRequestService cardPrintRequestService;

    @MockBean
    private IRequestHeaderLog requestHeaderLog;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private JwtFilter jwtFilter;


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

        mockMvc.perform(post("/saveCardPrintRequest")
                .content(asJsonString(cardPrintRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
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

        mockMvc.perform(get("/deleteCardPrintRequestById")
                .param("ipAddress",cardPrintRequestDTO.getIpAddress())
                .param("branchCode",cardPrintRequestDTO.getBranchCode())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
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

        mockMvc.perform(get("/getAllCardPrintRequest").param("pageNo","1").param("pageSize","5"))
                .andExpect(status().isOk())
                .andExpect(view().name("view-cardPrintRequest"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/view-cardPrintRequest.jsp"))
                .andExpect(model().attribute("cardPrintRequestDTOs", cardPrintRequestWithPagginationResp));
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

        mockMvc.perform(get("/getAllCardPrintRequestWithBranchCode")
                .param("branchCode","777890").param("pageNo","1").param("pageSize","5"))
                .andExpect(status().isOk())
                .andExpect(view().name("view-cardPrintRequest"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/view-cardPrintRequest.jsp"))
                .andExpect(model().attribute("cardPrintRequestDTOs", cardPrintRequestWithPagginationResp));
    }

    @Test
    void updateCardPrintRequest() throws Exception {
        CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
        cardPrintRequestDTO.setCardPan("50222910");
        cardPrintRequestDTO.setIpAddress("10.10.10.10");
        cardPrintRequestDTO.setPersonnelCode("123456");
        cardPrintRequestDTO.setBranchCode("123456");

        CardPrintRequestDTO cardPrintRequestDTOUpdated = new CardPrintRequestDTO();
        cardPrintRequestDTOUpdated.setCardPan("5022291048");
        cardPrintRequestDTOUpdated.setIpAddress("10.10.10.10");
        cardPrintRequestDTOUpdated.setPersonnelCode("123456");
        cardPrintRequestDTOUpdated.setBranchCode("123456");

        when(cardPrintRequestService.saveCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTO);
        when(cardPrintRequestService.updateCardPrintRequest(any(CardPrintRequestDTO.class))).thenReturn(cardPrintRequestDTOUpdated);


        mockMvc.perform(post("/updateCardPrintRequest")
                .content(asJsonString(cardPrintRequestDTOUpdated))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}