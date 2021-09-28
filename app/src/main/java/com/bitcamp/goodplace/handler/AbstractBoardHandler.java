package com.bitcamp.goodplace.handler;

import java.util.ArrayList;
import java.util.List;
import com.bitcamp.goodplace.domain.Board;

public abstract class AbstractBoardHandler implements Command {

  List<Board> boardList = new ArrayList<>();

  public AbstractBoardHandler (List<Board> boardList) {
    this.boardList = boardList;
  }
}
