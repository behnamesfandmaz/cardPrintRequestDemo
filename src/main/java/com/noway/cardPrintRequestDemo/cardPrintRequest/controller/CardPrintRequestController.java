package com.noway.cardPrintRequestDemo.cardPrintRequest.controller;


import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.service.impl.CardPrintRequestServiceImpl;
import com.noway.cardPrintRequestDemo.framework.exception.JspBusinessException;
import com.noway.cardPrintRequestDemo.framework.log.ExcutionTime;
import com.noway.cardPrintRequestDemo.framework.log.impl.RequestHeaderLogImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes({"cardPrintRequestDTO"})
public class CardPrintRequestController {
    private static final Logger LOG = Logger.getLogger(CardPrintRequestController.class.getName());
    protected static final Integer DEFAULT_PAGE_SIZE = 5;
    protected static final Integer DEFAULT_PAGE_NUM = 1;

    @Autowired
    CardPrintRequestServiceImpl cardPrintRequestService;

    @Autowired
    RequestHeaderLogImpl requestHeaderLog;

    @RequestMapping(value = "/saveCardPrintRequest",
            method = RequestMethod.POST)
    @ExcutionTime
    @ApiOperation(value = "insert new cardPrintRequest", notes = "validation and insert new cardPrintRequest")
    public String saveCardPrintRequest(
            @Valid CardPrintRequestDTO cardPrintRequestDTO,
            BindingResult bindingResult,
            @RequestHeader Map<String, String> headers) {
        try {

            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Save CardPrintRequest");
            if (bindingResult.hasErrors()) {
                return "save-cardPrintRequest";
            }
            cardPrintRequestService.saveCardPrintRequest(cardPrintRequestDTO);
            return "redirect:/getAllCardPrintRequest?pageNo=1&pageSize=5";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/saveCardPrintRequest",
            method = RequestMethod.GET)
    @ExcutionTime
    @ApiOperation(value = "send user to add cardPrintRequest Form with new Object", notes = "show add form.")
    public String showSaveCardPrintRequest(
            ModelMap model,
            @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Show Save CardPrintRequest");

            CardPrintRequestDTO cardPrintRequestDTO = new CardPrintRequestDTO();
            cardPrintRequestDTO.setPersonnelCode("10000");

            model.put("cardPrintRequestDTO",
                    cardPrintRequestDTO);
            return "save-cardPrintRequest";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }


    @RequestMapping(value = "/deleteCardPrintRequestById",
            method = RequestMethod.GET)
    @ExcutionTime
    @ApiOperation(value = "delete cardPrintRequest with custom Id", notes = "delete cardPrintRequest and send user to cardPrintRequests page.")
    public String deleteCardPrintRequestById(@ApiParam(value = "ipAddress of cardPrintRequest", required = true) @RequestParam("ipAddress") String ipAddress,
                                             @ApiParam(value = "branchCode of cardPrintRequest", required = true) @RequestParam("branchCode") String branchCode,
                                             @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "delete CardPrintRequest By Id");
            cardPrintRequestService.deleteCardPrintRequestById(new CardPrintRequestIdDTO(ipAddress, branchCode));
            return "redirect:/getAllCardPrintRequest?pageNo=1&pageSize=5";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }


    @RequestMapping(value = "/getAllCardPrintRequest", method = RequestMethod.GET)
    @ExcutionTime
    @ApiOperation(value = "show all cardPrintRequest of User", notes = "fetch cardPrintRequest of User.")
    public String getAllCardPrintRequest(
            @ApiParam(value = "page number of table", required = true) @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(value = "page size of table", required = true) @RequestParam(value = "pageSize") Integer pageSize,
            @RequestHeader Map<String, String> headers,
            ModelMap model) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Get All CardPrintRequest");
            CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = cardPrintRequestService.
                    findAllByPaggination(pageNo != null && !pageNo.equals(0) ? pageNo : DEFAULT_PAGE_NUM,
                            pageSize != null && !pageSize.equals(0) ? pageSize : DEFAULT_PAGE_SIZE);


            model.put("cardPrintRequestDTOs", cardPrintRequestWithPagginationResp);
            return "view-cardPrintRequest";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/getAllCardPrintRequestWithBranchCode", method = RequestMethod.GET)
    @ExcutionTime
    @ApiOperation(value = "show all cardPrintRequest of User", notes = "fetch cardPrintRequest of User.")
    public String getAllCardPrintRequestWithBrachCode(
            @ApiParam(value = "branch Code", required = true) @RequestParam(value = "branchCode") String branchCode,
            @ApiParam(value = "page number of table", required = true) @RequestParam(value = "pageNo") Integer pageNo,
            @ApiParam(value = "page size of table", required = true) @RequestParam(value = "pageSize") Integer pageSize,
            @RequestHeader Map<String, String> headers,
            ModelMap model) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Get All CardPrintRequest With BranchCode");
            CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = cardPrintRequestService.
                    findByIdBranchCode(branchCode
                            , pageNo != null && !pageNo.equals(0) ? pageNo : DEFAULT_PAGE_NUM
                            , pageSize != null && !pageSize.equals(0) ? pageSize : DEFAULT_PAGE_SIZE);


            model.put("cardPrintRequestDTOs", cardPrintRequestWithPagginationResp);
            return "view-cardPrintRequest";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/updateCardPrintRequest", method = RequestMethod.GET)
    @ExcutionTime
    @ApiOperation(value = "send user to update CardPrintRequest page with selected CardPrintRequest", notes = "update cardPrintRequest page")
    public String showUpdateCardPrintRequest(@ApiParam(value = "ipAddress Of selected Data", required = true) @RequestParam(value = "ipAddress") String ipAddress,
                                             @ApiParam(value = "branchCode of selected Data", required = true) @RequestParam(value = "branchCode") String branchCode,
                                             ModelMap model,
                                             @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "showUpdate CardPrintRequest");
            model.put("cardPrintRequestDTO",
                    cardPrintRequestService.findByIdAndUser(new CardPrintRequestIdDTO(ipAddress, branchCode)));
            return "update-cardPrintRequest";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }


    @RequestMapping(value = "/updateCardPrintRequest",
            method = RequestMethod.POST)
    @ExcutionTime
    @ApiOperation(value = "update cardPrintRequest", notes = "update cardPrintRequest")
    public String updateCardPrintRequest(@Valid CardPrintRequestDTO cardPrintRequestDTO,
                                         BindingResult bindingResult,
                                         @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Update CardPrintRequest");
            if (bindingResult.hasErrors()) {
                return "update-cardPrintRequest";
            }

            cardPrintRequestService.updateCardPrintRequest(cardPrintRequestDTO);


            return "redirect:/getAllCardPrintRequest?pageNo=1&pageSize=5";
        } catch (Exception e) {
            throw new JspBusinessException(e.getMessage(), e);
        }
    }


}
