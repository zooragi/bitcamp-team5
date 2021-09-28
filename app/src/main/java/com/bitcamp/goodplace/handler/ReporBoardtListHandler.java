package com.bitcamp.goodplace.handler;

import java.util.List;
import com.bitcamp.goodplace.domain.Board;

public class ReporBoardtListHandler extends AbstractBoardHandler {

  public ReporBoardtListHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[문의사항 목록]");
    for(Board board : boardList) {
      System.out.printf("%d, %s, %s, %s, %s \n", 
          board.getNo(), board.getTitle(), board.getContent(), board.getWriter().getNickName()
          , board.getRegisteredDate());
    }

  }

}
