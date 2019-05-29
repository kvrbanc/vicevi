package sel.zad.vicevi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Joke implements Comparable<Joke>{
    @Id
    private int id;

    @NotNull
    private String content;

    private int likes;

    private int dislikes;

    @ManyToOne
    private Category category;

    public Joke() {
    }

    public Joke(int id,@NotNull String content, int likes, int dislikes,Category category) {
        this.id=id;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int compareTo(Joke o) {
        int rating1=this.likes-this.dislikes;
        int rating2=o.likes-o.dislikes;
        return rating2 - rating1;
    }
}
