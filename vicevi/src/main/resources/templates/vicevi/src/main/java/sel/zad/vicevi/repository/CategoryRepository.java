package sel.zad.vicevi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sel.zad.vicevi.model.Category;
import sel.zad.vicevi.model.Joke;

import java.awt.font.OpenType;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Optional<Category> findById(int id);

}
