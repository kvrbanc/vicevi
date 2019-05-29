package sel.zad.vicevi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sel.zad.vicevi.model.Joke;
import sel.zad.vicevi.repository.JokeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JokeServiceJPA implements JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    @Override
    public void addJoke(Joke joke) {
        jokeRepository.save(joke);
    }

    @Override
    public Joke findWithBiggestId() {
        return jokeRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Joke> listAllJokes() {
        return jokeRepository.findAll();
    }

    @Override
    public Joke findJokeById(int id) {
        Optional<Joke> joke=jokeRepository.findById(id);
        if(joke.isPresent()){
            return joke.get();
        }else{
            return null;
        }
    }

    @Override
    public void updateLikes(int jokeId, int likes) {
        jokeRepository.updateJokeLikes(jokeId,likes);
    }

    @Override
    public void updateDislikes(int jokeId, int dislikes) {
        jokeRepository.updateJokeDislikes(jokeId, dislikes);
    }
}
