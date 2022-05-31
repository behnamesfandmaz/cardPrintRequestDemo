package com.noway.cardPrintRequestDemo.framework.repository.loggingAspect;

import com.noway.cardPrintRequestDemo.framework.entity.activityLog.ActivityLog;
import com.noway.cardPrintRequestDemo.framework.repository.IGenericRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@Primary
public interface LoggingAspectRepository extends IGenericRepository<ActivityLog> {
}
