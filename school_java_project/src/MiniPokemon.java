import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MiniPokemon {
    static Pokemon pokemons[] = new Pokemon[8];

    private static void init_pokemons()
    {
        pokemons[0] =  new Pokemon("피카츄",new Quick_Attack(10,3,10),new Bite(30,3,8),new Double_Edge(70,4,10),new Rock_Smash(10,30,4),"./pikachu.png");
        pokemons[1] =  new Pokemon("라이츄",new Quick_Attack(10,3,10),new Dragon_Pulse(30,3,8),new Double_Edge(70,4,10),new Rock_Smash(10,30,4),"./raichu.png");
        pokemons[2] =  new Pokemon("파이리",new Quick_Attack(10,3,10),new Bite(30,3,8),new Earthquake(70,4,10),new Teleport(10,30,4),"./pairi.png");
        pokemons[3] =  new Pokemon("꼬부기",new Quick_Attack(10,3,10),new Bite(30,3,8),new Double_Edge(70,4,10),new Rock_Smash(10,30,4),"./gobugi.png");
        pokemons[4] =  new Pokemon("야도란",new Teleport(10,3,10),new Bite(30,3,8),new Sand_Attack(70,4,10),new Rock_Smash(10,30,4),"./yadoran.png");
        pokemons[5] =  new Pokemon("피존투",new Quick_Attack(10,3,10),new Bite(30,3,8),new Earthquake(70,4,10),new Rock_Smash(10,30,4),"./pizontu.png");
        pokemons[6] =  new Pokemon("또가스",new Earthquake(10,3,10),new Bite(30,3,8),new Double_Edge(70,4,10),new Sand_Attack(10,30,4),"./ttogasu.png");
        pokemons[7] =  new Pokemon("단데기",new Sand_Attack(10,3,10),new Bite(30,3,8),new Double_Edge(70,4,10),new Rock_Smash(10,30,4),"./dandegi.png");

    }

    private static void select_pokemon(User p , User enemy)
    {
        p.SetEnemy(enemy);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        WindowFrame win  = new WindowFrame(frame,"User : "  + p.GetName());

        JPanel panel = new JPanel();

        for(Pokemon poke : pokemons)
        {
            JButton tmp = new JButton(poke.GetName());
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    poke.SetEnemy(enemy);
                    p.SetPokemon(poke);
                    frame.dispose();
                    win.dispose();

                }
            };
            tmp.addActionListener(listener);
            panel.add(tmp);

        }

        win.add(panel);
        win.setSize(300,300);
        win.setLocation(300,400);
        win.pack();

        frame.setVisible(true);
        win.setVisible(true);


    }

    private static void init_user(User p)
    {
        Scanner s = new Scanner(System.in);
        p.SetName(s.nextLine());
    }

    private static void start_game(User p1 , User p2)
    {
        //포켓몬 이미지 show
        p1.GetPokemon().init_pokemon_show(new Point(100,200),p1.GetName());
        p2.GetPokemon().init_pokemon_show(new Point(900,200),p2.GetName());
        p1.Usershow(new Point(100,130));
        p2.Usershow(new Point(900,130));
    }

    public static void main(String[] args)
    {

        init_pokemons();

        User p1 = new User(1000);
        User p2 = new User(1000);

        System.out.print("player 1 이름 입력 : ");
        init_user(p1);

        System.out.print("player 2 이름 입력 : ");
        init_user(p2);

        select_pokemon(p1,p2);
        select_pokemon(p2,p1);

        System.out.println(p1.GetName() + " : " + p1.GetPokemon().GetName());
        System.out.println(p2.GetName() + " : " + p2.GetPokemon().GetName());

        System.out.println(p1.GetPokemon().GetEnemy().GetName());
        start_game(p1,p2);
    }
}
