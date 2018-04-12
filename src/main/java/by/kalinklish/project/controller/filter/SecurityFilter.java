package by.kalinklish.project.controller.filter;

import by.kalinklish.project.constant.JspPageConstants;
import by.kalinklish.project.constant.ParameterConstants;
import by.kalinklish.project.constant.RoleType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "START_PATH", value = JspPageConstants.INDEX_PAGE)})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String type = (String) session.getAttribute(ParameterConstants.PARAM_ROLE);

        if (type == null) {
            session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_GUEST);
            RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageConstants.INDEX_PAGE);
            dispatcher.forward(request, response);
            return;
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
