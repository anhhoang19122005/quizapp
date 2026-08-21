package anh.quizapp.controller;

import anh.quizapp.dto.request.CreateQuizRequest;
import anh.quizapp.dto.response.QuestionResponse;
import anh.quizapp.dto.response.ResponseRecord;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.dto.response.QuizResponse;
import anh.quizapp.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("api/quizs")
public class QuizController {
    QuizService quizService;

    @PostMapping("create")
    public ApiResponse<QuizResponse> createQuiz(@RequestBody CreateQuizRequest createQuizRequest) {
        QuizResponse result = quizService.createQuiz(createQuizRequest);
        return ApiResponse.<QuizResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Set<QuestionResponse>> getQuizQuestions(@PathVariable Integer id) {
        Set<QuestionResponse> records = quizService.getQuizQuestions(id);
        return ApiResponse.<Set<QuestionResponse>>builder()
                .result(records)
                .build();
    }

    @PostMapping("/submit/{id}")
    public ApiResponse<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<ResponseRecord> responses) {
        return ApiResponse.<Integer>builder().result(quizService.calcQuizResult(id,responses)).build();
    }
}
