package anh.quizapp.service;

import anh.quizapp.dao.QuestionDAO;
import anh.quizapp.dto.CreateQuestionRecord;
import anh.quizapp.dto.QuestionRecord;
import anh.quizapp.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    public List<QuestionRecord> getAllQuestion() {
        List<QuestionRecord> records = new ArrayList<>();
        try {
            questionDAO.findAll().forEach(q -> {
                QuestionRecord questionRecord = new QuestionRecord(q.getId()
                        , q.getQuestionTitle(),q.getOption1(),q.getOption2()
                        ,q.getOption3(),q.getOption4(),q.getDifficultyLevel(),q.getCategory()
                );
                records.add(questionRecord);
            });
            return records;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }


    public List<QuestionRecord> getQuestionsByCategory(String category) {
        List<QuestionRecord> records = new ArrayList<>();
        questionDAO.findAll().forEach(q -> {
            QuestionRecord questionRecord = new QuestionRecord(q.getId()
            , q.getQuestionTitle(),q.getOption1(),q.getOption2()
            ,q.getOption3(),q.getOption4(),q.getDifficultyLevel(),q.getCategory()
            );
            records.add(questionRecord);
        });
        return records;
    }

    public String addQuestion(CreateQuestionRecord record) {
        Question question = Question.builder()
                .questionTitle(record.questionTitle())
                .option1(record.option1())
                .option2(record.option2())
                .option3(record.option3())
                .option4(record.option4())
                .difficultyLevel(record.difficultyLevel())
                .category(record.category())
                .build();
        try {
            questionDAO.save(question);
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "failed";
    }
}
