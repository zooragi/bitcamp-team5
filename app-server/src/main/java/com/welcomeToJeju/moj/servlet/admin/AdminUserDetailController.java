package com.welcomeToJeju.moj.servlet.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/admin/detail")
public class AdminUserDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int no = Integer.parseInt(request.getParameter("no"));
      User user = userDao.findByNo(no);

      request.setAttribute("user", user);
      request.getRequestDispatcher("/admin/AdminUserDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
