package engine.logic;

import com.fasterxml.jackson.annotation.JsonView;
import engine.exception.QuizNotFound;
import engine.model.Answer;
import engine.model.Quiz;
import engine.model.QuizResponse;
import engine.model.Views;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/api")
public class QuizController {
    private final Map<Long, Quiz> quizzes = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @JsonView(Views.Internal.class)
    @PostMapping(value = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        quiz.setId(nextId.getAndIncrement());
        quizzes.put(quiz.getId(), quiz);

        return quiz;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        if (quizzes.containsKey(id)) {
            return quizzes.get(id);
        }

        throw new QuizNotFound();
    }

    @JsonView(Views.Public.class)
    @GetMapping("/quizzes")
    public Quiz[] getAllQuizzes() {
        return quizzes.values().toArray(Quiz[]::new);
    }

    @JsonView(Views.Internal.class)
    @PostMapping("/quizzes/{id}/solve")
    public QuizResponse respondToAnswer(@PathVariable long id, @RequestBody Answer answer) {
        if (!quizzes.containsKey(id)) {
            throw new QuizNotFound();
        }

        Quiz quiz = quizzes.get(id);

        if (Arrays.equals(answer.getAnswer(), quiz.getAnswer())) {
            return new QuizResponse(true, "Congratulations, you're right!");
        }

        return new QuizResponse(false, "Wrong answer! Please, try again.");
    }
}
