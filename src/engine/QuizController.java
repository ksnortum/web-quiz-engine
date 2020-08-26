package engine;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class QuizController {
    private static final String TITLE = "The Java Logo";
    private static final String TEXT = "What is depicted on the Java logo?";
    private static final String[] OPTIONS = {
            "Robot",
            "Tea leaf",
            "Cup of coffee",
            "Bug"
    };

    @GetMapping("/quiz")
    public Quiz getQuiz() {
        return new Quiz(TITLE, TEXT, OPTIONS);
    }

//    @PostMapping("/quiz/{answer}")
//    public QuizResponse respondToAnswer(@PathVariable int answer) {
    @PostMapping("/quiz")
    public QuizResponse respondToAnswer(@RequestParam(value = "answer") int answer) {
        if (answer == 2) {
            return new QuizResponse(true, "Congratulations, you're right!");
        }

        return new QuizResponse(false, "Wrong answer! Please, try again.");
    }
}
