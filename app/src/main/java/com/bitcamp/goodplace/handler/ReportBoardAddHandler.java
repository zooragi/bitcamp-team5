package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;
import com.bitcamp.goodplace.domain.Board;
import com.bitcamp.util.Prompt;

public class ReportBoardAddHandler extends AbstractBoardHandler {

  public ReportBoardAddHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[문의 사항]");
    System.out.println();
    System.out.println("1. 테마 신고");
    System.out.println("2. 악성유저 신고");
    System.out.println("3. 자주 묻는 질문");
    System.out.println("4. 기타 문의사항 등록하기");
    int no = Prompt.inputInt("선택 > ");

    if(no == 1) {
      request.getRequestDispatcher("/report/theme").forword(request);
      return;
    } else if(no == 4) {
      Board board = new Board();

      if(boardList.size()==0) {
        board.setNo(1);
      } else { board.setNo(boardList.get(boardList.size()-1).getNo());
      }

      String title = Prompt.inputString("제목 > ");
      String content = Prompt.inputString("내용 > ");
      board.setTitle(title);
      board.setContent(content);
      board.setRegisteredDate(new Date(System.currentTimeMillis()));
      board.setWriter(AuthLoginHandler.getLoginUser());

      System.out.println("문의사항 등록이 완료되었습니다.");
      boardList.add(board);
      return;
    }
    System.out.println("유효한 번호를 입력하세요.");



  }

}
