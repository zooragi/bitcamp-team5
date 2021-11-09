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
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;

@WebServlet("/mytheme/add")
public class MyThemeAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  ThemeDao themeDao;
  UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //    Theme theme = new Theme();
    //
    //    theme.setTitle(request.getParameter("title"));
    //    try {
    //      Category category = themeDao.findCategoryByNo(
    //          Integer.valueOf(request.getParameter("category")));
    //      // 카테고리
    //      theme.setCategory(category);
    //
    //      // 해시태그
    //      theme.getHashtags().add(request.getParameter("hashtag"));
    //      int isPublic = Integer.valueOf(request.getParameter("isPublic"));
    //      theme.setIsPublic(isPublic);
    //
    //      User user = userDao.findByNo(Integer.valueOf(request.getParameter("no")));
    //      theme.setOwner(user);
    //
    //      themeDao.insert(theme);
    //      for (String hashtag : theme.getHashtags()) {
    //        themeDao.insertHashtag(theme.getNo(), hashtag);
    //      }
    //      sqlSession.commit();
    //      response.setHeader("Refresh", "1;url=list");
    request.getRequestDispatcher("/theme/myTheme/MyThemeAdd.jsp").forward(request, response);

    //    } catch (Exception e) {
    //      request.setAttribute("error", e);
    //
    //      // 오류가 발생하면 오류내용을 출력할 뷰를 호출한다.
    //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    //
    //    }
  }


}
