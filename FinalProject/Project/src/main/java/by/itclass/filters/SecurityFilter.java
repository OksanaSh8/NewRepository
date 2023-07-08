package by.itclass.filters;

import by.itclass.constants.AppConstant;
import by.itclass.model.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", value = {AppConstant.ADD_NEWS_JSP, AppConstant.MY_NEWS_JSP},
dispatcherTypes = DispatcherType.REQUEST)
public class SecurityFilter implements Filter {
    public void destroy() {
        //Вызывается единожды для фильтра в конце его жизненного цикла
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //Вызывается на каждый клиентский запрос к ресурсу
        // на который настроен этот фильтр
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpSession session=request.getSession();

        User user=(User) session.getAttribute(AppConstant.USER_ATTR);
        if (user!=null){
            //Перех. к след.фильтру в цепочке, если такой есть
            // либо уже передают запрос в заправив. ресурс.
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath()+AppConstant.AUTH_JSP);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        //Вызывается единожды для фильтра в начале его жизненного цикла
    }

}
