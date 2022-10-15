package ru.job4j.ood.lsp;

public class Sword {
    protected int damage;

    public Sword(int damage) {
        this.damage = damage;
    }

    public void setDamage(int damage) {
        if (damage <= 0) {
            throw new IllegalArgumentException();
        }
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
