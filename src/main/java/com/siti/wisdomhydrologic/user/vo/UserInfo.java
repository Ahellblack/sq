package com.siti.wisdomhydrologic.user.vo;

import com.siti.wisdomhydrologic.user.entity.Org;
import com.siti.wisdomhydrologic.user.entity.Permission;
import com.siti.wisdomhydrologic.user.entity.Role;
import com.siti.wisdomhydrologic.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DC on 2019/8/19.
 *
 * @data ${DATA}-14:27
 */
public class UserInfo extends User implements Serializable, UserDetails, Principal {

    private List<Permission> menuList;

    private List<Role> roleList;

    private List<Org> orgList;

    public UserInfo(User user, List<Permission> menuList, List<Role> roleList, List<Org> orgList) {
        super(user);
        this.menuList = menuList;
        this.roleList = roleList;
        this.orgList = orgList;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (menuList == null || menuList.size() < 1) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }

        //StringBuilder commaBuilder = new StringBuilder();


        /*for (Permission auth : menuList) {
            if (auth.getCode() == null || "".equals(auth.getCode())) {
                continue;
            }
            commaBuilder.append(auth.getCode()).append(",");
        }
        String authorities = null;
        if (commaBuilder.length() > 0) {
            authorities = commaBuilder.substring(0, commaBuilder.length());
        }
        this.auth = authorities;*/

        return AuthorityUtils.commaSeparatedStringToAuthorityList(null);
    }

    public void calToTree(int root, Permission finalP, List<Permission> all) {
        int next = root + 1;
        List<Permission> child = all.stream().filter(e ->
                (e.getPath().split(",")[root].equals(finalP.getId() + "") && e.getSort() == next))
                .collect(Collectors.toList());
        if (child == null || child.size() < 1) {
            return;
        }
        finalP.setChild(child);
        child.stream().forEach(e -> {
            calToTree(next, e, all);
        });
    }



    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    public List<Permission> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Permission> menuList) {
        this.menuList = menuList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Org> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<Org> orgList) {
        this.orgList = orgList;
    }

}
