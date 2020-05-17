package edu.mangement.config;

import edu.mangement.security.FilterSystem;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:01 AM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        var listUrls = Arrays.asList("/login","/logout","/403","/logoutSuccessful","/assets/**","/j_spring_security_check","/upload/**");
        registry.addInterceptor(new FilterSystem())
                .addPathPatterns("/**")
                .excludePathPatterns(listUrls);
    }
}
