package com.example.ppmfullstack.security;

import com.example.ppmfullstack.domain.User;
import com.example.ppmfullstack.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.example.ppmfullstack.security.SecurityConstants.HEADER_STRING;
import static com.example.ppmfullstack.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try
        {
            String jwt=getJwtFromRequest(httpServletRequest);
            if(StringUtils.hasText(jwt)&&tokenProvider.validateToken(jwt))
            {
                Long userId= tokenProvider.getUserIdFromJWT(jwt);
                User userDetails=customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                        userDetails,null, Collections.emptyList());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        catch (Exception e)
        {
logger.error("could not user authenction in secuirt context,",e);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
    private String getJwtFromRequest(HttpServletRequest request)
    {
        String bearerToken=request.getHeader(HEADER_STRING);
        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX))
        {
              return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
