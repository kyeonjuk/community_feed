package com.kyeonjuk.common.config;

import com.kyeonjuk.auth.domain.TokenProvider;
import com.kyeonjuk.common.principal.AuthPrincipalArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 커스텀 어노테이션의 Handler인 HandlerMethodArgumentResolver를 커스텀한 클래스를 스프링에 등록
// +) WebMvcConfigurer : 웹 관련 설정
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    public AuthConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    // 매개변수 해석 방법 (커스텀_매개변수_어노테이션 handler 등록)
    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.add(new AuthPrincipalArgumentResolver(tokenProvider));
    }
}
