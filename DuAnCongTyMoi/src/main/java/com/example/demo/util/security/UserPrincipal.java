/**
 * 
 */
package com.example.demo.util.security;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Object to authenticate
 *
 * @author duongduc
 * @version 1.0
 * @since 2024-04-08
 */
@Getter
@Setter
public class UserPrincipal extends AbstractUserPrincipal implements Serializable {

    private static final long serialVersionUID = 6960173949433045836L;

    private User user;

    /**
     * @param authorities
     * @param user
     */
	public UserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassWord(), authorities);

		this.user = user;
	}

    public Long getUserId() {
        return this.user.getId();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public String getPassword() {
        return this.user.getPassWord();
    }
}
