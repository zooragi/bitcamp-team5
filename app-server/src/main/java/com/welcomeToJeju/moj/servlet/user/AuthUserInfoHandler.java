package com.welcomeToJeju.moj.servlet.user;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.welcomeToJeju.moj.dao.UserDao;

@WebServlet("/auth/userinfo")
public class AuthUserInfoHandler extends HttpServlet {

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

      // User user = (User) request.getSession(true).getAttribute("loginUser");
      //      User user = userDao.findByNo(no);

      request.getRequestDispatcher("/user/AuthUserInfo.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


  //  UserDao userDao;
  //
  //  public AuthUserInfoHandler(UserDao userDao) {
  //    this.userDao = userDao;
  //  }
  //
  //  @Override
  //  public void execute(CommandRequest request) throws Exception {
  //    System.out.println("[내 정보]");
  //
  //    User user = userDao.findByNo(AuthLoginHandler.loginUser.getNo());
  //
  //    System.out.printf("이메일 > %s\n", user.getEmail());
  //    System.out.printf("닉네임 > %s\n", user.getNickname());
  //    System.out.printf("가입일 > %s\n", user.getRegisteredDate());
  //    System.out.printf("🚨 경고 > %s\n", user.getWarningCount());
  //
  //    request.setAttribute("loginUser", AuthLoginHandler.getLoginUser());
  //
  //    String input = Prompt.inputString("수정하기(U) / 탈퇴하기(D) / 취소(0) > ");
  //
  //    switch (input) {
  //      case "U" :
  //      case "u" :
  //        request.getRequestDispatcher("/user/update").forward(request);
  //        return;
  //
  //      case "D" :
  //      case "d" :
  //        request.getRequestDispatcher("/user/delete").forward(request);
  //        return;
  //
  //      case "0" :
  //        return;
  //
  //      default :
  //        System.out.println("올바르지 않은 명령!");
  //    }
  //  }


}
