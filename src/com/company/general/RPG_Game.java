package com.company.general;

import com.company.players.*;


//Основное задание
//        Добавить в проект каждому классу героя свою уникальную способность
//        Tank должен получать от босса урон, и потом наносить ему свой урон + полученный от босса
//        Magic должен увеличивать атаку каждого героя после каждого раунда на n-ное количество
//        Warrior каждый раз при атаке критует. Т.е. каждая атака умножается на случайное число от 2 до 4.

//        Задание на сообразительность
//        Добавить еще игрока, Thor, имеет шанс оглушить босса на раунд, вследствие чего босс пропускает раунд и не бьёт
//        Добавить еще игрока, Golem, имеет увеличенную жизнь но слабый удар. Может принимать часть удара босса на других игроков себе
public class RPG_Game {

    public static void Start() {
        Boss boss = new Boss(600, 50);
        Warrior warrior = new Warrior(250, 10);
        Tank tank = new Tank(250, 10);
        Magic magic = new Magic(250, 10);
        Medic medic = new Medic(300, 5, 15);
        Medic youngMedic = new Medic(300, 8, 10);

        Hero[] heroes = {warrior, tank, magic, medic, youngMedic};

        printStatistic(boss,heroes);
        while (!isFinished(boss, heroes)) {
            round(boss, heroes);
        }
    }

    private static void printStatistic(Boss boss, Hero[] heroes) {
        System.out.println("------------------------------");
        System.out.println("Boss health: " + boss.getHealth());
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(heroes[i].getClass().getSimpleName() + " Health: " +
                    heroes[i].getHealth());
        }
        System.out.println("------------------------------");
    }

    private static void round(Boss boss, Hero[] heroes) {
        bossHit(boss, heroes);
        heroesHit(boss, heroes);
        heroesApplySuperAbilities(boss, heroes);
        printStatistic(boss, heroes);
    }

    private static boolean isFinished(Boss boss, Hero[] heroes) {
        if (boss.getHealth() < 0) {
            System.out.println("Heroes Won!");
            return true;
        }
        boolean allHeroesDied = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDied = false;
                break;
            }
        }
        if (allHeroesDied) {
            System.out.println("Boss Won!");
        }
        return allHeroesDied;
    }

    private static void bossHit(Boss boss, Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                heroes[i].setHealth(heroes[i].getHealth() - boss.getDamage());

            }
        }
    }

    private static void heroesHit (Boss boss, Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (boss.getHealth() > 0) {
                boss.setHealth(boss.getHealth() - heroes[i].getDamage());
            }
        }
    }
    private static void heroesApplySuperAbilities(Boss boss, Hero[] heroes){
        for (int i = 0; i < heroes.length; i++) {
            heroes[i].applySuperAbility(boss, heroes);

        }
    }
}
