package ru.job4j.ood.lsp;

public class DragonSword extends Sword {

    public DragonSword(int damage) {
        super(damage);
    }

    @Override
    public void setDamage(int damage) {
        super.damage = damage;
    }

    @Override
    public int getDamage() {
        int actualDamage = super.damage;
        if (actualDamage <= 10) {
            actualDamage += 2;
        }
        return actualDamage;
    }
}
