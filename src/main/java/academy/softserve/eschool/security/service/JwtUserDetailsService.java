package academy.softserve.eschool.security.service;

import academy.softserve.eschool.model.User;
import academy.softserve.eschool.repository.UserRepository;
import academy.softserve.eschool.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
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
