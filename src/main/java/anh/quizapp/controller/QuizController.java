package anh.quizapp.controller;

import anh.quizapp.dto.Response;
import anh.quizapp.service.QuizService;
import anh.quizapp.dto.QuestionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/quizs")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String quizName) {
        return quizService.createQuiz(category,numQ,quizName);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Set<QuestionRecord>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses) {
        return quizService.calcQuizResult(id,responses);
    }
}
