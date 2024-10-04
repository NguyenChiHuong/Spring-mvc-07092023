package com.javaweb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.javaweb.dto.MyUser;

public class SecurityUtils {
	public static MyUser getPrincipal() {
		MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();//Duy trì thông tin tài khoản sau khi đăng nhập 
        return myUser;
    }
	
	@SuppressWarnings("unchecked") //lấy danh sách quyền sử dụng của người dùng
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
		return results;
	}
}
