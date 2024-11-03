package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect // AOP로 사용하기 위해 작성해주어야 함
@Component //로 스프링빈으로 등록해도 되지만 Config에서 등록하는 걸 더 선호함
public class TimeTraceApp {
    @Around("execution(* hello.hello_spring..*(..))\n") // 패키지 하위에는 다 적용한다 -> 원하는 대상에 적용할 수 있음
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish -start;
            System.out.println("END: " + joinPoint.toString() +" "+ timeMs + "ms");
        }
    }
}