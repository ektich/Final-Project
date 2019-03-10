package com.ssm.shiro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * * @author 13979
 */
public class CorsFilter extends OncePerRequestFilter{

    //private static Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String origin = request.getHeader("Origin");
        if(null != origin && !"".equals(origin)){
            //logger.debug("request path: " + origin);
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with");
            response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST, DELETE");
            if(request.getMethod().equals(RequestMethod.OPTIONS.name())){
                response.setStatus(200);
                response.setHeader("Access-Control-Max-Age", "86400");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

}

