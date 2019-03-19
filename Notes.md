## Overview
23 design patterns 
* Divided into 3 types (gang of four) - organised by purpose (reflects what a pattern does)
    * Creational - concern the process of object creation
    * Structural - deal with the composition of classes or objects
    * Behavioral - characterize the ways in which classes or objects interact and distribute responsibility

* Design patterns can also be organised by scope (whether the pattern applies primarily to classes or to objects)
    * class patterns deal with relationships between classes and their subclasses - these relationships are established through inheritance
    * object patterns deal with object relationships, which can be changed at run-time and are more dynamic - describes how objects can be composed into larger structure using object composition or by including objects within other objects


## **Software Design Principles**

**Programming to an interface**
* the word interface is overloaded
    * there is the concept of interface, but there is also the Java construct interface
    * you can program to an interface, without having to actually use a Java interface

* "Program to an interface" really means "Program to a supertype"
    * the declared type of the var should be a supertype, usually an abstract class or interface
    * the objects assigned to those var can be of any concrete implementation of the supertype
    * the class declaring them doesn't have to know about the actual object types

* Do not declare var to be instances of a particular concrete class
    * instead, commit only to an interface defined by an abstract class (interface or abstract)

* always program for the interface and not for the implementation
    * will lead to flexible code which can work with any new implementation of the interface

* manipulating objects solely in terms of the interface is beneficial to clients
    * clients do not need to know the specific types of objects they use - as long as the objects adhere to the interface that clients expect
    * clients do not need to know the classes that implement these objects - they only know about the abstract class(es) defining the interface

* we can use interface types on var, return types of methods or parameter types in a method

