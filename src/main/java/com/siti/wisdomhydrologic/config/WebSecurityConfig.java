/*
package com.siti.wisdomhydrologic.config;

import com.siti.wisdomhydrologic.user.HandlerProvider.RestAuthenticationEntryPoint;
import com.siti.wisdomhydrologic.user.HandlerProvider.WhAuthenticationFailureHandler;
import com.siti.wisdomhydrologic.user.HandlerProvider.WhAuthenticationProvider;
import com.siti.wisdomhydrologic.user.HandlerProvider.WhAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private WhAuthenticationProvider myAuthenticationProvider;
    private WhAuthenticationSuccessHandler restAuthenticationSuccessHandler;

    @Autowired
    public void setMyAuthenticationProvider(WhAuthenticationProvider myAuthenticationProvider) {
        this.myAuthenticationProvider = myAuthenticationProvider;
    }

    @Autowired
    public void setRestAuthenticationSuccessHandler(WhAuthenticationSuccessHandler restAuthenticationSuccessHandler) {
        this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login/logout"
                , "/user/getEmailCheckCode"
                , "/user/checkInLaw"
                , "/user/updatePwdByEmailCode"
                , "/uploadFiles"
                , "/sysInfo/getPCversion"
                , "/sysInfo/getAPPversion"
                , "/dispatch/listDispatchDeal"
                , "/casePoint/saveCasePoint"
                , "/count/countRecentWeek"
                , "/count/dealRateRecentWeek"
                ,"/verify/getVerify"
                ,"/position*/
/**"
                ,"/bikeDispatch*/
/**"
                ,"/boundary*/
/**"
                ,"/bicycleTotal*/
/**"
                ,"/kpHour*/
/**"
                ,"/ble*/
/**"
                ,"/personPosition*/
/**"
                ,"/plat*/
/**"
        );
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();        // 解决前端无法使用iframe的问题
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("*/
/*").permitAll()
                // 其他地址访问需要验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //.loginPage("/login")// 指定登录页
                //.successForwardUrl("/indexHome")// 登录成功后的主页
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(new WhAuthenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

}
*/
