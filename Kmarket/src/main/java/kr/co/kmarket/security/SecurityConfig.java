package kr.co.kmarket.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityUserService service;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 인가(접근권한) 설정
		http.authorizeHttpRequests().antMatchers("/", "/index").permitAll();
		http.authorizeHttpRequests().antMatchers("/admin/product/register").hasAnyRole("2", "5"); // 판매자 회원, 관리자

		// 사이트 위변조 요청 방지
		http.csrf().disable();


		// 로그인 페이지 설정
		http.formLogin()
		.loginPage("/member/login")
		.defaultSuccessUrl("/index")
		.failureUrl("/member/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		// 로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
		.logoutSuccessUrl("/member/login?success=200");
		http.userDetailsService(service);

		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