* the point is to exploit polymorphism by programming to a supertype so that the actual runtime object is not locked into the code
___
**Abstract Classes vs Interfaces**
    * with support of default methods interfaces since the launch of Java 8, the gap between when to use an interface and when to use an abstract classes has been reduced

    * var in interfaces are **public static final** 
    * abstract classes can have other access modifiers for variables (private, protected, etc..

    * methods in interfaces are **public or public static**
    * methods in abstract classes can be private and protected too

    * utilize abstract classes to establish a relationship between interrelated objects - when you want to share code among several closely related classes then this common state or behavior can be put in the abstract class
    * utilize interfaces to establish a relationship between unrelated classes - the interfaces Comparable and Cloaneable are implemented by many unrelated classes
    * utilize interfaces if you want to specify the behavior of a particular data type, but are not concerned about who implements its behavior
    * utilize interfaces if you want to take advantage of multiple inheritance (implements)

    * **one is not better than the other**

    * you might create an interface and then have an abstract class implement that interface

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
    * something is part of another thing (wheels on an airplane)
    * rooms in a house - each house has a room or many rooms, rooms do not exist separate to a house
    * cells in a body - when the body object is destroyed, the cells get destroyed with it

Aggregation
* aggregation is a HAS-A relationship between objects and is closely related to composition
* aggregation implies a relationship where the child can exist independently of the parent 
    * a collection of things that are not part of it
    * airplanes at an airport
    * student in a class -get rid of the class and the students still exist
    * tires on a car - the tires can be taken off of the car and installed on a different one

* aggregation and composition are almost completely identical except that composition is used when the life of the child is completely controlled by the parent
    * the distinction loses much of its importance in languages that have garbage collection  - you do not have to concern yourself with the life of the object

* favoring obj composition over class inheritance helps you keep each class encapsulated and focused on one task
* your classes and class hierarchies will remain small and will be less likely to grow into unmanageable monsters

* inheritance breaks encapsulation because sub classes are dependent upon the base class behavior
    * inheritance is tightly coupled whereas composition is loosely coupled
    * when behavior of super class changes, functionality in sub class may get broken, without any change on its part

* java does not support multiple inheritance

* better test-ability
    * tdd - test driven development

* composition allows for code reuse from final classes - impossible with inheritance because you cannot extend a final class in Java

___

**Delegation Principles**
* delegation is the concept of one class "delegating" its behavior to another class
    * don't do all stuff by yourself, delegate it to a respective class
    * when you delegate, you are simply calling up some class which knows what must be done
    * don not really care how it does it, all you care about is that the class you are calling knows what it needs to do

* delegation can be viewed as a relationship between objects where one object forwards certain method calls to another object, called its delegate

* delegation is an extreme example of object composition
    * shows that you can always replace inheritance with object composition as a mechanism for code reuse
    * delegation means that you use an object of another class as an instance variable, and forward messages to the instance

* it is better than inheritance for many cases
    * it makes you to think about each message you forward - the instance is of a known class, rather than a new class
    * it does not force you to accept all the methods of the super class - you can provide only the methods that really make sense

Advantages
* the primary adv of delegation is run-time flexibility - it makes it easy to compose behaviors at run-time and to change the way they are composed
* delegation is a good design only when it simplifies more than it complicates
    * how effective it will be depends on the context and on how much experience you have with it
    * delegation works best when it is used in design patterns
* several design patterns use delegation:
    * State - an object delegates requests to a State object that represents its current state
    * Strategy - an object delegates a specific request to an object that represents a strategy for carrying out the request
    * Visitor - the operation that gets performed on each element of an object structure is always delegated to the Visitor object


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
    * the responsibility should be entirely encapsulated by the class
    * all its methods should be narrowly aligned with that responsibility
    * a class should have only one job

* a class should have a single responsibility, where a responsibility is nothing but a reason to change

* should make sure that one class at most is responsible for doing one task or functionality among the whole set of responsibilities that it has
    * only when theres is change needed in that specific task or functionality should this class be changed

* the single responsibility principle is closely related to the concepts of coupling and cohesion

**Coupling** is the degree of interdependence between software classes or methods
    * a measure of how closely connected two classes or two methods are
    * the strength of the relationship between classes

* low coupling means small dependencies between classes/methods
    * easier to change code without introducing bugs in other classes or other methods

* tight coupling means two classes/methods are closely connected
    * a change in one module may affect another module

* cohesion refers to what the class (or method) can do

**Cohesion** refers to what the class (or method) can do

* low cohesion would mean that the class does a great variety of actions
    * it is broad, unfocused on what it should do

* high cohesion means that the class is focused on what it should be doing 
    * contains only methods relating to the intention of the class

* The single responsibility principle is about limiting the impact of change by designing loosely(low) coupled classes that are highly cohesive

Examples of responsibilities
* some examples of responsibilities to consider that may need to be separated include:
    * Persistence
    * Class Selection / Instantiation
    * Validation
    * Formatting
    * Notification
    * Parsing
    * Error Handling
    * Mapping
    * Logging


``` java
class Employee{
    private String employeeId;
    private String name;
    private String address;
    private Date dateOfJoining;

    public boolean isPromotionDueThisYear(){ // -    * hr responsiblity
        // promotion logic
    }

    public double calcIncomeTaxForCurrentYear(){  // -    * finance responsibility
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
    * classes and methods should be open for extension (new functionality) and closed for modification
    * a class should be easily extendable without modifying the class itself

* a module is said to open if it is still available for extension
    * it should be possible to add fields to the data structures it contains, or new elements to the set of functions it performs

* a module is said to be closed if it is available for use by other modules
    * assumes that the module has been given a well-defined, stable description
    * the interface in the sense of information hiding (not a java interface)

* a general idea of this principle is that it tells you to write your code so that you will be able to add new functionality without changing the existing code
    * prevents situations in which a change to one of your classes also requires you to adapt all depending classes
    * reduces tight coupling

* Robert C. Marting considered this principle as the "most important principle of OO design"

* unfortunately, Bertrand Mayer proposed the use of inheritance to achieve the open/closed principle

* however, inheritance introduces tight coupling if the subclasses depend on implementation details of their parent class

* others redefined the Open/Closed Principle to the Polymorphic Open/Closed Principle
    * uses interfaces instead of super classes to allow different implementation
    * interfaces can be reused through inheritance but implementation need not be
    * can easily substitute without changing the code that uses them - multiple implementations can be created and polymorphically substituted for each other

* interfaces are closed for modifications
    * you can provide new implementations to extend the functionality of your software
    * new implementations must implement the interface

* interfaces introduce an additional level of abstraction which enables loose coupling
    * interfaces are independent of each other and don't need to share any code (usually)

Problem - every time we have a new shape we need to modify the AreaCalculator class with a new method
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
    * open for extension by implementing interface
    * close for modification
    * example also use delegation and polymorphism

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

* the principle defines that objects of a superclass can be replaceable with objects of its superclasses without breaking the application
    * requires the objects of your subclasses to behave in the same way as the objects of your superclass
    * methods which use a superclass type must be able to work with the subclass without any issues

* an overridden method of a subclass needs to accept the same input parameter values as the method of the superclass
    * do not implement any stricter validation rules on input parameters than implemented by the parent class
    * any code that calls this method on an object of the superclass might cause an exception, if it gets called with an object of the subclass

* the return value of a method of the subclass needs to comply with the same rules as the return value of the method of the superclass
    * you can only decide to apply strict rules by returning a specific subclass of the defined return value or by returning a subset of the valid return values of the superclass

* in order to follow LSP the subclass must enhance functionality, but not reduce functionality
___

## **Interface Segregation Principle**
* was defined by Robert C. Martin

* "Clients should not be forced to depend upon interfaces that they do not use"
    * a client should not implement an interface if it does not use a method in that interface
    * happens mostly when one interface contains more than one functionality, and the client only needs one fuctionality and not the other

* the goal of the interface segregation principle is to reduce the side effects and frequency of required changes by splitting the software into multiple, independent parts

* interface design is a tricky job because you release your interface you can not change it without breaking all implementation

* using the interface keyword in Java means that you have to implement all of the methods in the interface before any class can use it.
    * if you follow this principle in Java, you will implement less methods because each interface will have a single functionality

* if we created a single interface then all clients will have to unnecessarily implement all other clients' methods just to make their interface compile
    * this is referred to as a "fat" interface
    * makes the overall design rigid due to the enormous effort required to manage changes across all clients when making a change to a method pertaining to only one client

* it avoids the design drawbacks associated with a fat interface by refactoring each fat interface into multiple segregated interfaces
    * each segregate interface is a lean interface as it only contains methods which are required for a specific client

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
Refactored - added interface for calculate() -    * this consumer will only see calculate
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
* separated interface into
    * payments type
    * online orders
    * walk-in orders

___

## **Dependency Inversion**
* no var should hold a reference to a concrete class - use the factory design pattern to avoid this
* no class should subclass from a concrete class
    * if you subclass from a concrete class, you are depending on a concrete class
    * subclass from an abstraction (an interface or an abstract class)

* no method should override an implemented method of any of its base classes
    * if you override an implemented method, then your base class was not really an abstraction to start with
    * methods implemented in the base class are meant to be shared by all your subclasses

* this is a guideline you should strive for, rather than a rule you should follow all the time
    * if you have a class that is not likely to change, and you know it, then it is ok to instantiate a concrete class
    * we instantiate String objects all the time and this violates the principle - however, the String class is very unlikely to change

___

## **Dependency Injection**

**Dependencies**
* a Java class has a dependency on another class, if it uses an instance of this class
    * referred to as a class dependency
    * a class which accesses a logger service has a dependency on this service class

* java classes should be as independent as possible from another Java classes 
    * increases the possibility of reusing these classes and to be able to test them independently from other classes

* if a java class creates an instance of another class via the new operator, it cannot be uses (and tested) independently from this class - this is called a hard dependency

* dependency injection solves these "hard" dependencies


**Dependency Injection**
* dependency injection is a tech whereby one object supplies the dependencies of another object - enables you to replace dependencies without changing the class that uses them

* a dependency is an object that can be used (a service)

* an injection is the passing of a dependency to a dependent object (a client) that would use it

* allows us to remove the hard-coded dependencies and make our application loosely coupled, extendable and maintainable

* dependency injection is one form of the broader technique of dependency inversion
- supports the dependency inversion principle

* the client delegates the responsibility of providing its dependencies to external code (the injector)


**4 Roles in Dependency Injection**
* if you want to use the DI, you need classes that fulfill 4 basic role
    * the service you want to use
    * the client that uses the service
    * an interface that is used by the client and implemented by the service
    * the injector which creates a service instance and injects it into the client

* you already implement 3 of these 4 roles by following the dependency inversion principle - the service and client are the 2 classes between which the dependency inversion principle intends to remove the dependency by introducing an interface

* the injector is the only role that is not required by the dependency inversion principle


**Injection Types**
* constructor injection - the dependencies are provided through a class constructor
``` java
// Constructor
Client(Service service){
    // save the ref to the passed-in service inside the client
    this.service = service;
}

```

* setter injection - the client exposes a setter method that the injector uses to inject the dependency
``` java
// Setter method
public void setService(Service service){
    // save the ref to the passed-in service inside the client
    this.service = service;
}
```

* interface injection 
    * the dependency provides an injector method that will inject the dependency into any client passed to it
    * clients must implement an interface that exposes a setter method that accepts the dependency

``` java
// Service setter interface
public interface ServiceSetter{
    public void setService(Service service);
}

public class Service{
    void inject(Client c){
        c.setService(this);
    }
}

// Client class
public class Client implements ServiceSetter{
    // Internal ref to the service used by this client.
    private ServiceSetter;

    // Set the service that this client is to be use
    @Override
    public void serService(Service service){
        this.service = service;
    }
}
```
___

## **Creational Design Patterns**
* a program should not depend on how objects are created and arranged

* instantiation is an activity that should not always be done in public and can often lead to coupling problems

* In Java, the simplest way to create an instance of an object is by using the new operator
    * fred = new Fred(); //instance of Fred class
    * creates a concrete class

* Tying your code to a concrete class can make it more fragile and less flexible
    * code may have to be changed as new concrete classes are added
    * your code will not be "closed for modification" to extend it with new concrete types, you will have to reopen it

* creational design patterns provide a way to create objects

* creational design patterns abstract the instantiation process
    * the creation logic is hidden
    * encapsulates knowledge about which concrete classes the system uses
    * programmer may call a method or use another object, rather than instantiating objects directly using the new operator

* all the system at large knows about the objects is their interface as defined by abstract classes
    * gives the programmer a lot of flexibility in what gets created, who creates it, how it gets created, and when
    * lets you configure a systems with "product" objects that vary widely in structure and functionality
    * configuration can be static (compile-time) or dynamic (at run-time)

* by coding to an interface, you can insulate yourself from a lot of changes that might happen to a system down the road
* sometimes creational patterns are competitors
    * there are cases either Prototype or Abstract Factory could be used profitably

    * sometimes creational patterns are complementary
    * builder can use one of the other patterns to implement which components get built
    * prototype can use Singleton in its implementation


**Class Patterns vs. Object Patterns (sub-categories)**
* class patterns describe how relationships between classes are defined
    * use inheritance
    * relationships are established at compile time
    * factory pattern
    * drawback of this approach is that it can require creating a new subclass just to change the class of the product - changes can cascade when the product creator is itself created by a factory method, then you have to override its creator as well

* object patterns describe relationships between objects
    * use composition
    * relationships are typically created at runtime - more dynamic and flexible
    * abstract factory, singleton, builder, and prototype patterns

___

## **1. Factory**
* one of the most used design patterns in Java
    * a creational pattern
    * factories handle the details of object creation

* this pattern defines an interface for creating an object (Creator)

* when a class needs to instantiate a subclass of another class, but doesn't know which one - it lets subclasses decide which class to instantiate

* creates objects without exposing the creation logic to the client (Creator) and refers to the newly created object using a common interface (Product)

* gives us a way to encapsulate the instantiations of concrete types

Frameworks
* the factory method is used in frameworks
    * frameworks exist at an abstraction level

* frameworks uses abstract classes to define and maintain relationships between objects
    * often responsible for creating these objects as well

* the framework should not know and should not be concerned about instantiating specific objects 
    * need to defer the decisions about specific objects to the users of the framework

* use the Factory Method pattern when
    * a class cannot anticipate the class of objects it must create
    * a class wants its subclasses to specify the objects it creates

* also useful when implementing parallel class hierarchies 
    * when some of the responsibilities shift from one class to another

**(a) Factory Method Implementation (Abstract Creator)**
* It is the "strictest" implementation of the pattern
    * the creator class is an abstract class
    * you create a subclass of the creator class for each product type which contains an implementation of the factory method
    * to use the factory method(create objects). you simply specify an instance of that type and invoke the factory method

* the disadvantage of this approach is that every product has to subclass the creator class and implement its factory method

* product is the interface for the type of object that the Factory Method creates

* creator is the interface that defines the Factory Method
    * any other methods implemented here are written to operate on products produces by the factory method
    * the creator class is written without knowledge of the actual products taht will be created

* clients will need to subclass the Creator class to make a particular concrete product 
    * only subclasses actually implement the factory method and creates products

* the actual products that will be created is decided purely by the choice of the subclass that is used

``` java
public interface Shape {
    void draw();
}

class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method");
    }
}

