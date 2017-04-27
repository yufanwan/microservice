package com.example;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 42278 on 2017/4/13.
 */
public class AccessLogFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();

        logger.info("REQUEST :: < "+request.getScheme()+" "+request.getLocalAddr());
        logger.info("REQUEST :: < "+request.getMethod()+" "+request.getRequestURI());
        logger.info("RESPONSE :: > HTTP:"+response.getStatus());
        return null;
    }
}
