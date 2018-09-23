package jp.co.sparkworks.restaurant.backoffice.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jp.co.sparkworks.restaurant.backoffice.enums.Role;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class LoginUser extends User {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long userName;
    private String roleName;

    public LoginUser(jp.co.opentone.arapp.backoffice.db.entity.User user, Collection<GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);

        this.userId = user.getUserId();
        this.userName = user.getUserId();
        this.roleName = Role.of(user.getRoleId().intValue()).getLabel();

    }
}
