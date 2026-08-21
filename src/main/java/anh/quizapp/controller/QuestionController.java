package anh.quizapp.controller;

import anh.quizapp.dto.request.CreateQuestionRequest;
import anh.quizapp.dto.response.QuestionResponse;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.service.QuestionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
    public ApiResponse<List<QuestionResponse>> getAllQuestion() {
        List<QuestionResponse> questions =  service.getAllQuestion();

        return ApiResponse.<List<QuestionResponse>>builder()
                .result(questions)
                .build();
    }

    @GetMapping("/category/{category}")
    public ApiResponse<List<QuestionResponse>> getQuestionsByCategory(@PathVariable String category) {
        List<QuestionResponse> questions = service.getQuestionsByCategory(category);
        return ApiResponse.<List<QuestionResponse>>builder()
                .result(questions)
                .build();
    }

    @PostMapping("add")
    public ApiResponse<QuestionResponse> addQuestion(@RequestBody CreateQuestionRequest record) {
        return ApiResponse.<QuestionResponse>builder()
                .result(service.addQuestion(record))
                .build();

    }
}
