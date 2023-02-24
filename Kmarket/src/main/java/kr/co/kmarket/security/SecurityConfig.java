package kr.co.kmarket.security;

import kr.co.kmarket.entity.PersistentLogins;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityUserService service;
	@Autowired
	private DataSource dataSource;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 인가(접근권한) 설정
		// 전체 접근 가능
		http.authorizeHttpRequests().antMatchers("/", "/index").permitAll();

		// 일반 회원, 판매자 회원, 관리자 접근 가능
		// 모든 마이 페이지
		http.authorizeHttpRequests().antMatchers("/my/**").hasAnyRole("1", "2", "5");

		// 판매자 회원, 관리자 만 접근 가능
		http.authorizeHttpRequests()
				.antMatchers("/admin/product/**","/admin/index")
				.hasAnyRole("2", "5");
		// 관리자만 접근 가능
		http.authorizeHttpRequests().antMatchers("/admin/cs/**").hasAnyRole("5");

		// 사이트 위변조 요청 방지
		http.csrf().disable();

		//자동로그인 설정
		http.rememberMe()
		.key("unique")
		//login페이지 자동로그인 input name
		.rememberMeParameter("remember-me")
		//쿠키유지시간 초단위(일주일로설정)
		.tokenValiditySeconds(86400)
		.alwaysRemember(false)
		.userDetailsService(service)
		.tokenRepository(tokenRepository());

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
		.logoutSuccessUrl("/member/login?success=200")
		.deleteCookies("JSESSIONID", "remember-me");

		http.userDetailsService(service);

		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//자동로그인할때 필요한 토큰?
	@Bean
	public PersistentTokenRepository tokenRepository(){
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
}
