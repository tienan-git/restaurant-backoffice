package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.opentone.arapp.backoffice.db.entity.User;
import jp.co.sparkworks.restaurant.backoffice.dao.UserCustomDao;
import jp.co.sparkworks.restaurant.backoffice.dto.LoginUser;

@Service
public class UserDetailsServiceCustomerImpl implements UserDetailsService {

    @Autowired
    HttpSession session;

    @Autowired
    UserCustomDao userCustomDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // try {
        User user = userCustomDao.selectByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("該当ユーザ存在しません{" + email + "}");
        }

        Collection<GrantedAuthority> authorities = getAuthorities(user.getRoleId());

        // カレントユーザIDをセッションに設定
        session.setAttribute("userId", user.getUserId().toString());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("email", user.getEmail());

        return new LoginUser(user, authorities);
        // } catch (ResourceNotFoundException e) {
        // throw new UsernameNotFoundException("USER NOT FOUND", e);
        // }
    }

    /**
     * ロールの権限リスト取得
     */
    private Collection<GrantedAuthority> getAuthorities(Long roleId) {

        Collection<GrantedAuthority> authorityCollection = new ArrayList<GrantedAuthority>();
        // 権限を取得
        List<String> authorityList = userCustomDao.selectByRoleId(String.valueOf(roleId));
        for (String authority : authorityList) {
            authorityCollection.add(new SimpleGrantedAuthority(authority));
        }

        return authorityCollection;
    }

}
