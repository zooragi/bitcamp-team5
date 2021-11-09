package com.welcomeToJeju.moj.servlet.place;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Place;

@WebServlet("/place/detail")
public class PlaceDetailController extends GenericServlet {
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
      String id = request.getParameter("id");   //id? no?
      Place place = placeDao.findByPlaceId(id);

      request.setAttribute("place", place);
      request.getRequestDispatcher("/place/PlaceDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }


}
