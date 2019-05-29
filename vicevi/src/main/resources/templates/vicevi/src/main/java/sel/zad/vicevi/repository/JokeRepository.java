package sel.zad.vicevi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sel.zad.vicevi.model.Joke;

import java.util.Optional;

public interface JokeRepository  extends JpaRepository<Joke,Integer> {
    Joke findTopByOrderByIdDesc();

    Optional<Joke> findById(int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Joke joke SET joke.likes=:likes WHERE joke.id=:jokeId")
    void updateJokeLikes(@Param("jokeId")int id, @Param("likes")int likes);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Joke joke SET joke.dislikes=:likes WHERE joke.id=:jokeId")
    void updateJokeDislikes(@Param("jokeId")int id, @Param("likes")int dislikes);
}
