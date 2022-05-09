import java.util.ArrayList;

class Item {
    int price, weight, type;

    public Item(int price, int weight, int type) {
        this.price = price;
        this.weight = weight;
        this.type = type;
    }
}

class Enemy {
    int hp, damage;
    int exp;

    public Enemy(int hp, int damage, int exp) {
        this.hp = hp;
        this.damage = damage;
        this.exp = exp;
    }
}

abstract class Hero {

    protected String nickName;
    protected int s, a, i, exp, hp, mana; // ���� �������� ��������� ���� �������� ����
    protected int x, y; // ���������� ����� �� �����
    protected ArrayList<Item> items = new ArrayList<>(); // ������ �������� ���������
    protected int damage; // ����

    public int getMana() {
        return mana;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public void goToCursor(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public abstract void attack(Enemy enemy); // ���� ����� ������� ���� ��������� � ������� �����������

    public abstract void defense(Enemy enemy);// ���� ����� ������� ���� ��������� � ������� �����������

    public abstract void isLvlUP();

    public void openItem(Item item) {
        int r = (int) (Math.random() * 100);
        if (r <= 50) this.items.add(item);
        ;


        // ����� ������ ��������� ������� � ������ � ������������ 50 %
        // ��� ������������� ������������� ��������� ����� ������������ ��������� ����� �� 0 �� 100.
    }

}

class Warrior extends Hero {
    public Warrior() {
        hp = 500;
        mana = 10;
        s = 100;
        a = 50;
        i = 1;
        exp = 0;
        damage = 150;//������� ��������
    }


    @Override
    public void attack(Enemy enemy) {
        if (enemy.hp > 0 && this.hp > 0) {
            enemy.hp -= this.damage;
        }
        if (this.hp > 0 && enemy.hp <= 0) {
            this.exp += enemy.exp;
            isLvlUP();
        }
    }

    @Override
    public void defense(Enemy enemy) {
        if (enemy.hp > 0 && this.hp > 0) {
            if (enemy.hp > 0) this.hp -= enemy.damage;
        }
        if (this.hp <= 0) System.out.println("��� ����� ��� ����");

    }

    @Override
    public void isLvlUP() {
        while (this.exp >= 500) {
            this.exp -= 500;
            this.hp += 200;
            this.damage += 20;
            this.s += 10;
            this.a += 10;
            this.i += 10;
        }

    }

    @Override
    public void openItem(Item item) {
        super.openItem(item);
        int count = 0;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).type == 1 && i == items.size() - 1) count++;
            else return;
        }
        if (count == 1) this.damage += 100;
    }

    /*
����� ������ �������� ����� ���������� ����� ��� �������� ���� ������ �������� ���������� ����������������:
 �������� - 500, ���� - 10, ���� - 100, �������� - 50, ��������� - 1, ���� 0, ���� - 150.
 ��� ����� ����� ������� ���� ���� � �������� ���� � ����� �� ����, �������� ����������� ���� ��� �� �� �������.
 � ������ ������ ����� �������� ���� ����, ������ 500 ������ ����� ����� �������� ����� �������.
 ����� ������� ���� +10 �� ���� ���������������, +200 �������� � +20 �����.
 ���� ���� ����� ������� 1 ����, � ��� ����� ����������� 100.
            ��� ������� ���� - ����� ����� �� �������!!!
            */
}

class Archer extends Hero {
    public Archer() {
        hp = 500;
        mana = 10;
        s = 100;
        a = 50;
        i = 1;
        exp = 0;
        damage = 150;//������� ��������
    } //������� ��������

    @Override
    public void attack(Enemy enemy) {
        if (enemy.hp > 0 && this.hp > 0) {
            enemy.hp -= this.damage;
        }
        if (this.hp > 0 && enemy.hp <= 0) {
            this.exp += enemy.exp;
            isLvlUP();
        }
    }

    @Override
    public void defense(Enemy enemy) {
        if (enemy.hp > 0 && this.hp > 0) {
            int r = (int) (Math.random() * 100);
            if (enemy.hp > 0 && r < 30) this.hp -= enemy.damage;
        }
        if (this.hp <= 0) System.out.println("��� ����� ��� ����");

    }

    @Override
    public void isLvlUP() {
        while (this.exp >= 500) {
            this.exp -= 500;
            this.hp += 50;
            this.damage += 50;
            this.s += 10;
            this.a += 40;
            this.i += 10;
        }
    }

    @Override
    public void openItem(Item item) {
        this.items.add(item);
    }
    /*
                ����� ������ �������� ����� ���������� ����� ��� �������� ������ ������ �������� ���������� ����������������:
                 �������� - 200, ���� - 50, ���� - 20, �������� - 150, ��������� - 30, ���� 0, ���� - 200.
            ����� �� �������� � ������, �� � �������� ����� ������ ����� 30% ���� �������� ����
            ������ ������ ��������� ������� - 100%
            ����� ������� ���� +10 �� ���� ���������������, +50 �������� � +50 ����� � 30 ��������.
            */
}

