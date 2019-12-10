package tconq.entity.chain_of_responsibility;

import tconq.entity.IEntity;

public class AttackTower implements Chain {

    private Chain nextChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public boolean attack(IEntity attacker, IEntity opponent) {
        if (opponent.getEntityClass(opponent).getSimpleName().toLowerCase().equals("tower")){
            if ( attacker.getEntityClass(attacker).getSimpleName().toLowerCase().equals("strongunit") ||
                 attacker.getEntityClass(attacker).getSimpleName().toLowerCase().equals("mediumunit")) {
                System.out.println("attacking " + attacker.getEntityClass(attacker).getSimpleName().toLowerCase() + " destroyed tower");
                return true;
            }
            else
            {
                System.out.println("defending tower killed attacking weakunit");
                return false;
            }

        }
        else{
            return nextChain.attack(attacker, opponent);
        }
    }
}
