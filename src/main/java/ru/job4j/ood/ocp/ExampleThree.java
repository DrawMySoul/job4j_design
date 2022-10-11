package ru.job4j.ood.ocp;

public class ExampleThree {

    interface Ammunition {
    }

    private class Sword implements Ammunition {

    }

    private class AmmunitionGenerator {

        public Sword getAmmunition() {
            return new Sword();
        }
    }

    private class Fight {

        public void hit(Sword sword) {

        }
    }
}
