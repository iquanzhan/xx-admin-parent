package com.chengxiaoxiao.security.auth;

import com.chengxiaoxiao.core.exception.NotLoginException;
import com.chengxiaoxiao.core.exception.NotPermissionException;
import com.chengxiaoxiao.core.exception.NotRoleException;
import com.chengxiaoxiao.core.pojo.LoginUser;
import com.chengxiaoxiao.core.util.SpringContextHolderUtil;
import com.chengxiaoxiao.security.annotation.Logical;
import com.chengxiaoxiao.security.annotation.RequiresPermissions;
import com.chengxiaoxiao.security.annotation.RequiresRoles;
import com.chengxiaoxiao.security.service.TokenService;
import com.chengxiaoxiao.security.utils.SecurityUtil;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限底层实现类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2022-02-22 14:39
 */
public class AuthLogic {
    /**
     * 管理员角色权限标识
     */
    private static final String SUPER_ADMIN = "admin";
    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    public TokenService tokenService = SpringContextHolderUtil.getBean(TokenService.class);

    /**
     * 校验用户是否登录。
     */
    public void checkLogin() {
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser == null) {
            throw new NotLoginException("无效的token");
        }
    }

    /**
     * 校验用户是否有对应的角色信息
     *
     * @param requiresRoles 角色校验注解
     */
    public void checkRole(RequiresRoles requiresRoles) {
        if (requiresRoles.logical() == Logical.AND) {
            checkRoleAnd(requiresRoles.value());
        } else {
            checkRoleOr(requiresRoles.value());
        }
    }

    /**
     * 获取当前账号的角色列表
     *
     * @return 角色列表
     */
    public Set<String> getRoleList() {
        try {
            LoginUser loginUser = SecurityUtil.getLoginUser();
            return loginUser.getRoles();
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    /**
     * 校验当前用户是否包含此角色，包含一个即可
     *
     * @param roles 参照的角色
     */
    private void checkRoleOr(String[] roles) {
        Set<String> roleList = getRoleList();
        for (String role : roles) {
            if (hasRole(roleList, role)) {
                return;
            }
        }
        if (roles.length > 0) {
            throw new NotRoleException(roles);
        }
    }

    /**
     * 校验当前用户是否包含指定注解，必须全部存在
     *
     * @param roles 接口中的角色列表
     */
    private void checkRoleAnd(String[] roles) {
        Set<String> roleList = getRoleList();
        for (String role : roles) {
            if (!hasRole(roleList, role)) {
                throw new NotRoleException(role);
            }
        }
    }

    /**
     * 判断是否包含角色
     *
     * @param roles 角色列表
     * @param role  角色
     * @return 用户是否具备某角色权限
     */
    public boolean hasRole(Collection<String> roles, String role) {
        return roles.stream().filter(StringUtils::hasText)
                .anyMatch(x -> SUPER_ADMIN.contains(x) || PatternMatchUtils.simpleMatch(x, role));
    }


    /**
     * 校验是否有指定的权限信息
     *
     * @param requiresPermissions 权限
     */
    public void checkPermission(RequiresPermissions requiresPermissions) {
        String[] permissionArray = requiresPermissions.value();
        if (requiresPermissions.logical() == Logical.AND) {
            this.checkPermissionAnd(permissionArray);
        } else {
            this.checkPermissionOr(permissionArray);
        }
    }

    /**
     * 获取当前账号的权限列表
     *
     * @return 权限列表
     */
    public Set<String> getPermissionList() {
        try {
            LoginUser loginUser = SecurityUtil.getLoginUser();
            return loginUser.getPermissions();
        } catch (Exception e) {
            return new HashSet<>();
        }
    }


    private void checkPermissionOr(String[] permissions) {
        Set<String> permissionList = getPermissionList();
        for (String permission : permissions) {
            if (hasPermission(permissionList, permission)) {
                return;
            }
        }
        if (permissions.length > 0) {
            throw new NotPermissionException(permissions);
        }
    }

    private void checkPermissionAnd(String[] permissions) {
        Set<String> permissionList = getPermissionList();
        for (String permission : permissions) {
            if (!hasPermission(permissionList, permission)) {
                throw new NotPermissionException(permission);
            }
        }
    }


    /**
     * 判断是否包含权限
     *
     * @param authorities 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(Collection<String> authorities, String permission) {
        return authorities.stream().filter(StringUtils::hasText)
                .anyMatch(x -> ALL_PERMISSION.contains(x) || PatternMatchUtils.simpleMatch(x, permission));
    }


    /**
     * 根据token获取当前登录用户信息
     * @param token token
     * @return 登录用户
     */
    public LoginUser getLoginUser(String token) {
        return tokenService.getLoginUser(token);
    }

    /**
     * 判断当前登录用户是否过期
     * @param loginUser 登录用户信息
     */
    public void verifyLoginUserExpire(LoginUser loginUser) {
        tokenService.verifyToken(loginUser);
    }
}
