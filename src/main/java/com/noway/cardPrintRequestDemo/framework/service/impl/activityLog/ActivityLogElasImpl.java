package com.noway.cardPrintRequestDemo.framework.service.impl.activityLog;

import com.noway.cardPrintRequestDemo.framework.entity.activityLog.ActivityLogElas;
import com.noway.cardPrintRequestDemo.framework.repository.activityLog.ActivityLogElasRepository;
import com.noway.cardPrintRequestDemo.framework.service.inter.activityLog.IActivityLogElas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityLogElasImpl implements IActivityLogElas {

    @Autowired
    private ActivityLogElasRepository activityLogElasRepository;

    @Override
    public ActivityLogElas save(ActivityLogElas activityLogElas) {
        return activityLogElasRepository.save(activityLogElas);
    }

    @Override
    public void deleteActivityLogElas(ActivityLogElas activityLogElas) {
        activityLogElasRepository.delete(activityLogElas);
    }

    @Override
    public ActivityLogElas findOne(String id) {
        return activityLogElasRepository.findById(id).get();
    }

    @Override
    public Iterable<ActivityLogElas> findAll() {
        return activityLogElasRepository.findAll();
    }

    @Override
    public Page<ActivityLogElas> findByCardNumber(String cardNumber, PageRequest pageRequest) {
        return activityLogElasRepository.findByCardNumber(cardNumber,pageRequest);
    }

    @Override
    public List<ActivityLogElas> findByFunctionName(String functionName) {
        return activityLogElasRepository.findByFunctionName(functionName);
    }
}
