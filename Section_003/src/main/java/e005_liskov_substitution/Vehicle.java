package e005_liskov_substitution;

public abstract class Vehicle {
    abstract int getSpeed();
    abstract int getCubicCapacity();
}
//
//class Car extends Vehicle{
//    int getSpeed(){}
//    int getCubicCapacity() {}
//    boolean sHatchBack(){}
//}
//
//class Bus extends Vehicle{
//    int getSpeed(){}
//    int getCubicCapacity() {}
//    boolean getEmergencyExitLoc(){}
//}
//
//public static void main(String[] args) {
//    Vehicle v = new Bus();
//    v.getSpeed();
//    v = new Car();
//    v.getSpeed();
//}