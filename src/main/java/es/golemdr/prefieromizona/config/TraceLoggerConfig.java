package es.golemdr.prefieromizona.config;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.golemdr.prefieromizona.ext.aop.TraceInterceptor;

@Configuration
// @EnableAspectJAutoProxy(proxyTargetClass=true) // Viene implícita en la configuración de SpringBoot
public class TraceLoggerConfig {
	
    @Bean
    public CustomizableTraceInterceptor customizableTraceInterceptor() {
    	TraceInterceptor customizableTraceInterceptor = new TraceInterceptor();
        customizableTraceInterceptor.setUseDynamicLogger(true);
        customizableTraceInterceptor.setEnterMessage("Entrando $[targetClassShortName].$[methodName]($[arguments])");
        customizableTraceInterceptor.setExitMessage("Saliendo $[targetClassShortName].$[methodName](): $[returnValue]");
        
        return customizableTraceInterceptor;
    }
    
    
    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * es.golemdr.prefieromizona.service..*(..))");
        pointcut.setExpression("execution(public * es.golemdr.prefieromizona.controller..*(..))");
        return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
    }    
}