package cendy.chen.org.notify.config;

import cendy.chen.org.notify.aop.AuthorInterceptor;
import cendy.chen.org.notify.aop.SignInterceptor;
import cendy.chen.org.notify.job.PingCheckTask;
import cendy.chen.org.notify.service.AlertTextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {

    @Value("${wechat.cp.agentid}")
    private Integer agentid;


    @Bean
    public AuthorInterceptor authorInterceptor(){
        return new AuthorInterceptor();
    }

    @Bean
    public SignInterceptor signInterceptor(){
        return new SignInterceptor();
    }

    @Bean
    public PingCheckTask pingCheckTask(){
        return new PingCheckTask();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(signInterceptor());
        registry.addInterceptor(authorInterceptor());
    }

    @Bean
    public AlertTextBuilder alertTextBuilder(){
        return new AlertTextBuilder(agentid);
    }
}
