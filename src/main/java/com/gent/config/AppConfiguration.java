package com.gent.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import java.util.Locale;
/**
 * Created by daria on 30.09.2016.
 */

@Configuration
@ComponentScan("com.gent")
@Import({DBConfig.class, SecurityApplicationInitializer.class, SecurityConfig.class, MailConfiguration.class})
@EnableWebMvc
public class AppConfiguration extends WebMvcConfigurerAdapter {

//    public static String urlWriteImages = "C:/jav/i18n/goods/";
//    public static String urlReadImages = "file:///C:/jav/i18n/goods/";

    public static String urlWriteImages = "/home/daria/gent/goods/";
    public static String urlReadImages = "file:///home/daria/gent/goods/";


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("20MB");
        factory.setMaxRequestSize("20MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;


    @PostConstruct
    public void init() {
        requestMappingHandlerAdapter.setIgnoreDefaultModelOnRedirect(true);
    }
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/i18/usermsg");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("ru"));
        resolver.setCookieName("localeCookie");
        resolver.setCookieMaxAge(-1);

        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);

    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
        //        registry.addResourceHandler("/images/**").addResourceLocations("file:///home/daria/gent/goods/");
       registry.addResourceHandler("/images/**").addResourceLocations(urlReadImages);
       registry.addResourceHandler("/robots.txt").addResourceLocations(urlReadImages+"/robots.txt");
        registry.addResourceHandler("/sitemap.xml").addResourceLocations(urlReadImages+"/sitemap.xml");

    }



}
