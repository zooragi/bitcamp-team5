package com.welcomeToJeju.moj.servlet.user;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/user/add")
public class UserAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  UserDao userDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      User user = new User();

      user.setEmail(request.getParameter("email"));
      user.setNickname(request.getParameter("nickname"));
      user.setPassword(request.getParameter("password"));

      userDao.insert(user);
      sqlSession.commit();

      request.getRequestDispatcher("/user/UserAdd.jsp").forward(request, response);

    } catch (Exception e) {

      request.setAttribute("error", e);
      request.getRequestDispatcher("error").forward(request, response);

      //      throw new ServletException(e.getCause().toString().contains("UIX_email") ? 
      //          "이메일 중복!" : "닉네임 중복!");
      //      out.println(
      //          e.getCause().toString().contains("UIX_email") ? 
      //              "이메일 중복!" : "닉네임 중복!");
    }
  }


}
