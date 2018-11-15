package academy.softserve.eschool.service;

import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.JwtUserFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    @NonNull
    private UserRepository userRepository;

    /**
     * Find user by username in database
     * Returns {@link academy.softserve.eschool.security.JwtUser} object
     * @param s Username
     * @return Object of {@link academy.softserve.eschool.security.JwtUser}
     * @throws UsernameNotFoundException if no user find with specified username username
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
