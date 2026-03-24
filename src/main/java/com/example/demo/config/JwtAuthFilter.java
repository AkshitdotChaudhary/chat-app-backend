package com.example.demo.config;

import com.example.demo.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter
    extends
    OncePerRequestFilter
{
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
        throws ServletException,
        IOException
    {
        String authHeader = request.getHeader( "Authorization" );
        if ( authHeader == null || !authHeader.startsWith( "Bearer " ) )
        {
            filterChain.doFilter( request, response );
            return;
        }
        String token = authHeader.substring( 7 );
        //        if ( jwtUtils.isValid( token ) )
        //        {
        //            Authentication auth = jwtUtils.getAuthentication( token );
        //            SecurityContextHolder.getContext().setAuthentication( auth );
        //        }
        filterChain.doFilter( request, response );
    }
}