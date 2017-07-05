package mx.com.beo.config;
  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mx.com.beo.models.Urls;
 
@ComponentScan("mx.com.beo")
@Configuration
public class AutenticacionAppConfig {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/BEO/v1/accesoCliente").allowedOrigins("*");
            	registry.addMapping("/BEO/v1/cambioContrasena").allowedOrigins("*");
            }
        };
    }
	
	@Bean
	public Urls urls(){
		return new Urls();
	}
	 
	
}
