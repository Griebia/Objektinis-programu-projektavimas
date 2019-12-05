package tconq.entity.chain_of_responsibility;

import tconq.entity.IEntity;

public class AttackMedium implements Chain {

    private Chain nextChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public boolean attack(IEntity attacker, IEntity opponent) {
        if (opponent.getEntityClass(opponent).getSimpleName().toLowerCase().equals("mediumunit")){
            if ( attacker.getEntityClass(attacker).getSimpleName().toLowerCase().equals("strongunit") ||
                 attacker.getEntityClass(attacker).getSimpleName().toLowerCase().equals("mediumunit")) {
                System.out.println("attacking " + attacker.getEntityClass(attacker).getSimpleName().toLowerCase() + " killed defending mediumunit");
                return true;
            }
            else
            {
                System.out.println("defending mediumunit killed attacking weakunit");
                return false;
            }

        }
        else{
            return nextChain.attack(attacker, opponent);
        }
    }
}
