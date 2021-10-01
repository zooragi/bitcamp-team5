package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Report;

public abstract class AbstractBoardHandler implements Command {

  List<Report> boardList = new ArrayList<>();
  
  public AbstractBoardHandler (List<Report> boardList) {
    this.boardList = boardList;
  }
}
