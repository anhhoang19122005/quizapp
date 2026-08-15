package anh.quizapp.service;

import anh.quizapp.dao.QuestionDAO;
import anh.quizapp.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return ResponseEntity.ok(questionDAO.findAll());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDAO.getQuestionsByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "success";
    }
}
