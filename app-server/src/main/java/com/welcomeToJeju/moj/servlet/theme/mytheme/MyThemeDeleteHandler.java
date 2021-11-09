package com.welcomeToJeju.moj.servlet.theme.mytheme;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

@WebServlet("/mytheme/delete")
public class MyThemeDeleteHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  ThemeDao themeDao;
  PlaceDao placeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int no = Integer.valueOf(request.getParameter("no"));
      Theme theme = themeDao.findByNo(no);

      themeDao.deleteAllLikedThemeByThemeNo(theme.getNo());
      themeDao.deleteHashtag(theme.getNo());
      themeDao.deletePlaceUserTheme(theme.getNo());
      themeDao.delete(theme.getNo());
      sqlSession.commit();

      request.setAttribute("theme", theme);
      request.getRequestDispatcher("/theme/myTheme/ThemeDelete.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
