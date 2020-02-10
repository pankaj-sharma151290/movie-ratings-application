package api.gateway.apigateway.filter;

import com.netflix.zuul.ZuulFilter;

public class SampleRouteFilter  extends ZuulFilter {

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        System.out.println("Using Route Filter");
        return null;
    }

}