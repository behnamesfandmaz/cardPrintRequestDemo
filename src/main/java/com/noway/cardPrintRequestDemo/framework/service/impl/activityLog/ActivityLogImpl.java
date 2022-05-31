package com.noway.cardPrintRequestDemo.framework.service.impl.activityLog;

import com.noway.cardPrintRequestDemo.framework.dto.activityLog.ActivityLogDTO;
import com.noway.cardPrintRequestDemo.framework.entity.activityLog.ActivityLog;
import com.noway.cardPrintRequestDemo.framework.repository.loggingAspect.LoggingAspectRepository;
import com.noway.cardPrintRequestDemo.framework.service.impl.GenericServiceImpl;
import com.noway.cardPrintRequestDemo.framework.service.inter.activityLog.IActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityLogImpl extends GenericServiceImpl<ActivityLog, ActivityLogDTO> implements IActivityLog {

    @Autowired
    private LoggingAspectRepository loggingAspectRepository;

    @Override
    public ActivityLogDTO save(ActivityLogDTO activityLogDTO) {
        return super.save(new ActivityLog().convertDataTransferObjectToEntity(activityLogDTO));
    }
}
