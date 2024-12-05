package com.kyeonjuk.common.principal;

import com.kyeonjuk.auth.domain.TokenProvider;
import com.kyeonjuk.common.domain.exception.ErrorCode;
import com.kyeonjuk.common.ui.BaseException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

// 1. 커스텀 어노테이션 동작 설정 (해당 어노테이션 여부 확인 -> 원하는 함수 실행)
// 2. header 의 Authorization 필드의 "Bearer " 타입의 토큰을 파싱해서 값을 전달
public class AuthPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    public AuthPrincipalArgumentResolver(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // 매개변수에 @커스텀_매개변수_어노테이션이 붙어있는지 여부 확인
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // true 면 아래의 resolveArgument 실행
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        try {
            // Authorization 필드의 토큰가져오기
            String authToken = webRequest.getHeader("Authorization");

            if (authToken == null || authToken.split(" ").length != 2) {
                throw new IllegalArgumentException();
            }

            // Authorization : Bearer ~
            String token = authToken.split(" ")[1];
            Long userId = tokenProvider.getUserId(token);
            String role = tokenProvider.getUserRole(token);

            return new UserPrincipal(userId, role);

        } catch (Exception e) {
            throw new BaseException(ErrorCode.INVALID_TOKEN);
        }

    }
}
