import java.util.Random;


interface SkillFunc {
    int attack();
}

abstract class SkillSet implements SkillFunc
{
    private int attack_point; //공격량
    private int attack_probability; //공격 확률
    private int attack_count;

    private int UNDERFLOW_ATTACK_POINT_SIGNAL = -9999;

    SkillSet(int attack_point , int attack_probability , int attack_count)
    {
        this.attack_point = attack_point;
        this.attack_probability = attack_probability;
        this.attack_count = attack_count;
    }

    @Override
    public int attack()
    {
        Random r = new Random();
        int ret_attack_point = attack_point;

        /*
         == 공격확률 ==
         rand( (attack_probability+attack_point)*24 ) % attack_point == 0 - 빗나감 == 0
         rand(attack_probability * 30 + attack_point) % attack_probability == 0 - 치명타  == attack_point * 2
         빗나감이 치명타보다 우선순위
        */

        if(attack_count <= 0)
        {
            return UNDERFLOW_ATTACK_POINT_SIGNAL;
        }

        if(r.nextInt(attack_probability+attack_point)*24 % attack_point == 0 || attack_count <= 0) // 빗 나감
        {
            ret_attack_point = 0;
        }

        if(r.nextInt(attack_probability * 30 + attack_point) % attack_probability == 0) // 치명타
        {
            ret_attack_point = attack_point*2;
        }

        attack_count -= 1;
        return ret_attack_point;
    }

    public int[] GetAttackInfo()
    {
        int[] a = new int[3];
        a[0] = attack_point;
        a[1] = attack_probability;
        a[2] = attack_count;
        return a;
    }

    abstract String GetSkillName();
}


class Quick_Attack extends SkillSet //전광 석화
{
    Quick_Attack(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }

    @Override
    String GetSkillName()
    {
        return "전광 석화";
    }
}



class Bite extends SkillSet //물기
{

    Bite(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }

    @Override
    String GetSkillName()
    {
        return "물기";
    }
}


class Earthquake extends SkillSet //지진
{
    Earthquake(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }
    @Override
    String GetSkillName()
    {
        return "지진";
    }
}

class Teleport extends SkillSet //순간 이동
{
    Teleport(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }
    @Override
    String GetSkillName()
    {
        return "순간 이동";
    }
}

class Sand_Attack extends SkillSet //모래뿌리기
{
    Sand_Attack(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }
    @Override
    String GetSkillName()
    {
        return "모래뿌리기";
    }
}

class Double_Edge extends SkillSet //이판사판 태클
{
    Double_Edge(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }
    @Override
    String GetSkillName()
    {
        return "이판사판 태글";
    }
}

class Rock_Smash extends SkillSet //바위깨기
{
    Rock_Smash(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }
    @Override
    String GetSkillName()
    {
        return "바위 깨기";
    }
}

class Dragon_Pulse extends SkillSet //전광 석화
{
    Dragon_Pulse(int attack_point, int attack_probability , int attack_count) {
        super(attack_point, attack_probability,attack_count);
    }
    @Override
    String GetSkillName()
    {
        return "용의 파동";
    }
}