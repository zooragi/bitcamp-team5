package com.welcomeToJeju.moj.servlet.theme.mytheme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/mytheme/list")
public class MyThemeListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  HttpSession httpSession;
  ThemeDao themeDao;
  UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //    httpSession = request.getSession(true);
    //    User loginUser = (User) httpSession.getAttribute("loginUser");
    //    System.out.println(loginUser);

    int no = Integer.parseInt(request.getParameter("no"));

    try {

      Collection<Theme> themeList = themeDao.findByUserNo(no);

      request.setAttribute("myThemeList", themeList);
      request.getRequestDispatcher("/theme/myTheme/MyThemeList.jsp").forward(request, response);

    } catch (Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
