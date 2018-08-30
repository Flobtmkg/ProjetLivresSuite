package webAppProjetLivre.filters;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import webAppProjetLivre.classesTravail.RequestManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements Filter {

    private RequestManager myRequests;
    @Override
    public void init(FilterConfig cfg) {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(cfg.getServletContext());
        this.myRequests = ctx.getBean(RequestManager.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request=myRequests.addLocationParameter((HttpServletRequest) request);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

    public RequestManager getMyRequests() {
        return myRequests;
    }

    public void setMyRequests(RequestManager myRequests) {
        this.myRequests = myRequests;
    }
}
