package com.noway.cardPrintRequestDemo.framework.log.impl;

import com.noway.cardPrintRequestDemo.framework.log.inter.IRequestHeaderLog;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RequestHeaderLogImpl implements IRequestHeaderLog {
    private static final Logger LOG = Logger.getLogger(RequestHeaderLogImpl.class.getName());

    @Override
    public void printRequestHeaderData(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOG.info(String.format("Header '%s' = %s", key, value));
        });
    }
}
