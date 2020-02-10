package api.gateway.apigateway.filter;

import com.netflix.zuul.ZuulFilter;

public class SamplePostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("Using Post Filter");
        return null;
    }
}