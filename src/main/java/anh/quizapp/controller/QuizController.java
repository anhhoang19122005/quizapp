package anh.quizapp.controller;

import anh.quizapp.dto.request.CreateQuizRecord;
import anh.quizapp.dto.request.ResponseRecord;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.dto.response.QuizResponse;
import anh.quizapp.service.QuizService;
import anh.quizapp.dto.request.QuestionRecord;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("api/quizs")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ApiResponse<QuizResponse> createQuiz(@RequestBody CreateQuizRecord createQuizRecord) {
        QuizResponse result = quizService.createQuiz(createQuizRecord);
        return ApiResponse.<QuizResponse>builder()
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
