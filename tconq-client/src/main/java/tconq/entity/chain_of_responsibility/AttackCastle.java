package tconq.entity.chain_of_responsibility;

import tconq.entity.IEntity;

public class AttackCastle implements Chain {
    private Chain nextChain;

    @Override
    public void setNextChain(Chain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public boolean attack(IEntity attacker, IEntity opponent) {
        if (opponent.getEntityClass(opponent).getSimpleName().toLowerCase().equals("castle")){
            if ( attacker.getEntityClass(attacker).getSimpleName().toLowerCase().equals("strongunit")) {
                System.out.println("attacking strongunit destroyed castle");
                return true;
            }
            else
            {
                System.out.println("defending castle killed attacking " + attacker.getEntityClass(attacker).getSimpleName().toLowerCase());
                return false;
            }

        }
        else{
            System.out.println("attack failed attacking unit dies");
            return false;
        }
    }
}
