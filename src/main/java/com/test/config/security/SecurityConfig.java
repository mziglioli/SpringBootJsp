package com.test.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.config.security.service.UserDetailsService;
import com.test.config.security.voter.UserRoleVoter;
import com.test.config.servlet.ServletConfiguration;
import com.test.controller.ControllerDefault;
import com.test.service.ServicePackage;
import com.test.util.Catalago;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = { ServletConfiguration.class, ControllerDefault.class, ServicePackage.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http.csrf().disable().anonymous()
		.and().authorizeRequests()
				.antMatchers(Catalago.URL_PUBLIC_ALL_BASE).permitAll()
				.anyRequest().authenticated()
				.and().formLogin().permitAll()
			.loginPage(Catalago.URL_LOGIN)
				.defaultSuccessUrl(Catalago.URL_HOME)
				.usernameParameter(Catalago.LOGIN_PARAM_USERNAME)
				.passwordParameter(Catalago.LOGIN_PARAM_PASSWORD)
				.and().exceptionHandling().accessDeniedPage(Catalago.URL_DENIED)
		.and()
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

	@Bean
	public RoleVoter roleVoter() {
		return new UserRoleVoter();
	}
}