package com.noway.cardPrintRequestDemo.framework.log.inter;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface IRequestHeaderLog {
    void printRequestHeaderData(Map<String,String> headers);
}
