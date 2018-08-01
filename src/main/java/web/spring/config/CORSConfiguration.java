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
//@EnableWebMVC 이거떄문에 view resolving 안됐었다. mvc-anntation-driven과 비슷한 의미라하니 기존 설정이 먹지 않도록 하게 하는듯
public class CORSConfiguration extends WebMvcConfigurerAdapter{
	
	@Value("${spring.profiles.active}")
	private String activeSpringProfiles;
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		if(activeSpringProfiles == null)
			return;
		
		if(activeSpringProfiles.equals("development")) {
			 registry.addMapping("/api/**")
             .allowedMethods("GET", "POST", "PUT","DELETE")
             .allowedOrigins("http://localhost:8081");
		}
    }
	 
}