class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}
```
* factory 
``` java
public abstract class AbstractShapeFactory {
    protected abstract Shape factoryMethod();

    public Shape getShape(){
        return factoryMethod();
    }

    // other helper methods
}

class RectangleFactory extends AbstractShapeFactory{
    @Override
    protected Shape factoryMethod() {
        return new Rectangle();
    }
}

class CircleFactory extends AbstractShapeFactory{
    @Override
    protected Shape factoryMethod() {
        return new Circle();
    }
}

class SquareFactory extends AbstractShapeFactory{
    @Override
    protected Shape factoryMethod() {
        return new Square();
    }
}
```
* client
``` java
public class Client {
    public static void main(String[] args) {
        //get an object of Circle and call its draw method
        Shape shape1 = new CircleFactory().getShape();
        shape1.draw();

        //get an object of Circle and call its draw method
        Shape shape2 = new RectangleFactory().getShape();
        shape2.draw();

        //get an object of Circle and call its draw method
        Shape shape3 = new SquareFactory().getShape();
        shape3.draw();
    }
}
```

**(b) Factory Method Implementation (Concrete Creator)**
* this implementation includes creating a single concrete creator class
    * the creator class is concrete class
    * you add implementation code to one factory method to create your product type bases on a parameter passed to the method
    * to use the factory method (create objects), you create an instance of the creator class and invoke the factory method with an argument for your "class type"

* the advantage of this approach is that you do not need to create a new subclass of the abstract creator class and implement a new factory method

Example 1
* factory
``` java
public class ShapeFactory {
    // use getShape method to get object
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        } else if (shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }

        return  null;
    }
}
```
client
``` java
public class Client {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        // get an object of Circle and call its draw method
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        // get an object of Circle and call its draw method
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // get an object of Circle and call its draw method
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
```

Exmaple 2
``` java
public interface AnimalInterface {
    void walk();
    void eat();
}

class Tiger implements AnimalInterface{
    @Override
    public void walk() {
        System.out.println("Tiger::walk() method");
    }

