package com.welcomeToJeju.moj.servlet.theme.mytheme;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/mytheme/detail")
public class MyThemeDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  ThemeDao themeDao;
  UserDao userDao;
  PlaceDao placeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Theme theme = themeDao.findByNo(no);
      Collection<Place> placeList = placeDao.findAllByThemeNo(no);

      request.setAttribute("theme", theme);
      request.setAttribute("placeList", placeList);

      request.getRequestDispatcher("/theme/myTheme/MyThemeDetail.jsp").forward(request, response);
      request.getRequestDispatcher("/place/PlaceList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
