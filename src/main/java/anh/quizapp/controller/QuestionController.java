package anh.quizapp.controller;

import anh.quizapp.dto.request.CreateQuestionRecord;
import anh.quizapp.dto.request.QuestionRecord;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.service.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
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
    public ApiResponse<QuestionRecord> addQuestion(@RequestBody CreateQuestionRecord record) {
        return ApiResponse.<QuestionRecord>builder()
                .result(service.addQuestion(record))
                .build();

    }
}
