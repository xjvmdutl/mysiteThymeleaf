package hello.mysite.config;

import hello.mysite.interceptor.LoginInterceptor;
import hello.mysite.interceptor.LogoutInterceptor;
import hello.mysite.security.AuthUserHandlerMethodArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final LogoutInterceptor logoutInterceptor;

    private final AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authUserHandlerMethodArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
            .order(1)
            .addPathPatterns("/user/auth");
        registry.addInterceptor(logoutInterceptor)
            .order(2)
            .addPathPatterns("/user/logout");
    }
}
