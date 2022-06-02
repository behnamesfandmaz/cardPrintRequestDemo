package com.noway.cardPrintRequestDemo.framework.service.inter.activityLog;

import com.noway.cardPrintRequestDemo.framework.entity.activityLog.ActivityLogElas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IActivityLogElas {

    ActivityLogElas save(ActivityLogElas activityLogElas);

    void deleteActivityLogElas(ActivityLogElas activityLogElas);

    ActivityLogElas findOne(String id);

    Iterable<ActivityLogElas> findAll();

    Page<ActivityLogElas> findByCardNumber(String cardNumber, PageRequest pageRequest);

    List<ActivityLogElas> findByFunctionName(String functionName);
}