    @Override
    public void eat() {
        System.out.println("Tiger::eat() method");
    }
}

class Duck implements AnimalInterface{
    @Override
    public void walk() {
        System.out.println("Duck::walk() method");
    }

    @Override
    public void eat() {
        System.out.println("Duck::eat() method");
    }
}
```
factory
``` java
public abstract class AnimalFactoryInterface {
    public abstract AnimalInterface getAnimalType(String type) throws Exception;
}


class ConcreteFactory extends AnimalFactoryInterface {
    @Override
    public AnimalInterface getAnimalType(String animalType) throws Exception {

        switch (animalType){
            case "Duck":
                return new Duck();
            case "Tiger":
                return new Tiger();
            default:
                throw new Exception("Animal type: " + animalType + " cannot be instantiated");
        }
    }
}
```
client
``` java
public class Client {
    public static void main(String[] args) throws Exception{

        AnimalFactoryInterface animalFactory = new ConcreteFactory();

        AnimalInterface animal1 = animalFactory.getAnimalType("Duck");
        animal1.eat();
        animal1.walk();

        AnimalInterface animal2 = animalFactory.getAnimalType("Tiger");
        animal2.eat();
        animal2.walk();
    }
}
```

**(c) Factory Method Implementation (Static Method)**
* 3rd implementation of the factory method pattern includes the use of a static method
* define a factory as a static method is a common technique - often called a static factory
* this technique is sometimes used so that you do not need to instantiate an object to make use of the create method
* it has the disadvantage that you cannot subclass and change the behavior of the create method

factory
``` java
public class ShapeFactory {
    // use getShape method to get object
    public static Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        } else if (shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return  null;
    }
}
```
client
``` java
public class Client {
    public static void main(String[] args) {
        // get an object of Circle and call its draw method
        Shape shape1 = ShapeFactory.getShape("CIRCLE");
        shape1.draw();

        // get an object of Circle and call its draw method
        Shape shape2 = ShapeFactory.getShape("RECTANGLE");
        shape2.draw();

        // get an object of Circle and call its draw method
        Shape shape3 = ShapeFactory.getShape("SQUARE");
        shape3.draw();
    }
}
```
___

## **2. Abstract Factory**
**Overview**
* the abstract factory provides an interface for creating families of related or dependent objects without specifying their concrete classes
    * "factory of factories"
    * super factory that creates other factories

* a pattern that creates objects via abstraction (does not care how its products are created)

* the methods of an Abstract Factory are implemented as factory methods
    * provides an encapsulation mechanism to a group of individual factories
    * factory method is a subset of this pattern

* there is often one concrete class implemented for each family

**When to use it**
* when a system should be independent of how its products are created, compose, and represented

* when we need to deal with multiple factories

* when the problem domain has different families of objects present and each family is used under different circumstances

* when a family of related product objects is designed to be used together, and you need to enforce this constraint

* when you want to provide a class lib of products, and want to reveal just their interfaces, not their implementations


**Summary**
* all factories encapsulate object creation

* Factory Method relies on inheritance
    * object creation is delegated to subclasses, which implement the factory method to create objects

* Abstract Factory relies on object composition
    * object creation is implemented in methods exposed in the factory interface

* all factory patterns promote loose coupling by reducing the dependency of your application on concrete classes

* the intent of Factory Method is to allow a class to defer instantiation to its subclasses

* the intent of Abstract Factory is to create families of related objects without having to depend on their concrete classes

* objects - shape family
``` java
public interface Shape {
    void draw();
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Inside Rectangle::draw() method");
    }
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}
```
* object - color family
``` java
public interface Color {
    void fill();
}


