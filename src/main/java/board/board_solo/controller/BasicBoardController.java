package board.board_solo.controller;

import board.board_solo.model.Board;
import board.board_solo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor
public class BasicBoardController {

    private final BoardRepository boardRepository;

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "basic/boards";
    }


    @GetMapping("{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "basic/board";
    }

}
