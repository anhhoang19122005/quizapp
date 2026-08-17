package anh.quizapp.controller;

import anh.quizapp.dto.ResponseRecord;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.service.QuizService;
import anh.quizapp.dto.QuestionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String quizName) {
        String result = quizService.createQuiz(category,numQ,quizName);
        return ApiResponse.<String>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Set<QuestionRecord>> getQuizQuestions(@PathVariable Integer id) {
        Set<QuestionRecord> records = quizService.getQuizQuestions(id);
        return ApiResponse.<Set<QuestionRecord>>builder()
                .result(records)
                .build();
    }

    @PostMapping("/submit/{id}")
    public ApiResponse<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<ResponseRecord> responses) {
        return ApiResponse.<Integer>builder().result(quizService.calcQuizResult(id,responses)).build();
    }
}
