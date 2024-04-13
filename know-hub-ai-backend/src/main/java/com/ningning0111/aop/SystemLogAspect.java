package com.ningning0111.aop;

import com.ningning0111.model.entity.Logs;
import com.ningning0111.service.LogsService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：何汉叁
 * @date ：2024/4/10 19:37
 * @description：TODO
 */
@Aspect
@Component
public class SystemLogAspect {
    @Resource
    private LogsService logsService;
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Controller层切点
    @Pointcut("@annotation(SystemControllerLog)")
    public void controllerAspect(){
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        //获得当前用户登录信息
        //获取登录用户信息
        try {
            Logs logs = new Logs();
            logs.setMethods((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()));
            logs.setDdesc(getControllerMethodDescription(joinPoint));
            logs.setIp(request.getRemoteAddr());//获得访问IP地址记录到日志的IP属性中
            logs.setOpertime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if ( logsService.save(logs)){
                System.out.println("添加日志成功");
            }else{
                System.out.println("添加日志失败");
            }
        }catch (Exception e){
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息：{}",e.getMessage());
        }
    }


    /**
     * @Description  获取注解中对方法的描述信息 用于Controller层注解
     *
     *                没参数的方法不记录日志!!
     *
     * @date 2023年11月9日 上午12:01
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }



}