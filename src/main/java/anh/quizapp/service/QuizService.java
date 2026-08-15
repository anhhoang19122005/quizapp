package anh.quizapp.service;

import anh.quizapp.dao.QuestionDAO;
import anh.quizapp.dao.QuizDAO;
import anh.quizapp.entity.Question;
import anh.quizapp.entity.Quiz;
import anh.quizapp.dto.Response;
import anh.quizapp.dto.QuestionRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class QuizService {
    @Autowired
    QuestionDAO questionDAO;

    @Autowired
    QuizDAO quizDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String quizName) {
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
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Set<QuestionRecord>> getQuizQuestions(Integer id) {
            Optional<Quiz> quiz = quizDAO.findById(id);
            Set<Question> questionRecordList = quiz.get().getQuestionList();
            Set<QuestionRecord> questionRecords = new HashSet<>();

            questionRecordList.forEach(q -> {
                QuestionRecord questionRecord = new QuestionRecord(q.getQuestionTitle(),q.getOption1(),
                q.getOption2(), q.getOption3(),q.getOption4());
                questionRecords.add(questionRecord);
            });

            return ResponseEntity.status(HttpStatus.OK).body(questionRecords);
    }

    public ResponseEntity<Integer> calcQuizResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        Set<Question> questions = quiz.getQuestionList();
        int right = 0;
        for (Response response : responses) {
            for (Question question : questions) {
                if (response.getId().equals(question.getId()) && response.getResponse().equals(question.getRightAnswer())) {
                    log.info("RIGHT_ANSWER + 1");

                    right++;
                } else {
                    log.info("IGNORED {},{}, {}, {}", response.getId(), response.getResponse(), question.getId(), question.getRightAnswer());
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(right);
    }
}
