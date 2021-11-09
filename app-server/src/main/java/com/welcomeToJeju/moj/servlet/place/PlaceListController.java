package com.welcomeToJeju.moj.servlet.place;

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
import com.welcomeToJeju.moj.domain.Place;

@WebServlet("/place/list")
public class PlaceListController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  PlaceDao placeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      //      Theme theme = (Theme) request.getAttribute("theme");
      int no = Integer.parseInt(request.getParameter("no"));
      Collection<Place> placeList = placeDao.findAllByThemeNo(no);

      request.setAttribute("placeList", placeList);
      request.getRequestDispatcher("/place/PlaceList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
