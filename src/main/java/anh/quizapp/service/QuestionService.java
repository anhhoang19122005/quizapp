package anh.quizapp.service;

import anh.quizapp.mapper.QuestionMapper;
import anh.quizapp.repository.QuestionRepository;
import anh.quizapp.dto.request.CreateQuestionRequest;
import anh.quizapp.dto.response.QuestionResponse;
import anh.quizapp.entity.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionService {
    QuestionRepository questionRepository;
    QuestionMapper questionMapper;
    public List<QuestionResponse> getAllQuestion() {
        return questionRepository.findAll().stream().map(questionMapper::toQuestionRecord).toList();
    }


    public List<QuestionResponse> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category).stream().filter(question -> question.getCategory().equals(category))
                .map(questionMapper::toQuestionRecord).toList();
    }

    public QuestionResponse addQuestion(CreateQuestionRequest record) {
        Question question = questionMapper.toQuestion(record);
        questionRepository.save(question);
        return questionMapper.toQuestionRecord(question);
    }
}
