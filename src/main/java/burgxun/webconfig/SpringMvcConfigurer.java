package burgxun.webconfig;

import burgxun.common.filter.MyFilter;
import burgxun.common.interceptor.MyInterceptor;
import burgxun.common.interceptor.SecondInterceptor;
import burgxun.common.servlet.ContextListenerServlet;
import burgxun.common.servlet.MyServlet;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;


/**
 * @ClassName SpringMvcConfig
 * @Auther burgxun
 * @Description:
 * @Date 2020/06/16 15:36
 **/
@Configuration
public class SpringMvcConfigurer implements WebMvcConfigurer {
    private Logger logger = LoggerFactory.getLogger(SpringMvcConfigurer.class);

    @Bean
    public ContextListenerServlet contextListenerServlet() {
        return new ContextListenerServlet();
    }

    @Bean
    public MyServlet myServlet() {
        return new MyServlet();
    }

    @Bean
    public MyFilter myFilter() {
        return new MyFilter();
    }

    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    /*
     * 添加拦截器 Interceptor
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.myInterceptor());
        registry.addInterceptor(this.secondInterceptor());
    }

    /*
     * 配置使用 MessageConverters
     * */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converter.setFeatures(
                SerializerFeature.WriteMapNullValue,//保留空字段
                SerializerFeature.WriteNullStringAsEmpty,//String null=>""
                SerializerFeature.WriteNullNumberAsZero//Number null=>0
        );
        converters.add(converter);
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(this.contextListenerServlet());
        return servletListenerRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(this.myServlet());
        servletRegistrationBean.setUrlMappings(Collections.singleton("/demo/test"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(this.myFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/demo/*"));
        return filterRegistrationBean;
    }


}
