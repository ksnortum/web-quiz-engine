package engine.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import engine.exception.QuizNotFoundException;
import engine.model.*;
import engine.persistence.AccountRepository;
import engine.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RestController()
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private AccountRepository accountRepository;

    @JsonIgnore
    @PostMapping(value = "/register", consumes = "application/json")
    public Account registerAccount(@Valid @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @JsonIgnore
    @PostMapping(value = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);
    }

    @GetMapping("/quizzes")
    public Quiz[] getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        Quiz[] quizArray = new Quiz[quizzes.size()];
        IntStream.range(0, quizzes.size()).forEach(i -> quizArray[i] = quizzes.get(i));

        return quizArray;
    }

    @JsonIgnore
    @PostMapping("/quizzes/{id}/solve")
    public QuizResponse respondToAnswer(@PathVariable long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);

        if (quiz.getAnswer() == null) {
            quiz.setAnswer(new int[0]);
        }

        if (answer.getAnswer() == null) {
            answer.setAnswer(new int[0]);
        }

        if (Arrays.equals(answer.getAnswer(), quiz.getAnswer())) {
            return new QuizResponse(true, "Congratulations, you're right!");
        }

        return new QuizResponse(false, "Wrong answer! Please, try again.");
    }

    @JsonIgnore
    @DeleteMapping("/quizzes/{id}")
    public void deleteAQuiz(@PathVariable long id, HttpServletResponse response) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            throw new QuizNotFoundException();
        }
    }
}
