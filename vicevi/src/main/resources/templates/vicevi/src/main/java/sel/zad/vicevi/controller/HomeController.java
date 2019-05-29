package sel.zad.vicevi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sel.zad.vicevi.model.Joke;
import sel.zad.vicevi.service.JokeService;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private JokeService jokeService;


    //controller gor getting the homepage
    @RequestMapping(value={"/","/home","/index"})
    public String home(Model model){
        List<Joke>jokes=jokeService.listAllJokes();
        Collections.sort(jokes);
        if(jokes.size()<10){
            model.addAttribute("jokes",jokes.subList(0,jokes.size()));
        }else{
            model.addAttribute("jokes",jokes.subList(0,10));
        }
        model.addAttribute("maxSize",jokes.size());
        model.addAttribute("page",0);
        return "home";
    }
}
