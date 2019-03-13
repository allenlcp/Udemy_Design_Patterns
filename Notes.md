## Overview
23 design patterns 
* Divided into 3 types (gang of four) - organised by purpose (reflects what a pattern does)
> Creational - concern the process of object creation
> Structural - deal with the composition of classes or objects
> Behavioral - characterize the ways in which classes or objects interact and distribute responsibility

* Design patterns can also be organised by scope (whether the pattern applies primarily to classes or to objects)
> class patterns deal with relationships between classes and their subclasses - these relationships are established through inheritance
> object patterns deal with object relationships, which can be changed at run-time and are more dynamic - describes how objects can be composed into larger structure using object composition or by including objects within other objects


## **Software Design Principles**

**Programming to an interface**
* the word interface is overloaded
> there is the concept of interface, but there is also the Java construct interface
> you can program to an interface, without having to actually use a Java interface

* "Porgam to an interface" really means "Program to a supertype"
> the declared type of the var should be a supertype, usually an abstract class or interface
> the objects assigned to those var can be of any concrete implementatin of the supertype
> the class declaring them doesn't have to know about the actual object types

* Do not declare var to be instances of a particular concrete class
> instead, commit only to an interface defined by an abstract class (inteface or abstract)

* always program for the interface and not for the implementation
> will lead to flexible code which can work with any new implementation of the interface

* manipulating objects solely in terms of the interface is beneficial to clients
> clients do not need to know the specific types of objects they use - as long as the objects adhere to the interface that clients expect
> clients do not need to know the classes that implement these objects - they only know about the abstract class(es) defining the interface

* we can use interface types on var, return types of methods or parameter tpyes in a method

* the point is to exploit polymorphism by programming to a supertype so that the actual runtime object is not locked into the code
___
**Abstract Classes vs Interfaces**
> with support of default methods interfaces since the launch of Java 8, the gap between when to use an interface and when to use an abstract calsses has been reduced