class Red implements Color{
    public void fill() {
        System.out.println("Red:: fill() method");
    }
}

class Blue implements Color{
    public void fill() {
        System.out.println("Blue:: fill() method");
    }
}

class Green implements Color{
    public void fill() {
        System.out.println("Green:: fill() method");
    }
}
```
* object - factories
``` java
public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}

class ShapeFactory extends AbstractFactory{
    Shape getShape(String shapeType) {
        if(shapeType == null){
            return null;
        } else if (shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }

        return  null;
    }

    Color getColor(String color) {
        return null;
    }

}

class ColorFactory extends AbstractFactory{
    Color getColor(String colorType) {
        if(colorType == null){
            return null;
        } else if (colorType.equalsIgnoreCase("RED")){
            return new Red();
        } else if (colorType.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if (colorType.equalsIgnoreCase("BLUE")){
            return new Blue();
        }

        return  null;
    }

    Shape getShape(String shape) {
        return null;
    }
}
```
* factory of factory
``` java
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        }

        if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }

        return null;
    }
}
```
* client
``` java
public class Client {
    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("Shape");
        Shape shape1 = shapeFactory.getShape("Circle");
        shape1.draw();
        Shape shape2 = shapeFactory.getShape("Square");
        shape2.draw();
        Shape shape3 = shapeFactory.getShape("Rectangle");
        shape3.draw();


