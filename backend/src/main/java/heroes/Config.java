package heroes;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import heroes.filter.AuthFilter;

@Configuration
public class Config {

		@Bean
		public FilterRegistrationBean authFilterRegistrationBean(){
			FilterRegistrationBean registrationBean = new FilterRegistrationBean();
			registrationBean.setName("auth");
			AuthFilter authFilter = new AuthFilter();
			registrationBean.setFilter(authFilter);
			registrationBean.setOrder(1);
			return registrationBean;
		}
}
