package com.bitcamp.goodplace.handler;

import java.sql.Date;
import java.util.List;
import com.bitcamp.goodplace.domain.Board;
import com.bitcamp.goodplace.domain.Theme;
import com.bitcamp.goodplace.domain.User;
import com.bitcamp.util.Prompt;

public class ReportMyMapHandler extends AbstractMyMapHandler {

  List<Board> boardList;
  public ReportMyMapHandler(List<User> userList, List<Board> boardList) {
    super(userList);
    this.boardList = boardList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 신고]");
    Board board = new Board();
    if(boardList.size()==0) {
      board.setNo(1);
    } else { board.setNo((boardList.get(boardList.size()-1).getNo()) +1 );
    }

    board.setTitle("테마 신고");

    int index = 1;
    for(User user : userList) {
      for(Theme theme : user.getThemeList()) {
        if(theme.isPublic()) {
          System.out.printf("%d. %s\n", index++ ,theme.getTitle());
        }
      }
    }
    String input = Prompt.inputString("신고할 테마의 이름을 입력해주세요");
    System.out.println();

    for( User user : userList) {
      for(Theme theme : user.getThemeList()) {
        if(theme.getTitle().equals(input)) {
          theme.setWarning(theme.getWarning()+1);          
        }
      }
    }

    String content = Prompt.inputString("신고 사유를 기입해주세요");
    board.setContent(content);
    board.setRegisteredDate(new Date(System.currentTimeMillis()));
    board.setWriter(AuthLoginHandler.getLoginUser());
    boardList.add(board);
    System.out.println("테마 신고가 완료되었습니다.");

  }

}