        AbstractFactory colorFactory = FactoryProducer.getFactory("Color");
        Color color1 = colorFactory.getColor("Red");
        color1.fill();
        Color color2 = colorFactory.getColor("Blue");
        color2.fill();
        Color color3 = colorFactory.getColor("Green");
        color3.fill();
    }
}
```
___
## **3. Singleton**
Singleton pattern is one of the simplest design patterns in java - it comes under creational pattern as this pattern provides one of the best ways to create an object

Ensures a class only has one instance, and provide a global point of access to it

We are taking class and letting it manage a single instance of itself
* also preventing any other class from creating a new instance on its own
* to get an instance, you have got to go through the class itself

We are also providing a global access point to the instance
* whenever you need an instance, just query the class and it will hand you back the single instance
* a global variable makes an object accessible, but it does not keep you from instantiating multiple objects

**Adv / Disadv of the singleton**
Controlled access to sole instance
* because the Singleton class encapsulates its sole instance, it can have strict control over how and when clients access it

Reduced name space
* an improvement over global variables
* avoids polluting the name space with global variables that store sole instances

Permits a variable number of instances
* makes it easy to change your mind and allow more than one instance of the Singleton class

Singletons hinder unit testing
* might cause issues for writing testable code if the object and the methods associated with it are so tightly coupled that it becomes impossible to test without writing a fully functional class dedicated to the Singleton

Singletons create hidden dependencies
* because it is readily available throughout the code base, it can be overused
* since its reference is not completely transparent while passing to different methods, it becomes difficult to track 

**Singleton vs Dependency Injection**
We know DI is a tech whereby one object supplies the dependencies of another object
* enables you to replace dependencies without changing the class that uses them

DI can also be used to avoid statics (one of the most common reasons to use it)

We know that singletons ensure only one instance of an object

Using DI, you can use constructor or setter injection to pass around a single object
* have the injector create a single object and then inject it via the constructor or setter of any dependent objects
* implements the singleton with less dependencies

...more

**Overview**
To implement the Singleton pattern, there are different approaches but all of them have the following common concepts:
* private constructor to restrict instantiation of the class from other class
* private static variable of the same class that is the only instance of the class
* public static method that returns the instance of the class - is the global access point for outer world to get the instance of the singleton class

**Approaches**
There are 5 main approaches when implementing the singleton pattern:

**(a) Lazy evaluation approach**
* It is not multi-thread safe
* use this approach if you are not worried about multiple threads
* this is not a recommended approach

Singleton class
``` java
public class Singleton {
    // the private reference to the one and only instance
    private static Singleton uniqueInstance = null;

