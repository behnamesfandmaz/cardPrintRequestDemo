package com.noway.cardPrintRequestDemo.framework.repository.activityLog;

import com.noway.cardPrintRequestDemo.framework.entity.activityLog.ActivityLogElas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ActivityLogElasRepository extends ElasticsearchRepository<ActivityLogElas, String> {

    Page<ActivityLogElas> findByCardNumber(String cardNumber, Pageable pageable);

    List<ActivityLogElas> findByFunctionName(String functionName);

}
