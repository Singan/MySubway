package com.traffic.common.config;

import com.traffic.common.filter.CustomCorsFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig   {

    private final String[] PERMIT_URL_ARRAY = {
            "/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui",
            "/swagger-ui.html",
            "/api-docs",
            "/v3/api-docs/**"
    };


    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().mvcMatchers(
                PERMIT_URL_ARRAY
        );
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    /**
     * 2. HTTP에 대해서 ‘인증’과 ‘인가’를 담당하는 메서드이며 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정을 담당하는 메서드이다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // [STEP1] 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않는다.
                .csrf().disable()
                .cors().and()
                // [STEP2] 토큰을 활용하는 경우 모든 요청에 대해 '인가'에 대해서 적용
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/member/*").permitAll()
                .anyRequest().authenticated()
                .and()
                // CorsFilter
                .addFilterBefore(new CustomCorsFilter(), CorsFilter.class)
                // [STEP4] Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                // [STEP5] form 기반의 로그인에 대해 비 활성화하며 커스텀으로 구성한 필터를 사용한다.
                .formLogin().disable();

        return http.build();
    }
}
