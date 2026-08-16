package anh.quizapp.dao;

import anh.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    public List<Question> getQuestionsByCategory(String category);

    @Query("SELECT q FROM Question q where q.category =:category ORDER BY RANDOM() limit :numQ")
    public Set<Question> getRandomQuestionsByCategory(@Param("numQ") int numQ,@Param("category") String category);
}
