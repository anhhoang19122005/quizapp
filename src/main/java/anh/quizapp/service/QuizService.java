package anh.quizapp.service;

import anh.quizapp.dto.request.CreateQuizRecord;
import anh.quizapp.dto.response.ApiResponse;
import anh.quizapp.dto.response.QuizResponse;
import anh.quizapp.mapper.QuestionMapper;
import anh.quizapp.mapper.QuizMapper;
import anh.quizapp.repository.QuestionRepository;
import anh.quizapp.repository.QuizRepository;
import anh.quizapp.entity.Question;
import anh.quizapp.entity.Quiz;
import anh.quizapp.dto.request.ResponseRecord;
import anh.quizapp.dto.request.QuestionRecord;
import anh.quizapp.exception.AppException;
import anh.quizapp.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizService {
    QuizMapper quizMapper;
    QuestionMapper questionMapper;
    QuestionRepository questionRepository;
    QuizRepository quizRepository;

    public QuizResponse createQuiz(CreateQuizRecord createQuizRecord) {
        Set<Question> questionList = questionRepository.getRandomQuestionsByCategory(createQuizRecord.numQuestions(),createQuizRecord.category());
        Quiz quiz = quizMapper.toQuiz(createQuizRecord);
        quiz.setQuestionList(questionList);
//        for (Question q : questionList) {
//            System.out.println(q);
//        }
            quizRepository.save(quiz);
            return quizMapper.toQuizResponse(quiz);
    }

    public Set<QuestionRecord> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorCode.NOT_FOUND_ID)
        );


        Set<Question> questionRecordList = quiz.getQuestionList();
        return questionRecordList.stream().map(questionMapper::toQuestionRecord).collect(Collectors.toSet());

    }

    public Integer calcQuizResult(Integer id, List<ResponseRecord> responses) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND_ID));
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
