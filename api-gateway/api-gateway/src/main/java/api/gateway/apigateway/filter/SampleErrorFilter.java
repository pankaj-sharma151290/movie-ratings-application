package api.gateway.apigateway.filter;

import com.netflix.zuul.ZuulFilter;

public class SampleErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("Using Error Filter");
        return null;
    }

}