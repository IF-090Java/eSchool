package academy.softserve.eschool.security;

import academy.softserve.eschool.model.Role;
import academy.softserve.eschool.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                Long.valueOf(user.getId()),
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static ArrayList<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        ArrayList<GrantedAuthority> authorities1 = new ArrayList<>();
        authorities1.add(new SimpleGrantedAuthority(role.toString()));
        return authorities1;
    }

}
