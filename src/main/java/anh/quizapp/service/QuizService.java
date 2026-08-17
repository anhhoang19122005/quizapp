package anh.quizapp.service;

import anh.quizapp.dao.QuestionDAO;
import anh.quizapp.dao.QuizDAO;
import anh.quizapp.entity.Question;
import anh.quizapp.entity.Quiz;
import anh.quizapp.dto.ResponseRecord;
import anh.quizapp.dto.QuestionRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class QuizService {
    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    QuizDAO quizDAO;

    public String createQuiz(String category, int numQ, String quizName) {
        Set<Question> questionList = questionDAO.getRandomQuestionsByCategory(numQ,category);
        Quiz quiz = Quiz.builder()
                .quizName(quizName)
                .numQuestions(numQ)
                .questionList(questionList)
                .build();

        for (Question q : questionList) {
            System.out.println(q);
        }
        try {
            quizDAO.save(quiz);
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "failed";
    }

    public Set<QuestionRecord> getQuizQuestions(Integer id) {
            Quiz quiz = quizDAO.findById(id).orElseThrow(() ->
                new RuntimeException("Quiz Not Found")
            );
            Set<Question> questionRecordList = quiz.getQuestionList();
            Set<QuestionRecord> questionRecords = new HashSet<>();

            questionRecordList.forEach(q -> {
                QuestionRecord questionRecord = new QuestionRecord(q.getId(),q.getQuestionTitle(),q.getOption1(),
                q.getOption2(), q.getOption3(),q.getOption4(),q.getDifficultyLevel(),q.getCategory());
                questionRecords.add(questionRecord);
            });

            return questionRecords;
    }

    public Integer calcQuizResult(Integer id, List<ResponseRecord> responses) {
        Quiz quiz = quizDAO.findById(id).orElseThrow(() -> new RuntimeException("Quiz Not Found"));
        Set<Question> questions = quiz.getQuestionList();
        int right = 0;
        for (ResponseRecord response : responses) {
            for (Question question : questions) {
                if (response.getId().equals(question.getId()) && response.getResponse().equals(question.getRightAnswer())) {
                    log.info("RIGHT_ANSWER + 1");

                    right++;
                } else {
                    log.info("IGNORED {},{}, {}, {}", response.getId(), response.getResponse(), question.getId(), question.getRightAnswer());
                }
            }
        }

        return right;
    }
}
