package com.gradleJSP.demo.config;

import com.gradleJSP.demo.entity.Account;
import com.gradleJSP.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    /**
     1. 패스워드 암호화 (다양한 암호화 모듈을 가지고 있다.)
        - 암호 크래킹에 대한 저항력을 높이기 위해 암호 확인하는데 1초가 걸리도록함(기본값 10)
    */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     2. SecurityFilterChain
        - Spring Security의 필터에대한 체인을 관리하는 부분
        - 기본적인 설정 및 어떤 필터들을 사용할 것인가에 대한 정의를 하는 메서드
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 공격방지
                .csrf(withDefaults())
                // 인증 필터
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/register").permitAll()
                        .anyRequest().authenticated()
                )

                // 기본 인증 필터
                //.httpBasic(withDefaults())
                // 사용자 이름&비밀번호 인증 필터
                //.formLogin(withDefaults());
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/")
                )
                .userDetailsService(new CustomUserDetailsService())
                //.authenticationManager(new CustomAuthenticationProvider())
                // 커스텀 필터 추가
                // .addFilterBefore(new CustomFilter()));
                // 캐시 관련
                // .requestCache(new HttpSessionRequestCache()))
                // 세션 관리
                .sessionManagement(session -> session
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession) // 세션 고정 보호
                        .invalidSessionUrl("/login") // 만료된 세션 요청시 이동할 url
                        .maximumSessions(1) // 중복 로그인 차단 (단일 사용자 로그인)
                        .maxSessionsPreventsLogin(true) // 두번째 로그인이 차단됨
                )
                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true) // 세션 초기화
                        .deleteCookies("JSESSIONID", "remember-me") // 쿠키 삭제
                        // Clear-Site-Data를 사용하여 로그아웃 -> 세션 쿠키를 포함한 모든 항목 정리됨
                        // 이를 이용해 쿠키를 지우려면 마지막 매개변수로 ClearSiteDataHeaderWriter.Directive.COOKIES 를 주면됨
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                        .permitAll()

                )
                ;

        return http.build();
    }

    /*
        SecurityContextHolder 에 SecurityContext 가 들어있고
        SecurityContext에 Authentication이 들어있고 Authentication에 인증된 유저의 정보가 들어있다.
        Authentication에는 principal, credentials, authorities 를 가지고 있다.
        // 현재 인증된 사용자에 대한 정보 접근
        // SecurityContext context = SecurityContextHolder.getContext();
        // Authentication authentication = context.getAuthentication();
        // String username = authentication.getName();
        // Object principal = authentication.getPrincipal();
        // Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
     */

    /**
     * 3. UsernamePasswordAuthenticationToken
     * AuthenticationManager
     * - 인증 처리 방법을 정의하는 API
     * - 인증을 처리하면 SecurityContextHolder에 Authentication 값이 세팅됨.
     * AuthenticationManager(ProviderManager)가 가지고 있는 provider 목록을 순회하면서 provider 가 실행 가능한 경우에
     * provider의 authenticate 메서드를 호출하여 인증 절차를 수행한다.
     * CustomProvider 를 구현하여 Config를 통해 AuthenticationManager에 추가 함으로써 추가 인증 절차 구현
     * class명은 어떤 인증 절차에 따라 확실하게 네이밍 하는 것도 좋다.
     */
//    class CustomAuthenticationProvider implements AuthenticationProvider{
//        @Override
//        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//            String username = authentication.getName();
//            String password = authentication.getCredentials().toString();
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//            UserDetails userDetails = new CustomUserDetailsService().loadUserByUsername(username);
//            if(!passwordEncoder().matches(password, userDetails.getPassword())){
//                return null;
//            }
//
//            return new UsernamePasswordAuthenticationToken(username, password, authorities);
//        }
//
//        // supports 함수를 재정의하여 언제 provider 를 사용할 건지 조건을 지정할 수 있다.
//        @Override
//        public boolean supports(Class<?> authentication) {
//            // false를 return하면 해당 provider의 authenticate() 가 호출되지 않는다.
//            return true;
//        }
//    }

    /**
     * UserDetailsService
     * - 유저의 계정, 비밀번호, 권한을 어디에 저장할 것인가
     * 1) InMemory
     * @Bean
     * public UserDetailsService userDetailsService(){
     *    UserDetails admin = User.builder()
     *      .username("admin")
     *      .password(passwordEncoder().encode("admin"))
     *      .roles("USER","ADMIN")
     *      .build();
     *    return new InMemoryUserDetailsManager(admin);
     * }
     * 2) JDBC
     * datasource를 정의하여 주입 받은 뒤 InMemory와 동일하게 admin을 UserDetail로 정의
     * JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
     * users.createUser(admin);
     *
     * 3) 사용자 정의 UserDetailsService
     * UserDetailsService를 구현한 CustomUserDetailsService를 정의함으로써
     * JPA와 연결되어있는 DB에 저장되어있는 데이터와 입력값을 검증하여 로그인이 진행되도록 사용자 정의
     * 최종적으로 DbUserDetailService()를 SecurityFilterChain에 추가해줌으로써 인증방식 변경
     */

    @Autowired
    private AccountRepository accountRepository;

    @Service
    class CustomUserDetailsService implements UserDetailsService{

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Account> account = accountRepository.findById(username);
            if(!account.isPresent()) {
                throw new UsernameNotFoundException(username);
            }
            UserDetails userDetails = User.builder()
                    .username(account.get().getUsername())
                    .password(account.get().getPassword())
                    .roles(account.get().getRole())
                    .build();
            return userDetails;
        }
    }

    /**
     * 인증 지속 및 세션 관리를 위한 이벤트 퍼블리셔를 @Bean에 추가해줘야한다.
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }




}

