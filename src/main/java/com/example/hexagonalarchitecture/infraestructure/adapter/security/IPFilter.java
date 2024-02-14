package com.example.hexagonalarchitecture.infraestructure.adapter.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class IPFilter implements Filter {
    private Map<String, Integer> ipRequests = new HashMap<>();

    @Value("${api.limit.max-requests}")
    private int maxRequests;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String clientIP = httpRequest.getRemoteAddr();

        if (exceedsMaxRequests(clientIP)) {
            throw new ServletException("Se ha excedido el límite de peticiones");
        }

        chain.doFilter(request, response);
    }

    private boolean exceedsMaxRequests(String clientIP) {
        int requests = ipRequests.getOrDefault(clientIP, 0);
        if (requests >= maxRequests) {
            return true;
        }
        ipRequests.put(clientIP, requests + 1);
        return false;
    }

}
