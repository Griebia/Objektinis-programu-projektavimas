package tconq.entity.chain_of_responsibility;

import tconq.entity.IEntity;

public class AttackWeak implements Chain {

    private Chain nextChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public boolean attack(IEntity attacker, IEntity opponent) {
        if (opponent.getEntityClass(opponent).getSimpleName().toLowerCase().equals("weakunit")){
            System.out.println("attacking " + attacker.getEntityClass(attacker).getSimpleName().toLowerCase() + " killed defending weakunit");
            return true;
        }
        else{
            return nextChain.attack(attacker, opponent);
        }
    }
}
