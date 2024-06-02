package ecommerce.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDtlService {
    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;
    Long getCurrentUserId();
}