package com.library.librarymgtsystem.serviceimpl;

import com.library.librarymgtsystem.model.Patron;
import com.library.librarymgtsystem.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatronRepository patronRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername method");
       Patron user = patronRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("email not found"));
       return user;
    }
}
