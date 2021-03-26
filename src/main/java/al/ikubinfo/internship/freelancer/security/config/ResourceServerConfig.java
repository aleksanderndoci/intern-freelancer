package al.ikubinfo.internship.freelancer.security.config;

import al.ikubinfo.internship.freelancer.security.errors.AppAccessDeniedHandler;
import al.ikubinfo.internship.freelancer.security.errors.AppAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;

	@Autowired
	private AppAuthenticationEntryPoint authorizationErrorHandler;

	@Autowired
	private AppAccessDeniedHandler appAccessDeniedHandler;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceIds).tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/actuator/**", "/api/api-docs**", "/api/swagger-ui/**").permitAll()
				.antMatchers("/api/login", "/api/oauth/authorize**", "/api/oauth/token**").permitAll()
				.antMatchers("/api/experience**", "/api/jobPost**", "/api/application**").hasAuthority("USER")
				.antMatchers("/api/user**").hasAnyAuthority("ADMIN", "USER")
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(authorizationErrorHandler).accessDeniedHandler(appAccessDeniedHandler);
	}
}
