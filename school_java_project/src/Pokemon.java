import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


class Pokemon {
    private String name;
    private SkillSet skills[] = new SkillSet[4];  // 4개
    private String imgpath;
    private Pokeshow pokemon_dialog;
    private Pokeshow skill_dialog;
    private User enemy;

    Pokemon(String name , SkillSet skill1 , SkillSet skill2 , SkillSet skill3 ,  SkillSet skill4,String imgpath)
    {
        this.name = name;
        skills[0] = skill1;
        skills[1] = skill2;
        skills[2] = skill3;
        skills[3] = skill4;

        this.imgpath = imgpath;
    }

    public void SetEnemy(User enemy){ this.enemy = enemy; }
    public User GetEnemy(){ return enemy; }

    public String GetName() {  return this.name;  }

    private void pokemon_dialog_show(Point p ,String username)
    {
        pokemon_dialog  = new Pokeshow("User : "  + username);

        ImageIcon icon = new ImageIcon(new ImageIcon(imgpath).getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT));


        JLabel img = new JLabel(icon);

        pokemon_dialog.add(img);

        pokemon_dialog.setSize(300,300);
        pokemon_dialog.setLocation(p);
        pokemon_dialog.pack();

        pokemon_dialog.setVisible(true);
    }

    private void pokemon_skill_show(Point p ,String username)
    {
        skill_dialog  = new Pokeshow("User : "  + username);

        JPanel panel = new JPanel();

        for(SkillSet skill: skills)
        {
            int skill_origin = skill.GetAttackInfo()[2];
            JButton jButton = new JButton(skill.GetSkillName() + " " + skill.GetAttackInfo()[0] + " : " + skill.GetAttackInfo()[2] + " / " + skill_origin);

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enemy.attacked(skill.attack());
                    jButton.setText(skill.GetSkillName() + " " + skill.GetAttackInfo()[0] + " : " + skill.GetAttackInfo()[2] + " / " + skill_origin);
                }
            };

            jButton.addActionListener(listener);
            panel.add(jButton);
        }

        skill_dialog.add(panel);
        skill_dialog.setSize(300,300);
        skill_dialog.setLocation(p.x-50,p.y+400);
        skill_dialog.pack();

        skill_dialog.setVisible(true);
    }

    public void init_pokemon_show(Point p,String username)
    {
        pokemon_dialog_show(p,username);
        pokemon_skill_show(p,username);
    }

    public void game_over()
    {
        pokemon_dialog.dispose();
        skill_dialog.dispose();
    }

}
//피카츄 라이츄 파이리 꼬부기 야도란 피존투 또가스 단데기
