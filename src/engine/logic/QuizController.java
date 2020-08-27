package engine.logic;

import engine.exception.QuizNotFound;
import engine.model.Quiz;
import engine.model.QuizResponse;
import engine.model.QuizWithAnswer;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/api")
public class QuizController {
    private final Map<Long, QuizWithAnswer> quizzes = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @PostMapping(value = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@RequestBody QuizWithAnswer quiz) {
        quiz.setId(nextId.getAndIncrement());
        quizzes.put(quiz.getId(), quiz);

        return quiz;
    }

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        if (quizzes.containsKey(id)) {
            return new Quiz(quizzes.get(id));
        }

        throw new QuizNotFound();
    }

    @GetMapping("/quizzes")
    public Quiz[] getAllQuizzes() {
        return quizzes.values().toArray(Quiz[]::new);
    }

    @PostMapping("/quizzes/{id}/solve")
    public QuizResponse respondToAnswer(@PathVariable long id, @RequestParam(value = "answer") int answer) {
        if (!quizzes.containsKey(id)) {
            throw new QuizNotFound();
        }

        QuizWithAnswer quiz = quizzes.get(id);

        if (answer == quiz.getAnswer()) {
            return new QuizResponse(true, "Congratulations, you're right!");
        }

        return new QuizResponse(false, "Wrong answer! Please, try again.");
    }
}
