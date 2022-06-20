package com.noway.cardPrintRequestDemo.framework.controller;

import com.noway.cardPrintRequestDemo.framework.entity.jwtModels.JwtRequest;
import com.noway.cardPrintRequestDemo.framework.entity.jwtModels.JwtResponse;
import com.noway.cardPrintRequestDemo.framework.service.impl.user.UserDetailsServiceImpl;
import com.noway.cardPrintRequestDemo.framework.utility.JWTUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestController {

    private static final Logger LOG = Logger.getLogger(UserRestController.class.getName());

    private final JWTUtility jwtUtility;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public UserRestController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        LOG.info("request For JwtToken from Username = "+jwtRequest.getUsername());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
