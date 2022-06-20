package com.noway.cardPrintRequestDemo.cardPrintRequest.controller;

import com.noway.cardPrintRequestDemo.cardPrintRequest.customResponse.CardPrintRequestWithPagginationResp;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.dto.CardPrintRequestIdDTO;
import com.noway.cardPrintRequestDemo.cardPrintRequest.service.inter.ICardPrintRequestService;
import com.noway.cardPrintRequestDemo.framework.exception.BusinessException;
import com.noway.cardPrintRequestDemo.framework.log.ExcutionTime;
import com.noway.cardPrintRequestDemo.framework.log.inter.IRequestHeaderLog;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CardPrintRequestRestController {

    private static final Logger LOG = Logger.getLogger(CardPrintRequestRestController.class.getName());
    protected static final Integer DEFAULT_PAGE_SIZE = 5;
    protected static final Integer DEFAULT_PAGE_NUM = 1;

    private final ICardPrintRequestService cardPrintRequestService;
    private final IRequestHeaderLog requestHeaderLog;


    @Autowired
    public CardPrintRequestRestController(ICardPrintRequestService cardPrintRequestService, IRequestHeaderLog requestHeaderLog) {
        this.cardPrintRequestService = cardPrintRequestService;
        this.requestHeaderLog = requestHeaderLog;
    }

    @RequestMapping(value = "/saveCardPrintRequest", method = RequestMethod.POST,
            produces = {"application/json"},
            consumes = {"application/json"})
    @ExcutionTime
    @ApiOperation(value = "insert new CardPrintRequest", notes = "insert new CardPrintRequest")
    public ResponseEntity<CardPrintRequestDTO> saveCardPrintRequest(
            @Valid @RequestBody CardPrintRequestDTO cardPrintRequestDTO,
            BindingResult bindingResult,
            @RequestHeader Map<String, String> headers) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> messages = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                throw new BusinessException(messages.toString());
            }
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Save CardPrintRequest RestAPI With Data :" + cardPrintRequestDTO.toString());
            return new ResponseEntity<>(cardPrintRequestService.saveCardPrintRequest(cardPrintRequestDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/deleteCardPrintRequestById", method = RequestMethod.DELETE,
            produces = {"application/json"},
            consumes = {"application/json"})
    @ExcutionTime
    @ApiOperation(value = "delete cardPrintRequest", notes = "delete cardPrintRequest by ID")
    public ResponseEntity<String> deleteCardPrintRequestById(
            @RequestBody CardPrintRequestIdDTO cardPrintRequestIdDTO,
            @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "delete CardPrintRequest By Id RestAPI");
            cardPrintRequestService.deleteCardPrintRequestById(cardPrintRequestIdDTO);
            return new ResponseEntity<>("Object Deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/getAllCardPrintRequest", method = RequestMethod.GET,
            produces = {"application/json"})
    @ExcutionTime
    @ApiOperation(value = "get all CardPrintRequest of user", notes = "get all cardPrintRequest of User With Paggination")
    public ResponseEntity<CardPrintRequestWithPagginationResp> getAllCardPrintRequest(
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("pageSize") Integer pageSize,
            @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Get All CardPrintRequest RestAPI");
            CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = cardPrintRequestService.
                    findAllByPaggination(
                            pageNo != null && !pageNo.equals(0) ? pageNo : DEFAULT_PAGE_NUM,
                            pageSize != null && !pageSize.equals(0) ? pageSize : DEFAULT_PAGE_SIZE);
            return new ResponseEntity<>(cardPrintRequestWithPagginationResp, HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/getAllCardPrintRequestWithBranchCode", method = RequestMethod.GET,
            produces = {"application/json"})
    @ExcutionTime
    @ApiOperation(value = "get All cardPrintRequest of user", notes = "get All cardPrintRequest with branchCode with paggination")
    public ResponseEntity<CardPrintRequestWithPagginationResp> getAllCardPrintRequestWithBrachCode(
            @RequestParam(value = "branchCode") String branchCode,
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("pageSize") Integer pageSize,
            @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Get All CardPrintRequest With branch Code RestAPI");
            CardPrintRequestWithPagginationResp cardPrintRequestWithPagginationResp = cardPrintRequestService.
                    findByIdBranchCode(
                            branchCode,
                            pageNo != null && !pageNo.equals(0) ? pageNo : DEFAULT_PAGE_NUM,
                            pageSize != null && !pageSize.equals(0) ? pageSize : DEFAULT_PAGE_SIZE);
            return new ResponseEntity<>(cardPrintRequestWithPagginationResp, HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/updateCardPrintRequestPut", method = RequestMethod.PUT,
            produces = {"application/json"},
            consumes = {"application/json"})
    @ExcutionTime
    @ApiOperation(value = "update cardPrintRequest", notes = "update cardPrintRequest method PUT")
    public ResponseEntity<CardPrintRequestDTO> updateCardPrintRequestPut(
            @Valid @RequestBody CardPrintRequestDTO cardPrintRequestDTO,
            BindingResult bindingResult,
            @RequestHeader Map<String, String> headers) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> messages = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                throw new BusinessException(messages.toString());
            }
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Update CardPrintRequest RestAPI (PUT)");
            return new ResponseEntity<>(cardPrintRequestService.updateCardPrintRequest(cardPrintRequestDTO), HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }


    @RequestMapping(value = "/updateCardPrintRequestPatch", method = RequestMethod.PATCH,
            produces = {"application/json"},
            consumes = {"application/json"})
    @ExcutionTime
    @ApiOperation(value = "update cardPrintRequest", notes = "update cardPrintRequest method PATCH")
    public ResponseEntity<CardPrintRequestDTO> updateCardPrintRequestPatch(
            @RequestBody CardPrintRequestDTO cardPrintRequestDTO,
            @RequestParam("ipAddress") String ipAddress,
            @RequestParam("branchCode") String branchCode,
            @RequestHeader Map<String, String> headers) {
        try {
            requestHeaderLog.printRequestHeaderData(headers);
            LOG.log(Level.INFO, "Update CardPrintRequest RestAPI (PATCH)");
            return new ResponseEntity<>(cardPrintRequestService.updateCardPrintRequestIfNeeded(cardPrintRequestDTO,
                    new CardPrintRequestIdDTO(ipAddress, branchCode))
                    , HttpStatus.OK);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

}
