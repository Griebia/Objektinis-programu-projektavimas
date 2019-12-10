package tconq.entity.chain_of_responsibility;

import tconq.entity.IEntity;

public interface Chain {

    public void setNextChain(Chain nextChain);

    public boolean attack(IEntity attacker, IEntity opponent);

}
