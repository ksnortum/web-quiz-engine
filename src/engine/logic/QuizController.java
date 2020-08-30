package engine.logic;

import com.fasterxml.jackson.annotation.JsonView;
import engine.exception.QuizNotFound;
import engine.model.Answer;
import engine.model.Quiz;
import engine.model.QuizResponse;
import engine.model.Views;
import engine.persistence.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RestController()
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @JsonView(Views.Internal.class)
    @PostMapping(value = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/quizzes/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return quizRepository.findById(id).orElseThrow(QuizNotFound::new);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/quizzes")
    public Quiz[] getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        Quiz[] quizArray = new Quiz[quizzes.size()];
        IntStream.range(0, quizzes.size()).forEach(i -> quizArray[i] = quizzes.get(i));

        return quizArray;
    }

    @JsonView(Views.Internal.class)
    @PostMapping("/quizzes/{id}/solve")
    public QuizResponse respondToAnswer(@PathVariable long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFound::new);

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
}
