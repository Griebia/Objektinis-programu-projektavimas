package tconq.entity.chain_of_responsibility;

import tconq.entity.IEntity;

public class AttackStrong implements Chain{

    private Chain nextChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public boolean attack(IEntity attacker, IEntity opponent) {
        if (opponent.getEntityClass(opponent).getSimpleName().toLowerCase().equals("strongunit")){
            if ( attacker.getEntityClass(attacker).getSimpleName().toLowerCase().equals("strongunit")) {
                System.out.println("attacking strongunit killed defending mediumunit");
                return true;
            }
            else
            {
                System.out.println("defending strongunit killed attacking " + attacker.getEntityClass(attacker).getSimpleName().toLowerCase());
                return false;
            }

        }
        else{
            return nextChain.attack(attacker, opponent);
        }
    }
}