> var in interfaces are **public static final** 
> abstract classes can have other access modifiers for variables (private, protected, etc..

> methods in interfaces are **public or public static**
> methods in abstract classes can be private and protected too

> utilize abstract classes to establish a relationship between interrelated objects - when you want to share code among several closely related classes then this common state or behavior can be put in the abstract class
> utlize interfaces to establish a relationship between unrelated classes - the interfaces Comparable and Cloaneable are implemented by many unrelated classes
> utilize interfaces if you want to specify the behavior of a particular data type, but are not concerened about who implements its behavior
> utlize interfaces if you want to take advantag of multiple inheritance (implements)

> **one is not better than the other**

> you might create an interface and then have an abstract class implement that interface

``` java
interface displayModule{
    public void display();
}

class Monitor implements  displayModule {
    public void display() {
        System.out.println("Display through Monitor");
    }
}

class Projector implements  displayModule {
    public void display() {
        System.out.println("Display through Projector");
    }
}

public class Computer{
    displayModule dm;

    public void setDisplayModule(displayModule dm) {
        this.dm = dm;
    }

    public void display(){
        dm.display();
    }

    public static void main(String[] args) {
        Computer cm = new Computer();
        displayModule dm1 = new Monitor();
        displayModule dm2 = new Projector();
        cm.setDisplayModule(dm1);
        cm.display();
        cm.setDisplayModule(dm2);
        cm.display();
    }
}
```
___

**Composition vs Inheritance**

Composition
* composition is refereed as a HAS-A relationship between classes in OO design - an object contains(owns) another object as a member var of its class

* composition implies a relationship where the child cannot exist independent of the parent
> somethis is part of another thing (wheels on an airplane)
> rooms in a house - each house has a room or many rooms, rooms do not exist separate to a house
> cells in a body - when the body object is detroyed, the cells get destroyed with it

Aggregation
* aggregation is a HAS-A relationship between objects and is closely related to composition
* aggregation implies a relationship where the child can exist independently of the parent 
> a collection of things that are not part of it
> airplanes at an airport
> student in a class -get rid of the class and the students still exist
> tires on a car - the tires can be taken off of the car and installed on a different one

* aggregation and composition are almost completely identical except that composition is used when the life of the child is completely controlled by the parent
> the disticntion loses much of its importance in languages that have garbage collection  - you do not have to concern yourself with the life of the object

* favoring obj composition over class inheritance helps you keep each class encapsulated and focused on one task
* your calsses and class hierarchies will remain smaill and will be less likely to grow into unmanageable monsters

* inheritance breaks encapsulation because sub classes are dependent upon the base class behavior
> inheritance is tightly coupled wiehreas composition is loosely coupled
> when behavior of super class changes, functionality in sub class may get broken, without any change on its part

* java does not support multiple inheritance

* better test-ability
> tdd - test driven development

* composition allows for code reuse from final classes - impossible with inheritance because you cannot extend a final class in Java

___

**Delegation Principles**
* delegation is the concept of one class "delegating" its behavior to another class
> don't do all stuff by yourself, delegate it to a respective class
> when you delegate, you are simply calling up some class which knows what must be done
> don not really care how it does it, all you care about is that the calss you are calling knows what it needs to do

* delegation can be viewed as a relationship between objects where one object forwards certain method calls to another object, called its delegate

* delegation is an extreme example of object composition
> shows that you can always replace inheritance with object composition as a mechanism for code reuse
> delegation means that you use an object of another calss as an instance variable, and forward messages to the instance

* it is better than inheritance for many cases
> it makes you to think about each message you forward - the instance is of a known class, rather than a new class
> it does not force you to accept all the methods of the super calss - you can provide only the methods that really make sense

Advantages
* the primary adv of delegation is run-time flexibility - it makes it easy to compose behaviors at run-time and to change the way they are composed
* delegation is a good design only when it simplifies more than it complicates
> how effective it will be depends on the context and on how much experience you have with it
> delegation works best when it is used in design patterns
* several design patterns use delegation:
> State - an object delegates requests to a State object that represents its current state
> Strategy - an object delegates a specific request to an object that represents a strategy for carrying out the request
> Visitor - the operation that gets performed on each element of an object structure is always delegated to the Visitor object


Examples
* the equals() and hashCode() method in Java is a classic example of delegation - in order to compare 2 objects for equality, we ask the class itself to do comparison instead of client class doing that check

* event delegation is another example - an event is delegated to handlers for handling

``` java
class RealPrinter{
    // the "delegate"
    void print(){
        System.out.println("The Delegate");
    }
}

class Printer{
    // the "delegator"
    RealPrinter p = new RealPrinter();

    // create the delegate
    void print(){
        p.print();
    }
}

public class DelegationExample {
    public static void main(String[] args) {
        // To the outside world it looks like Printer actually prints
        Printer printer = new Printer();
        printer.print();
    }
}
```
___


## **Single Responsibility**
* states that every class should have responsibility over a single part of the functionality provided by the software
> the responsibility should be entirely encapsulated by the class
> all its methods should be narrowly aligned with that responsibility
> a class should have only one job

* a class should have a single responsiblity, where a responsibility is nothing but a reason to change

* should make sure that one class at most is responsible for doing one task or functionality among the whole set of responsibiities that it has
> only when theres is change needed in that specific task or functionality should this class be changed

* the single responsiblity principle is closely related to the concepts of coupling and cohesion

**Coupling** is the degree of interdependence between software classes or methods
> a measure of how closely connected two classes or two methods are
> the stength of the realtionship between classes

* low coupling means small dependencies between classes/methods
> easier to change code without introducing bugs in other classes or other methods

* tight coupling means two classes/methods are closely connected
> a change in one module may affect another module

* cohesion refers to what the class (or method) can do

**Cohesion** refers to what the class (or method) can do

* low cohesion would mean that the class does a great variety of actions
> it is broad, unfocused on what it should do

* high cohesion means that the class is focused on what it should be doing 
> contains only methods relating to the intention of the class

* The single resonsibility principle is about limiting the impact of change by designing loosely(low) coupled classes that are highly cohesive

Examples of responsibilities
* some examples of responsiblities to consider that may need to be seperated include:
> Persistence
> Class Selectin / Instantiation
> Validatin
> Formatting
> Notification
> Parsing
> Error Handling
> Mapping
> Logging


``` java
class Employee{
    private String employeeId;
    private String name;
    private String address;
    private Date dateOfJoining;

    public boolean isPromotionDueThisYear(){ // -> hr responsiblity
        // promotion logic
    }

    public double calcIncomeTaxForCurrentYear(){  // -> finance responsibility
        // income tax logic
    }

    // getters / setters for all member variables
}
```
* refactored
``` java
class Employee{
    private String employeeId;
    private String name;
    private String address;
    private Date dateOfJoining;

    // getters / setters for all member variables
}
```
``` java
public class HRPromotions {
    public boolean isPromotionDueThisYear(Employee emp){
        // promotion logic implementation is using the employee info passed in
    }
}
``` java
public class Finance {
    public double calcIncomeTaxForCurrentYear(Employee emp){
        // income tax logic using employee passed in
    }
}
```
___

## **Open Closed Principle**

* Bertrand Meyer proposed the open-closed principle (OCP)
> classes and methods should be open for extension (new functionnality) and closed for modification
> a class should be easily extendable without modifying the class itself

* a module is said to open if it is still available for extension
> it should be possible to add fields to the data structures it contains, or new elements to the set of functions it performs

* a module is said to be claosed if it is available for use by other modules
> assumes that the module has been given a well-defined, stable description
> the interface in the sense of informatin hiding (not a java interface)

* a general idean of this principle is that it tells you to write your code so that you will be able to add new functionality without changing the existing code
> prevents situations in which a change to one of your classes also requires you to adapt all depending classess
> reduces tight coupling

* Robert C. Marting considered this principle as the "most important principle of OO design"

* unfortunately, Bertrand Mayer proposed the use of inheritance to achieve the open/closed principle

* however, inheritance introduces tight coupling if the subclasses depend on implementation details of their parent class

* others redefined the Open/Closed Principle to the Polymorphic Open/Closed Principle
> uses interfaces instead of super classes to allow diffrent implementation
> interfaces can be reused through inheritance but implementation need not be
> can easily substitute without changing the code taht uses them - multiple implementations can be created and polymorphically substituted for each other

* interfaces are closed for modifications
> you can provide new implementations to extend the functionality of your software
> new implementations must implement the interface

* interfaces introduce an additional level of abstraction which enables loose coupling
> interfaces are independent of eact other and don't need to share any code (usually)

Problem - evertime we have a new shape we need to modify the AreaCalculator class with a new method
``` java
class AreaCalculator{
    public double calculateRectangleArea(Rectangle rectangle){
        return rectangle.length * rectangle.width;
    }
    public double calculateCircleArea(Circle circle){
        return (22/7) * circle.radius * circle.radius;
    }
}

class Rectangle{
    public double length;
    public double width;
}

class Circle{
    public double radius;
}
```

* Refactor - use interface and have each class implement specifics
> open for extension by implementing interface
> close for modification
> example also use delegation and polymorphism

``` java
interface Shape{
    double calculateArea();
}

class Rectangle implements Shape{
    public double length;
    public double width;

    public double calculateArea() {
        return length * width;
    }
}

class Circle implements Shape{
    public double radius;

    public double calculateArea() {
        return (22/7) * radius * radius;
    }
}

class AreaCalculator{
    public double calculateShapeArea(Shape shape){
        return shape.calculateArea();
    }
}
```
___

## **Liskov Substition Principle**
* was introduced by Barbara Liskov

* the principle defines that objects of a supercalss can be replaceable with objects of its superclasses without breaking the application
> requires the objects of your subclasses to behave in the same way as the objects of your superclass
> methods which use a superclass type must be able to work with the subclass without any issues

* an overriden method of a subclass needs to accept the same input parameter values as the method of the supercalss
> do not implement any stricter validation rules on input parameters than implemented by the parent class
> any code that calls this method on an object of the supercalss might cause an exception, if it gets called with an object of the subclass

* the return value of a method of the subclass needs to comply with the same rules as the return value of the method of the superclass
> you can only decide to apply stricted rules by returning a specific subclass of the defined return valur or by returning a subset of the valid return values of the superclass

* in order to follow LSP the subclass must enhance functionality, but not reduce functionality
___

## **Interface Segregation Principle**
* was defined by Robert C. Martin

* "Clients should not be forced to depend upon interfaces that they do not use"
> a client should not implement an interface if it does not use a method in that interface
> happens mostly when one interface contains more than one functionality, and the client only needs one fuctinality and not the other

* the goal of the interface segregation principle is to reduce the side effects and frequency of required changes by splitting the software into multiple, independent parts

* interface design is a tricky job because you release your interface you can not change it without breaking all implementation

* using the interface keyword in Java means that you have to implement all of the methods in the interface before any class can use it.
> if you follow this principle in Java, you will implement less methods because each interface will have a single functionality

* if we created a single interface then all clients will have to unnecessarily implement all other clients' methods just to make their interface compile
> this is referred to as a "fat" interface
> makes the overall design rigid due to the ernomous effort required to manage changes across all clients when making a change to a method pertaining to only one client

* it avoids the design drawbacks associated with a fat interface by refactoring each fat interface into multiple segregated interfaces
> each segregate interface is a lean interface as it only contains methods which are required for a specific client

* a lean interface does not mean one method per interface

* a lean interface caters to a consumers of a specific type of functionality or a specific set of customers all of whom have the same functional needs


Example 1 - Problem - this interface does not cater for flat shapes e.g circle does not have volume
``` java
interface ShapeInterface{
    double area();  
    double volume();  
}
```
Refactored - split the interface
``` java
interface ShapeInterface{
    double area();
}

interface SolidShapeInterface{
    double volume();
}

class Cube implements  ShapeInterface, SolidShapeInterface{
    @Override
    public double area() {
        // calculate the surface area of the cuboid
        return 1.0;
    }

    @Override
    public double volume() {
        // calculate the volume of the cuboid
        return 1.0;
    }
}
```
Refactored - added interface for calculate() -> this consumer will only see calculate
``` java 

interface ShapeInterface{
    double area();
}

interface SolidShapeInterface{
    double volume();
}

interface ManagedShapeInterface{
    public double calculate();
}

class Cube implements  ShapeInterface, SolidShapeInterface, ManagedShapeInterface{
    @Override
    public double area() {
        // calculate the surface area of the cuboid
        return 1.0;
    }

    @Override
    public double volume() {
        // calculate the volume of the cuboid
        return 1.0;
    }

    @Override
    public double calculate() {
        return this.area() + this.volume();
    }
}

class Square implements ShapeInterface, ManagedShapeInterface{
    @Override
    public double area() {
        return 2.0;
    }

    @Override
    public double calculate() {
        return this.area();
    }
}
```


Example 2 - Problem - client need to implement all methods 
``` java 
interface RestaurantInterface{
    void acceptOnlineOrder();
    void takeTelephoneOrder();
    void payOnline();
    void walkInCustomerOrder();
    void payInPerson();
}

class OnlineClientImpl implements RestaurantInterface{
    @Override
    public void acceptOnlineOrder() {}

    @Override
    public void takeTelephoneOrder() {}

    @Override
    public void payOnline() {}

    @Override
    public void walkInCustomerOrder() {}

    @Override
    public void payInPerson() {}
}
```

Refactored
* seperated interface into
> payments type
> online orders
> walk-in orders

___


## **Dependency Inversion**