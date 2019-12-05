//package tconq.entity.decorator;
//
//import tconq.entity.IEntity;
//
//public class Attack extends UnitDecorator {
//    public Attack(IEntity newUnit) {
//        super(newUnit);
//    }
//
//    //checks if current unit can beat passed unit
//    public boolean getAttack(IEntity enemyUnit) {
//        switch (tempUnit.getEntityClass(tempUnit).getSimpleName().toLowerCase()){       //determines current units type
//            case "weakunit":    //if type is WeakUnit
//                if (enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("weakunit"))
//                    return true;
//                else
//                    return false;
//            case "mediumunit":  //if type is mediumUnit
//                if (enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("weakunit") ||
//                    enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("mediumunit"))
//                    return true;
//                else
//                    return false;
//            case "strongunit": //if type is strongUnit
//                if (enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("weakunit") ||
//                    enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("mediumunit") ||
//                    enemyUnit.getEntityClass(enemyUnit).getSimpleName().toLowerCase().equals("strongunit"))
//                    return true;
//                else
//                    return false;
//        }
//
//        return false;
//    }
//}
