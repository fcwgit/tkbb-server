package cn.com.yusys.tkbb.config;

import cn.com.yusys.tkbb.component.LocaleResolverComponent;
import cn.com.yusys.tkbb.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//使用WebMvcConfigurer扩展springmvc的功能
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("success");
    }


    //将页面访问的  / , /index.html都转发到thymeleaf处理
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源：*.css、*.js
                //SpringBoot已经做好了静态资源映射
                //TODO
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/*")
                .excludePathPatterns("/index.html","/","/login");
            }
        };
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new LocaleResolverComponent();
    }

}
