package com.kyeonjuk.common.idempotency;

import com.kyeonjuk.common.ui.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component  // 스프링에 등록
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyRepository idempotencyRepository;
    private final HttpServletRequest request;

    @Around("@annotation(Idempotent)")  // 특정로직의 실행 전과 실행 후를 관여
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        String idempotencyKey = request.getHeader("Idempotency-Key");

        // 특정 로직을 처음 실행 할 경우
        if (idempotencyKey == null) {
            return joinPoint.proceed();
        }

        Idempotency idempotency = idempotencyRepository.getByKey(idempotencyKey);

        // 특정 로직을 재실행하는 경우
        if (idempotency != null) {
            return idempotency.getResponse();   // 로직을 수행하지 않고 저장된 응답 값 반환
        }

        Object result = joinPoint.proceed();    // 로직을 수행

        Idempotency newIdempotency = new Idempotency(idempotencyKey, (Response<?>) result);
        idempotencyRepository.save(newIdempotency);

        return result;
    }

}
