package com.test.config.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.test.config.security.service.UserDetailsService;
import com.test.config.servlet.ServletConfiguration;
import com.test.controller.ControllerDefault;
import com.test.service.ServiceDefault;
import com.test.util.Catalago;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan(basePackageClasses = { ControllerDefault.class, ServiceDefault.class, ServletConfiguration.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http.csrf().disable().anonymous()
		.and().authorizeRequests()
				.antMatchers(Catalago.URL_PUBLIC_ALL_BASE).permitAll()
				.anyRequest().authenticated()
				.and().formLogin().permitAll()
			.loginPage(Catalago.URL_PUBLIC_LOGIN)
				.defaultSuccessUrl(Catalago.URL_HOME)
				.usernameParameter(Catalago.LOGIN_PARAM_USERNAME)
				.passwordParameter(Catalago.LOGIN_PARAM_PASSWORD)
				.and().exceptionHandling().accessDeniedPage(Catalago.URL_PUBLIC_DENIED)
			.and().logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher(Catalago.URL_PUBLIC_LOGOUT))
	            .logoutSuccessUrl(Catalago.URL_PUBLIC_BASE)
	            .deleteCookies("JSESSIONID")
	            .deleteCookies("remember-me")
	            .invalidateHttpSession(true).permitAll()
	        .and()
	            .rememberMe();;	
		;
		//@formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService();
	}
}