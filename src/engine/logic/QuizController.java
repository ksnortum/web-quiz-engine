package engine.logic;

import engine.exception.QuizNotFoundException;
import engine.model.*;
import engine.service.AccountService;
import engine.service.CompletionService;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RestController()
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CompletionService completionService;

    @PostMapping(value = "/register", consumes = "application/json")
    public Account registerAccount(@Valid @RequestBody Account account, HttpServletResponse response) {
        if (accountService.existsByEmail(account.getEmail())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            return new Account();
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(account.getPassword());
        account.setPassword(encodedPassword);

        return accountService.save(account);
    }

    @PostMapping(value = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        String creator = SecurityContextHolder.getContext().getAuthentication().getName();
        quiz.setCreator(creator);

        return quizService.save(quiz);
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return quizService.findById(id).orElseThrow(QuizNotFoundException::new);
    }

    @GetMapping("/quizzes")
    public Quiz[] getAllQuizzes() {
        List<Quiz> quizzes = quizService.findAll();
        Quiz[] quizArray = new Quiz[quizzes.size()];
        IntStream.range(0, quizzes.size()).forEach(i -> quizArray[i] = quizzes.get(i));

        return quizArray;
    }

    @PostMapping("/quizzes/{id}/solve")
    public QuizResponse respondToAnswer(@PathVariable long id, @RequestBody Answer answer) {
        Quiz quiz = quizService.findById(id).orElseThrow(QuizNotFoundException::new);

        if (quiz.getAnswer() == null) {
            quiz.setAnswer(new int[0]);
        }

        if (answer.getAnswer() == null) {
            answer.setAnswer(new int[0]);
        }

        if (Arrays.equals(answer.getAnswer(), quiz.getAnswer())) {
            // Update Completion
            Completion completion = new Completion();
            completion.setId(quiz.getId());
            completion.setAccountId(quiz.getCreator());
            completion.setCompletedAt(LocalDateTime.now());
            completionService.save(completion);

            return new QuizResponse(true, "Congratulations, you're right!");
        }

        return new QuizResponse(false, "Wrong answer! Please, try again.");
    }

    @DeleteMapping("/quizzes/{id}")
    public void deleteAQuiz(@PathVariable long id, HttpServletResponse response) {
        Optional<Quiz> optionalQuiz = quizService.findById(id);

        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            String authorizedName = SecurityContextHolder.getContext().getAuthentication().getName();

            if (!quiz.getCreator().equals(authorizedName)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                quizService.deleteById(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } else {
            throw new QuizNotFoundException();
        }
    }

    @GetMapping("quizzes/completed")
    public List<Completion> findAllCompletionsByAccount() {
        String authorizedName = SecurityContextHolder.getContext().getAuthentication().getName();

        return completionService.findAllCompletionsByAccount(authorizedName);
    }
}
