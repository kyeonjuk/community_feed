package com.kyeonjuk.common.principal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)      // 어노테이션 적용 방식(= 메소드의 매개변수에만 사용)
@Retention(RetentionPolicy.RUNTIME) // 유지 기간 (= 런타임 동안 유지)
public @interface AuthPrincipal {

}
