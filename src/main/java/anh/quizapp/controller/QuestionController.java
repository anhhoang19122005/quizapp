package anh.quizapp.controller;

import anh.quizapp.dto.CreateQuestionRecord;
import anh.quizapp.dto.QuestionRecord;
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
    public ResponseEntity<List<QuestionRecord>> getAllQuestion() {
        List<QuestionRecord> questions =  service.getAllQuestion();
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionRecord>> getQuestionsByCategory(@PathVariable String category) {
        List<QuestionRecord> questions = service.getQuestionsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody CreateQuestionRecord record) {
        try {
            String result = service.addQuestion(record);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");

    }
}
