package board.board_solo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String content;

    public Board() {

    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
