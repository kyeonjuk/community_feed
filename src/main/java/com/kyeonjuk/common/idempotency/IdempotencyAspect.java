package com.kyeonjuk.common.idempotency;

import com.kyeonjuk.common.ui.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotencyAspect {

    private final IdempotencyRepository idempotencyRepository;
    private final HttpServletRequest request;

    @Around("@annotation(Idempotent)")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        String idempotencyKey = request.getHeader("Idempotency-Key");

        if (idempotencyKey == null) {
            return joinPoint.proceed();
        }

        Idempotency idempotency = idempotencyRepository.getByKey(idempotencyKey);

        if (idempotency != null) {
            return idempotency.getResponse();
        }

        Object result = joinPoint.proceed();

        Idempotency newIdempotency = new Idempotency(idempotencyKey, (Response<?>) result);
        idempotencyRepository.save(newIdempotency);

        return result;
    }

}