class Magician extends Hero {
    public Magician() {
        hp = 100;
        mana = 5000;
        s = 5;
        a = 30;
        i = 300;
        exp = 0;
        damage = 40;
        //������� ��������
    }

    ArrayList<Item> casts = new ArrayList<>();
    /*
        ����� ������ �������� ����� ���������� ����� ��� �������� ��� ������ �������� ���������� ����������������:
        �������� - 100, ���� - 5000, ���� - 5, �������� - 30, ��������� - 300, ���� 0, ���� - 40.
    ����� �� �������� � ������
    ������ ������� �����, ��� ����� ����������� ���� ����������� � �������� �����
    ������ ���� �������������� ���������� �����, ������� ��������� ���� ����, �� ������ �������� ������ ����.
    ����� ������� ���� +10 �� ���� ���������������, +30 ��������, 1000 ���� � +10 �����.
    � ������ ����������� ����� ����� � ���� - ��� ���� � ����!!! � ������ �������
    */

    @Override
    public void attack(Enemy enemy) {
        if (enemy.hp > 0 && this.hp > 0) {

            if (enemy.hp > this.damage) makeCast(enemy);
            else enemy.hp -= this.damage;
        }
        if (this.hp > 0 && enemy.hp <= 0) {
            this.exp += enemy.exp;
            isLvlUP();
        }

    }

    @Override
    public void defense(Enemy enemy) {
        if (enemy.hp > 0 && this.hp > 0) {
            if (enemy.hp > 0) {
                if (mana > enemy.damage) this.mana -= enemy.damage;
                else this.hp -= enemy.damage;
            }
        }
        if (this.hp <= 0) System.out.println("��� ����� ��� ����");
    }


    @Override
    public void isLvlUP() {
        while (this.exp >= 500) {
            this.exp -= 500;
            this.hp += 30;
            this.damage += 10;
            this.s += 10;
            this.a += 10;
            this.i += 10;
            this.mana += 1000;
        }
    }

    public void makeCast(Enemy enemy) {
        if (casts.size() > 0 && mana > 100) {
            this.mana -= 100;
            enemy.hp -= 1000;
            casts.remove(casts.size() - 1);
        }
        // ���� � ������ ���� �������� ����� ��� ���������� �������� 100 ���� � ������ 1000 ����� ����.
        // ����� ������������ ������� �� ������ ���������
    }

    @Override
    public void openItem(Item item) {
        super.openItem(item);
        eduCast(item);
    }

    public void eduCast(Item item) {
        if (item.type == 2) casts.add(item);
    }
                /*
                ���� ������� ��� �������� �� ������ ����� ��� 2, ��� ���������� � �� ����� �������� � ������
���������� ����
                */
}



public class Main {
    public static void main(String[] args) {
        //Hero myHero = new Warrior();
        //   Hero myHero = new Archer();
        Hero myHero = new Magician();

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(30, 20, 1200)); // 1 - ��������� �������
        enemies.add(new Enemy(2018, 1, 350)); // 2 - ���� �������
        enemies.add(new Enemy(280, 50, 3050)); // 3 - ���
        enemies.add(new Enemy(100500, 100500, 100500)); // 4 - ����������

        //������ ����
        for (int i = 0; i < 300; i++) {
            Item item = new Item(0, 0, i % 3); // i%3 - ����� �������� �������� ��� 0, 1, 2
            myHero.openItem(item);  // ����� �������� ������� � ����
        }
        System.out.println("����� �������: " + myHero.items.size());
        System.out.println ("����� ����������: " + ((Magician) myHero).casts.size());

        // ���!
        int count = 1;
        for (Enemy enemy : enemies) {
            System.out.println(count++ + "-� ���:");
            System.out.println("  ����� { hp=" + myHero.hp + " mana=" + myHero.mana + " }");
            System.out.println("  ���� { hp=" + enemy.hp + " }");
            int counter = 1;
            while (myHero.hp > 0 & enemy.hp > 0) {
                System.out.println(counter++ + " �����:");
                myHero.attack(enemy);
                myHero.defense(enemy);
                System.out.println("    ����� { hp=" + myHero.hp + " mana=" + myHero.mana + " }");
                System.out.println("    ���� { hp=" + enemy.hp + " }");
            }
            System.out.println(myHero.hp > 0 ? "������, ����� ������� ���� " + enemy.exp : "���������");
            System.out.printf("����� { HP=%d, MANNA=%d, damage=%d, exp=%d }\n", myHero.hp, myHero.mana, myHero.damage, myHero.exp);
        }
    }
}
