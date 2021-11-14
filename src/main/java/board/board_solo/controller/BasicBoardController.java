package board.board_solo.controller;

import board.board_solo.model.Board;
import board.board_solo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor
public class BasicBoardController {

    private final BoardRepository boardRepository;

    @GetMapping // 출력
    public String boards(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "basic/boards";
    }


    @GetMapping("{boardId}") // 각각의 게시물 출력
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "basic/board";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    @PostMapping("/add")
    public String addBoard(Board board, RedirectAttributes redirectAttributes) {
        Board savedBoard = boardRepository.save(board);
        redirectAttributes.addAttribute("boardId", savedBoard.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/boards/{boardId}";
    }


    // 상품 수정
    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "basic/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute Board board) {
        boardRepository.update(boardId, board);
        return "redirect:/basic/boards/{boardId}";
    }

    //Test용 아이템 추가
    @PostConstruct
    public void init() {
        boardRepository.save(new Board("제목1", "내용1"));
        boardRepository.save(new Board("제목2", "내용2"));

    }
}
