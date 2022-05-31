package com.noway.cardPrintRequestDemo.framework.service.inter.activityLog;

import com.noway.cardPrintRequestDemo.framework.dto.activityLog.ActivityLogDTO;
import com.noway.cardPrintRequestDemo.framework.entity.activityLog.ActivityLog;
import com.noway.cardPrintRequestDemo.framework.service.inter.IGenericService;

public interface IActivityLog extends IGenericService<ActivityLog, ActivityLogDTO> {

    ActivityLogDTO save(ActivityLogDTO activityLogDTO);
}
