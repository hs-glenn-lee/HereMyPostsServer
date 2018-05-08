package web.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 * 프론트엔드에서 ajax 사용을위한 CORS설정
 * */
@Configuration
@EnableWebMvc
public class CORSConfiguration extends WebMvcConfigurerAdapter{
	
	@Value("${spring.profiles.active}")
	private String activeSpringProfiles;
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		if(activeSpringProfiles == null)
			return;
		
		if(activeSpringProfiles.equals("development")) {
			 registry.addMapping("/**")
             .allowedMethods("GET", "POST", "PUT")
             .allowedOrigins("http://localhost:8081");
		}
    }
	 
}
