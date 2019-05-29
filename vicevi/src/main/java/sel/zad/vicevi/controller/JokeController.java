package sel.zad.vicevi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sel.zad.vicevi.model.Category;
import sel.zad.vicevi.model.FormJoke;
import sel.zad.vicevi.model.Joke;
import sel.zad.vicevi.service.CategoryService;
import sel.zad.vicevi.service.JokeService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class JokeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private JokeService jokeService;



    //controller for getting the page for adding a new joke
    @RequestMapping(value={"/new"},method = RequestMethod.GET )
    public String getForm(Model model){
        List<Category>categories=categoryService.listAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("jokeForm",new FormJoke());
        return "newJoke";
    }


    //controller for adding a new joke
    @RequestMapping(value={"/new"},method = RequestMethod.POST )
    public String addJoke(Model model ,@ModelAttribute FormJoke formJoke){
        String content=formJoke.getContent();
        int categoryId=formJoke.getCategoryId();
        if(content==null || categoryId == 0){
            model.addAttribute("message","There was a problem, one of the required fields came as null!");
        }else{
            Category category=categoryService.findCategoryById(categoryId);
            if(category !=null){
                Joke biggestId=jokeService.findWithBiggestId();
                jokeService.addJoke(new Joke(biggestId.getId()+1,content,0,0,category));
                model.addAttribute("message","You have successfully added a joke!");
            }else {
                model.addAttribute("message", "There was a problem while fetching the category you picked.");
            }
        }

        List<Category>categories=categoryService.listAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("jokeForm",new FormJoke());
        return "newJoke";
    }


    //controller for liking a joke
    @RequestMapping(value={"/like"},method = RequestMethod.POST )
    public String likeJoke(Model model , @RequestParam("id") String id,@RequestParam("page") String pageNum){
        if(id == null){
            model.addAttribute("message", "There is something wrong with your like request.");
        }else {
            int jokeId = Integer.parseInt(id);
            Joke joke=jokeService.findJokeById(jokeId);
            if(joke != null){
                int jokeLikes=joke.getLikes();
                jokeService.updateLikes(jokeId,jokeLikes+1);
            }else{
                model.addAttribute("message","You are trying to like a non existing joke!");
            }
        }
        List<Joke>jokes=jokeService.listAllJokes();
        Collections.sort(jokes);
        if(pageNum != null){
            int pageNumber=Integer.parseInt(pageNum);
            int size=jokes.size();
            if(pageNumber*10 >= size){
                model.addAttribute("message","You are trying to like a joke on non existing page!");
                model.addAttribute("page",0);
            }else{
                List<Joke>pageJokes=new ArrayList<>();
                //if its the first page
                if(pageNumber == 0){
                    if(jokes.size()<10){
                        pageJokes=jokes.subList(0,jokes.size());
                    }else{
                        pageJokes=jokes.subList(0,10);
                    }
                }else{
                    //for the last page
                    if((size-pageNumber*10)<10){
                        pageJokes=jokes.subList(pageNumber*10,size);
                    }else{
                        pageJokes=jokes.subList(pageNumber*10,pageNumber*10+10);
                    }
                }
                model.addAttribute("page",pageNumber);
                model.addAttribute("maxSize",jokes.size());
                model.addAttribute("jokes",pageJokes);
                return "home";
            }
        }else{
            model.addAttribute("message","There was a problem in a like request.");
            model.addAttribute("page",0);
        }
        if(jokes.size()<10){
            model.addAttribute("jokes",jokes.subList(0,jokes.size()));
        }else{
            model.addAttribute("jokes",jokes.subList(0,10));
        }
        model.addAttribute("maxSize",jokes.size());
        return "home";
    }


    //controller for disliking a joke
    @RequestMapping(value={"/dislike"},method = RequestMethod.POST )
    public String dislikeJoke(Model model , @RequestParam("id") String id,@RequestParam("page") String pageNum){
        if(id == null){
            model.addAttribute("message", "There is something wrong with your dislike request.");
        }else {
            int jokeId = Integer.parseInt(id);
            Joke joke=jokeService.findJokeById(jokeId);
            if(joke != null){
                int jokeDislikes=joke.getDislikes();
                jokeService.updateDislikes(jokeId,jokeDislikes+1);
            }else{
                model.addAttribute("message","You are trying to dislike a non existing joke!");
            }
        }
        List<Joke>jokes=jokeService.listAllJokes();
        Collections.sort(jokes);
        if(pageNum != null){
            int pageNumber=Integer.parseInt(pageNum);
            int size=jokes.size();
            if(pageNumber*10 >= size){
                model.addAttribute("message","You are trying to like a joke on non existing page!");
                model.addAttribute("page",0);
            }else{
                List<Joke>pageJokes=new ArrayList<>();
                //if its the first page
                if(pageNumber == 0){
                    if(jokes.size()<10){
                        pageJokes=jokes.subList(0,jokes.size());
                    }else{
                        pageJokes=jokes.subList(0,10);
                    }
                }else{
                    //for the last page
                    if((size-pageNumber*10)<10){
                        pageJokes=jokes.subList(pageNumber*10,size);
                    }else{
                        pageJokes=jokes.subList(pageNumber*10,pageNumber*10+10);
                    }
                }
                model.addAttribute("page",pageNumber);
                model.addAttribute("maxSize",jokes.size());
                model.addAttribute("jokes",pageJokes);
                return "home";
            }
        }else{
            model.addAttribute("message","There was a problem in a like request.");
            model.addAttribute("page",0);
        }

        if(jokes.size()<10){
            model.addAttribute("jokes",jokes.subList(0,jokes.size()));
        }else{
            model.addAttribute("jokes",jokes.subList(0,10));
        }
        model.addAttribute("maxSize",jokes.size());
        return "home";
    }



    //controller for paging
    @RequestMapping(value={"/jokes"},method = RequestMethod.GET )
    public String getJokesByPage(Model model , @RequestParam("page") String pageNum){
        List<Joke>jokes=jokeService.listAllJokes();
        Collections.sort(jokes);
        if(pageNum != null){
            int pageNumber=Integer.parseInt(pageNum);
            int size=jokes.size();
            if(pageNumber*10 >= size){
                model.addAttribute("message","You are trying to like a joke on non existing page!");
                model.addAttribute("page",0);
            }else{
                List<Joke>pageJokes=new ArrayList<>();
                //if its the first page
                if(pageNumber == 0){
                    pageJokes = jokes.subList(0, 10);
                }else{
                    //for the last page
                    if((size-pageNumber*10)<10){
                        pageJokes=jokes.subList(pageNumber*10,size);
                    }else{
                        pageJokes=jokes.subList(pageNumber*10,pageNumber*10+10);
                    }
                }
                model.addAttribute("page",pageNumber);
                model.addAttribute("maxSize",jokes.size());
                model.addAttribute("jokes",pageJokes);
                return "home";
            }
        }else{
            model.addAttribute("message","There was a problem in a like request.");
            model.addAttribute("page",0);
        }
        model.addAttribute("jokes",jokes.subList(0,10));
        model.addAttribute("maxSize",jokes.size());
        return "home";
    }





}
