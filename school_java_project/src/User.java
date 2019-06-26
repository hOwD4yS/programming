import javax.swing.*;
import java.awt.*;

public class User {
    private String name;
    private int hp;
    private int originalhp;

    private Pokemon pokemon;
    private Pokeshow usershow;
    private JLabel hps;

    private User enemy;

    private int UNDERFLOW_ATTACK_POINT_SIGNAL = -9999;

    User()
    {
        hp = 100;
        originalhp = 100;
    }

    User(int hp)
    {
        this.hp = hp;
        this.originalhp = hp;
    }

    public void SetEnemy(User enemy){ this.enemy = enemy; }
    public User GetEnemy(){ return enemy; }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void SetPokemon(Pokemon pokemon)
    {
        this.pokemon = pokemon;
    }
    public Pokemon GetPokemon()
    {
        return pokemon;
    }

    public void Usershow(Point p)
    {
        usershow =  new Pokeshow("User : "  + name);

        hps = new JLabel(hp + " / " + originalhp);
        usershow.add(hps);

        usershow.setSize(300,50);
        usershow.setLocation(p);
        usershow.setLayout(new FlowLayout());

        usershow.setVisible(true);

    }

    public void attacked(int power)
    {
        if(power  == UNDERFLOW_ATTACK_POINT_SIGNAL)
        {
            return;
        }

        hp -= power;

        hps.setText(hp + " / " + originalhp);

        if(hp <= 0)
        {
            usershow.dispose();
            pokemon.game_over();
            enemy.usershow.dispose();
            enemy.pokemon.game_over();
            System.out.println(enemy.GetName() + " defeat " + name + " !!");
        }

    }

}
