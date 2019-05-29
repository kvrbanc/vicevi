package sel.zad.vicevi.service;

import sel.zad.vicevi.model.Joke;

import java.util.List;

public interface JokeService {
    void addJoke(Joke joke);
    Joke findWithBiggestId();
    List<Joke> listAllJokes();
    Joke findJokeById(int id);
    void updateLikes(int jokeId,int likes);
    void updateDislikes(int jokeId,int dislikes);
}
