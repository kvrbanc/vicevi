package sel.zad.vicevi.service;

import sel.zad.vicevi.model.Category;
import sel.zad.vicevi.model.Joke;

import java.util.List;

public interface CategoryService {
    List<Category> listAllCategories();
    Category findCategoryById(int id);
}
