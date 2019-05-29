package sel.zad.vicevi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Category {

    @Id
    private int id;

    @NotNull
    private String name;


    public Category() {
    }

    public Category(@NotNull String name, Set<Joke> jokes) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
