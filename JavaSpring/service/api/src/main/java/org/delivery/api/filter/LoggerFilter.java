package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest)request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req,res);

        // req info

        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey->{
            var headerValue = req.getHeader(headerKey);

            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append(" ,").append("]");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var url = req.getRequestURL();
        var method = req.getMethod();

        log.info(">>>> url : {}, method : {}, header : {}, body : {}", url,method,headerValues,requestBody);

        // res info
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append(" ,").append("]");
        });
        var responseBody = new String(res.getContentAsByteArray());
        log.info("<<<< url : {}, method : {}, header : {}, body : {}", url,method,responseHeaderValues,responseBody);

        res.copyBodyToResponse();

    }
}
