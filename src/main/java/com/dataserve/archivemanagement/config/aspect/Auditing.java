package com.dataserve.archivemanagement.config.aspect;

import com.dataserve.archivemanagement.model.AppUsers;
import com.dataserve.archivemanagement.model.MobileAudit;
import com.dataserve.archivemanagement.model.MobileAuditParameter;
import com.dataserve.archivemanagement.repository.MobileAuditParamRepository;
import com.dataserve.archivemanagement.repository.MobileAuditRepository;
import com.dataserve.archivemanagement.repository.UsersRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;


@Aspect
@Component
public class Auditing {
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    MobileAuditRepository mobileAuditRepository;
    @Autowired
    MobileAuditParamRepository mobileAuditParamRepository;

    @AfterReturning(pointcut = "execution(* com.dataserve.archivemanagement.controller..*(..))", returning = "result")
    public void logBefore(JoinPoint pjp, Object result) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String methodName = methodSignature.getMethod().getName();
        ObjectMapper mapper = new ObjectMapper();
        String res = mapper.writeValueAsString(result);
        if (!methodName.equals("loginAndGenerateToken")) {
            MobileAuditParameter mobileAuditParameter = null;
            MobileAudit mobileAudit = new MobileAudit();
            List<MobileAuditParameter> auditParameterList = new ArrayList<>();
            String[] parameterNames = methodSignature.getParameterNames();
            Object[] parameterValues = pjp.getArgs();
            mobileAudit.setMethodName(methodName);
            mobileAudit.setResult(res);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<AppUsers> loggedUser = usersRepo.findByUserNameLdap(authentication.getName());
            if (loggedUser.isPresent()) {
                AppUsers users = loggedUser.get();
                String userNameLdap = users.getUserNameLdap();
                mobileAudit.setUserName(userNameLdap);
                mobileAudit.setAppUser(users);
            }
            mobileAudit.setTransactionDate(new Date());
            mobileAudit.setAuditParameterList(auditParameterList);
            MobileAudit saveMobileAudit = mobileAuditRepository.save(mobileAudit);
            for (int i = 0; i < parameterNames.length; i++) {
                mobileAuditParameter = new MobileAuditParameter(parameterNames[i], parameterValues[i].toString());
                mobileAuditParameter.setMobileAudit(new MobileAudit(saveMobileAudit.getId()));
                auditParameterList.add(mobileAuditParameter);
            }
            mobileAuditParamRepository.saveAll(auditParameterList);
        }

    }

}


