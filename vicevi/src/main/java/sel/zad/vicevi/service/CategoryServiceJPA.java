package sel.zad.vicevi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sel.zad.vicevi.model.Category;
import sel.zad.vicevi.model.Joke;
import sel.zad.vicevi.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceJPA implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(int id) {
        Optional<Category>category=categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }
    }

}