    // an instance attribute
    private int data = 0;

    /**
    * The Singleton Constructor
    * Note that it is private!
    * No client can instantiate a singleton object!
    */
    private Singleton(){}

    public static Singleton getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
```
Client
``` java
public class TestSingleton {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();

        // set the data value
        s.setData(55);

        System.out.println("First reference: " + s);
        System.out.println("Singleton data value is: " + s.getData());

        // Get another reference to the singleton
        // Is it the same object?
        s = null;
        s = Singleton.getInstance();
        System.out.println("First reference: " + s);
        System.out.println("Singleton data value is: " + s.getData());

    }
}
```

**Problems with lazy initialization approach**
* The implementation is not thread safe
* Suppose two calls to getInstance() are made at virtually the same time
* the first thread checks to see whether the instance exists.  It does not, it goes into the part of the code that will create the new instance 
* however, before it has done that, suppose a second thread also looks to see whether the instance member is null - because the first thread has not created anything yet, the instance is still equal to null, so the second thread also goes into the code that will create and object
* both threads now perform a new on the Singleton object, thereby creating two objects
* if the Singleton is absolutely stateless, then thread safety may not be a problem
* if the Singleton has state, and if you expect that when one object changes the state, all other objects should see the change, then this could become a serious problem - the first thread will be interacting with a different object than all other threads do
* inconsistent state between threads using the different Singleton objects
* if the object creates a connection, there will actually be two connections (one for each object)
* if a counter is used, there will be two counters
* it may be very difficult to find these problems:
> dual creation is very intermittent - it usually won't happen
> it may not be obvious why the counts are off, because only one client object will contain one of the Singleton objects while all the other client objects will refer to the other Singleton



**(b) Synchronized Approach**
* thread safe
* use when performance is not critical to you application, but it is multi-threaded
* straightforward and effective

Just 'synchronized' the getInstance() method
``` java
public class Singleton {
    // the private reference to the one and only instance
    private static Singleton uniqueInstance = null;

    // an instance attribute
    private int data = 0;

    /**
    * The Singleton Constructor
    * Note that it is private!
    * No client can instantiate a singleton object!
    */
    private Singleton(){}

    // by adding the synchronized keyword to getInstance
    // we force every thread to wait its turn before it can enter the method
    public static synchronized Singleton getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
```

**(c) double-checked locking principle approach**
* thread safe
* increases performance from the synchronized approach


**(d) Eager evaluation Approach**
* if your application always creates and uses an instance of the Singleton
* does not use a lot of resources
* thread safe
* the instance is created even though client application might not be using it


**(e) Bill Pugh Approach**
* thread safe
* high performance
* ensures that the instance is only created if a client needs it
* create the Singleton class using a inner static helper class
* regarded as the standard method to implement singletons in Java



___
## **4. Builder**


___
## **5. Prototype**
