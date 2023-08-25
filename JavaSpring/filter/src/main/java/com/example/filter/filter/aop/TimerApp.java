package com.example.filter.filter.aop;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class TimerApp {
    @Pointcut(value = "within(com.example.filter.controller.UserApiController)") //only beans
    public void timerPointCut(){}

    @Before(value ="timerPointCut()")
    public void before(JoinPoint jointPoint){
        System.out.println("Before");
    }

    @After(value ="timerPointCut()")
    public void after(JoinPoint jointPoint){
        System.out.println("After");
    }

    @AfterReturning(value ="timerPointCut()", returning = "result") // only success
    public void afterReturning(JoinPoint jointPoint, Object result){
        System.out.println("After Returning");
    }


    @AfterThrowing(value ="timerPointCut()", throwing = "tx") // only exception
    public void afterThrowing(JoinPoint jointPoint, Throwable tx){
        System.out.println("After Throwing");
    }

    @Around(value = "timerPointCut()") // between before and after
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("before method run");
        Arrays.stream(joinPoint.getArgs()).forEach( // convert object
                it-> {
                    if(it instanceof UserRequest){
                        var tempUser = (UserRequest)it;
                        var phoneNumber = tempUser.getPhoneNumber().replace("-","");
                        tempUser.setPhoneNumber(phoneNumber);
                    }
                }
        );
        //encrypt/decrypt/logging
        var newObjs = List.of(
                new UserRequest()
        );

        var stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed(); // proceed(newObjs)
        stopWatch.stop();
        System.out.println("total time : "+stopWatch.getTotalTimeMillis());
        System.out.println("after method run");
    }

}
