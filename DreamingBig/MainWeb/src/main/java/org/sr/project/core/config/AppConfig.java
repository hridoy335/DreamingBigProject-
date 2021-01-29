package org.sr.project.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration

//@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:config/application-${envTargert:postgresql}.properties")
})
@ComponentScan("org.sr.project.core")
//@EnableJpaRepositories
public class AppConfig {

    //region PRIVATE VARIABLES
    private final static int CONN_PULL_SIZE = 5;
    //endregion

    @Autowired
    private Environment environment;

    public AppConfig(){
        super();
    }
}
