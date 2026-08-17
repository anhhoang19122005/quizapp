package anh.quizapp.controller;

import anh.quizapp.dto.CreateQuestionRecord;
import anh.quizapp.dto.QuestionRecord;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.entity.Question;
import anh.quizapp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    QuestionService service;

    @GetMapping("/allQuestions")
    public ApiResponse<List<QuestionRecord>> getAllQuestion() {
        List<QuestionRecord> questions =  service.getAllQuestion();

        return ApiResponse.<List<QuestionRecord>>builder()
                .result(questions)
                .build();
    }

    @GetMapping("/category/{category}")
    public ApiResponse<List<QuestionRecord>> getQuestionsByCategory(@PathVariable String category) {
        List<QuestionRecord> questions = service.getQuestionsByCategory(category);
        return ApiResponse.<List<QuestionRecord>>builder()
                .result(questions)
                .build();
    }

    @PostMapping("add")
    public ApiResponse<String> addQuestion(@RequestBody CreateQuestionRecord record) {
        return ApiResponse.<String>builder()
                .result(service.addQuestion(record))
                .build();

    }
}
