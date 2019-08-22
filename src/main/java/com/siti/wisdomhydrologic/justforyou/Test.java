package com.siti.wisdomhydrologic.justforyou;

import com.google.common.collect.Lists;
import com.siti.wisdomhydrologic.user.entity.Permission;
import com.siti.wisdomhydrologic.user.service.UserDetailService;
import com.siti.wisdomhydrologic.user.vo.UserInfo;
import com.siti.wisdomhydrologic.util.ExceptionUtil;
import com.siti.wisdomhydrologic.util.enumbean.SystemError;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/7/25.
 *
 * @data ${DATA}-16:28
 */
@RestController
public class Test {

    @Autowired
    UserDetailService UserDetailService;

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @RequestMapping(value = "/rest/article", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "接口说明", httpMethod = "POST", notes = "接口发布说明")
    @ApiParam(name = "参数", value = "这是描述参数")
    public String testSwagger2(@RequestBody String name) {
        return name;
    }

    @RequestMapping(value = "/updateS", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "station", httpMethod = "GET", notes = "station")
    @ApiParam(name = "station", value = "station")
    public int updateS(@Param("station") String station){
        System.out.print(station);
        return 1;
    }

    //@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public String updateS(){
        UserInfo details= (UserInfo) UserDetailService.loadUserByUsername("dc");
        List<Permission> all=details.getMenuList();
        Permission last=new Permission(){{
            setId(1);
            setDescription("WH");
            setSort(0);
            setChild(new ArrayList<Permission>());
        }};
        backToFront(0,last,all);
       List<Permission> p=new ArrayList<>();
        p.add(last);
        details.setMenuList(p);
        return "/login";
    }

    public static void backToFront(int root, Permission finalP, List<Permission> all) {
        int next = root + 1;
        List<Permission> child= Lists.newArrayList();
        for(Permission one:all){
           String[] str= one.getPath().split(",");
            if(str.length>root){
                if(str[root].equals(finalP.getId() + "")&& one.getSort() == next){
                    child.add(one);
                }
            }
        }
        if (child == null || child.size() < 1) {
            return;
        }
        finalP.setChild(child);
        child.stream().forEach(e -> {
            backToFront(next, e, all);
        });
    }

    @RequestMapping("/login-error")
    public String loginError(){
        return "login-error";
    }

    public void quartzJob() {

        System.out.println("----------------------here comes!-------------------------------");
    }

    @RequestMapping(value = "/do/d", method = RequestMethod.GET, produces = "application/json")
    public String dod(){
        return "/do";
    }
}
