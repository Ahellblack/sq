package com.siti.wisdomhydrologic.user.service;

import com.siti.wisdomhydrologic.user.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by DC on 2019/8/19.
 *
 * @data ${DATA}-14:29
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
//这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        return new UserInfo("admin", "123456", "ROLE_ADMIN", true, true, true, true);
    }

}

