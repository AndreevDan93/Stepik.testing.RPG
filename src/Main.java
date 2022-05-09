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
    protected int s, a, i, exp, hp, mana; // сила ловкость интеллект опыт здоровье мана
    protected int x, y; // координаты героя на карте
    protected ArrayList<Item> items = new ArrayList<>(); // список поднятых предметов
    protected int damage; // урон

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

    public abstract void attack(Enemy enemy); // этот метод должнен быть определен в классах наследниках

    public abstract void defense(Enemy enemy);// этот метод должнен быть определен в классах наследниках

    public abstract void isLvlUP();

    public void openItem(Item item) {
        int r = (int) (Math.random() * 100);
        if (r <= 50) this.items.add(item);
        ;


        // метод должен добавлять предмет в список с вероятностью 50 %
        // для осуществления вероятностных процессов можно использовать случайное число от 0 до 100.
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
        damage = 150;//базовые значение
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
        if (this.hp <= 0) System.out.println("Ваш герой был убит");

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
класс должен обладать всеми свойствами героя при создании воин должен обладать следующими характеристиками:
 здоровье - 500, мана - 10, сила - 100, ловкость - 50, интеллект - 1, опыт 0, урон - 150.
 При атаке герой наносит цели урон и получает урон в ответ от цели, действия повторяются пока кто то не победит.
 В случае победы герой получает опыт цели, каждые 500 единиц опыта герой получает новый уровень.
 Новый уровень дает +10 ко всем характеристикам, +200 здоровья и +20 урона.
 Если воин имеет предмет 1 типа, к его урону добавляется 100.
            Уже мертвая цель - урона герою не наносит!!!
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
        damage = 150;//базовые значение
    } //базовые значение

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
        if (this.hp <= 0) System.out.println("Ваш герой был убит");

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
                класс должен обладать всеми свойствами героя при создании лучник должен обладать следующими характеристиками:
                 здоровье - 200, мана - 50, сила - 20, ловкость - 150, интеллект - 30, опыт 0, урон - 200.
            атака по аналогии с воином, но в процессе атаки лучник имеет 30% шанс избежать урон
            лучник всегда открывает предмет - 100%
            Новый уровень дает +10 ко всем характеристикам, +50 здоровья и +50 урона и 30 ловкости.
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
        //базовые значение
    }

    ArrayList<Item> casts = new ArrayList<>();
    /*
        класс должен обладать всеми свойствами героя при создании маг должен обладать следующими характеристиками:
        здоровье - 100, мана - 5000, сила - 5, ловкость - 30, интеллект - 300, опыт 0, урон - 40.
    атака по аналогии с воином
    помимо обычной атаки, маг имеет возможность бить заклинанием в процессе атаки
    защита мага осуществляется магическим щитом, который полгащает весь урон, но вместо здоровья тратит ману.
    Новый уровень дает +10 ко всем характеристикам, +30 здоровья, 1000 маны и +10 урона.
    В случае возможности убить врага с руки - маг бьет с руки!!! в Первую очередь
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
        if (this.hp <= 0) System.out.println("Ваш герой был убит");
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
        // если в списке есть заклятия можно его произнести потратив 100 маны и нанеся 1000 урона цели.
        // после произнесения предмет из списка пропадает
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
                если предмет или предметы из списка имеют тип 2, это заклинания и их нужно добавить в список
заклинаний мага
                */
}



public class Main {
    public static void main(String[] args) {
        //Hero myHero = new Warrior();
        //   Hero myHero = new Archer();
        Hero myHero = new Magician();

        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy(30, 20, 1200)); // 1 - Пряничный человек
        enemies.add(new Enemy(2018, 1, 350)); // 2 - Туча комаров
        enemies.add(new Enemy(280, 50, 3050)); // 3 - Вор
        enemies.add(new Enemy(100500, 100500, 100500)); // 4 - Гальватрон

        //найден клад
        for (int i = 0; i < 300; i++) {
            Item item = new Item(0, 0, i % 3); // i%3 - будет задавать предмету тип 0, 1, 2
            myHero.openItem(item);  // герои получает предмет в руки
        }
        System.out.println("Всего собрано: " + myHero.items.size());
        System.out.println ("Всего заклинаний: " + ((Magician) myHero).casts.size());

        // бой!
        int count = 1;
        for (Enemy enemy : enemies) {
            System.out.println(count++ + "-й бой:");
            System.out.println("  Герои { hp=" + myHero.hp + " mana=" + myHero.mana + " }");
            System.out.println("  Враг { hp=" + enemy.hp + " }");
            int counter = 1;
            while (myHero.hp > 0 & enemy.hp > 0) {
                System.out.println(counter++ + " раунд:");
                myHero.attack(enemy);
                myHero.defense(enemy);
                System.out.println("    Герои { hp=" + myHero.hp + " mana=" + myHero.mana + " }");
                System.out.println("    Враг { hp=" + enemy.hp + " }");
            }
            System.out.println(myHero.hp > 0 ? "Победа, герой получил опыт " + enemy.exp : "Поражение");
            System.out.printf("Герой { HP=%d, MANNA=%d, damage=%d, exp=%d }\n", myHero.hp, myHero.mana, myHero.damage, myHero.exp);
        }
    }
}
