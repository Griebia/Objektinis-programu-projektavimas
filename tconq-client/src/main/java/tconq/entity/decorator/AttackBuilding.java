//package tconq.entity.decorator;
//
//import tconq.entity.IEntity;
//
//public class AttackBuilding extends UnitDecorator {
//    public AttackBuilding(IEntity newUnit) {
//        super(newUnit);
//    }
//
//    //checks if current unit can destroy passed building
//    public boolean getDestroyBuilding(IEntity enemyBuilding) {
//        switch (tempUnit.getEntityClass(tempUnit).getSimpleName().toLowerCase()){   //determines current units type
//            case "weakunit":    //if type is WeakUnit
//                if (enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("house"))
//                    return true;
//                else
//                    return false;
//            case "mediumunit":  //if type is mediumUnit
//                if (enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("house") ||
//                    enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("tower"))
//                    return true;
//                else
//                    return false;
//            case "strongunit":  //if type is strongUnit
//                if (enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("house") ||
//                    enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("tower") ||
//                    enemyBuilding.getEntityClass(enemyBuilding).getSimpleName().toLowerCase().equals("castle") )
//                    return true;
//                else
//                    return false;
//        }
//
//        return false;
//    }
//}
