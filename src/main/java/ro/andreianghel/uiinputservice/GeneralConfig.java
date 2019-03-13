package ro.andreianghel.uiinputservice;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public static Gson getGson() {
        return new Gson();
    }
}
