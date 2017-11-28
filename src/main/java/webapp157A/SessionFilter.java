package webapp157A;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionFilter implements Filter {


    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse hsr = (HttpServletResponse) res;
        hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        hsr.setHeader("Pragma", "no-cache");
        hsr.setDateHeader("Expires", 0);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig arg0) throws ServletException {}

}
