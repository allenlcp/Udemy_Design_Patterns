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

**Problem with synchronized approach**
One big problem is that synchronization may end up being a sever bottleneck
* all the threads will have to wait for the check on whether the object already exists
* reduces the performance because of cost associated with the synchronization method

The only time sync is relevant is the first time through this method
* once we have set the uniqueInstance variable to an instance of Singleton, we have no further need to synchronize this method
* after the first time through, synchronization is totally uneeded overhead

For most Java app, we need to ensure that the Singleton works in the presence of multiple threads and does not have performance issues
* use the double checked locking principle


**(c) double-checked locking principle approach**
* thread safe
* increases performance from the synchronized approach

* this approach will use a synchronized block inside the if condition with an additional check to ensure that only one instance of the singleton class is created
> intent is to optimize away unnecessary locking, increase performance
> the synchronization check happens at most one time, so it will not be a bottleneck

* use "double-checked locking" to reduce the use of synchronization in getInstance()

* with double=checked locking, we first check to see if an instance is created, and if not, THEN we synchronize
> we only synchronize the first time through, just what we want

``` java
public class Singleton {
    // the private reference to the one and only instance
    private volatile static Singleton uniqueInstance = null;

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
    public static Singleton getInstance(){
        if (uniqueInstance == null){
            synchronized (Singleton.class) { 
                if (uniqueInstance == null){
                    uniqueInstance = new Singleton();
                }
            }
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

**(d) Eager evaluation Approach**
* if your application always creates and uses an instance of the Singleton
* does not use a lot of resources
* thread safe
* the instance is created even though client application might not be using it

* in eager initialization, the instance of Singleton Class is created at the time of class loading
> the easiest method to create a singleton class
> it has a drawback that the instance is created even though client application might not be using it

* using this approach, we rely on the JVM to create the unique instance of the Singleton when the class is loaded
> the JVM guarantees that the instance will be created before any thread accesses that static uniqueInstance variable (threadsafe)

``` java
public class Singleton{
    private static Singleton uniqueInstance = new Singleton();

    private Singleton(){}

    public static Singleton getInsntance(){
        // we already got an instance so just return it
        return uniqueInstance;
    }
}
```

**(e) Bill Pugh Approach**
* thread safe
* high performance
* ensures that the instance is only created if a client needs it
* create the Singleton class using a inner static helper class
* regarded as the standard method to implement singletons in Java

``` java
public class Singleton {

    // an instance attribute
    private int data = 0;

    /**
    * The Singleton Constructor
    * Note that it is private!
    * No client can instantiate a singleton object!
    */
    private Singleton(){}

    private static class SingletonHelper{
        // Nested class is reference after getInstance() is called
        // the private reference to the one and only instance
        private static final Singleton uniqueInstance = new Singleton();

    }

    public static Singleton getInstance(){
        return SingletonHelper.uniqueInstance;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
```

___
## **4. Builder**
The builder design pattern separates the construction of a complex object from its representation
* uses the same construction processes to create the same object - however, these processes can create different representations of the object
* uses simple objects and a step by step approach to create the object
* the builder class is independent of the other objects

Useful when creating the object is very complex and is independent of the assembly of the parts of the object

An example would be creating your own computer
* different parts are assembled depending upon the order received by the customer
> a customer can demand a 500 GB hard disk with an Intel processor
> another customer can choose a 250 GB hard disk with an AMD processor

Building a vacation planner for Disney World
* guests can choose a hotel and various types of admission tickets
* make restaurant reservations, and even book special events

You need a flexible design
* each guest's planner can vary in the number of days and types of activities it includes
* a local resident might not need a hotel, but wants to make dinner and special event reservations
* another guest might by flying into Orlando and needs, a hotel , dinner reservations and admission tickets

We encapsulate the creation of the trip planner in an object (a builder)
* have our client ask the builder to construct the trip planner structure for it

You need a flexible data structure that can represent guest planners and all their variations

You also need a follow a sequence of potentially complex steps to create the planner

The builder design pattern can provide a way to create the complex structure without mixing it with the steps for creating it

**Why the Builder Pattern**
This pattern was introduced to solve problems with the Factory and Abstract Factory design patterns
* these pattern do not work well when the Object to be created contains a lot of attributes

There are 3 major issues
* too many arguments to pass from the client to the Factory class
> can be error prone
> its hard to maintain the order of the arguments on the client side

* some of the parameters might be optional
> in the factory pattern we are forced to send all the parameters - optional parameters need to be sent as NULL

* if the object is heavy and its creation is complex
> all the complexity will be part of factory classes which can cause major confusion

One way to solve the optional parameters problem
* provide a constructor with required parameters and then different setter methods to set the optional parameters - however, the Object state will be inconsistent until/unless all the attributes are set explicitly

A better approach is to use the Builder pattern
* provides a way to build the object step-by-step
* provides a method that will actually return the final complex object

**Advantages**
It encapsulates the way a complex object is constructed
* separates the code of assembling from its representation
* hides the complex construction process and represents it as simple process

Allows objects to be constructed in a multistep and varying process (as opposed to one-step factories)

Hides the internal representation of the product from the client

Product implementations can be swapped in and out because the client only sees an abstract interface

Focuses on "how the product will be made"

**Disadvantages**
Often used for building composite structures

Constructing objects requires more domain knowledge of the client than when using a Factory

Requires some amount of code duplication

**Summary**
Use the Builder pattern when
* the algorithm for creating a complex object should be independent of the parts that make the object and how they are assembled
* the construction process must allow different representations for the object that is constructed

We should not use this pattern if we want a mutable object
* an object which can be modified after the creational process is over


**Implementation Overview**
The main participants when implementing the builder pattern are the following

Builder
* specifies an abstract interface for creating parts of a Product object
* defines an operation for each component that a director may ask it to create
* must be general enough to allow the construction of products for all kinds of concrete builders


ConcreteBuilder
* constructs and assembles parts of the product by implementing the Builder interface - overrides operations for components it is interested in creating
* defines and keeps track of the representation it creates
> usually appended to the product via some type of list
> sometimes you might need access to parts of the product constructed earlier - a builder would return child nodes to the director, which then would pass them back to the builder to build the parent nodes
* provides an interface for retrieving the product (GetProduct())


Director
* constructs an object using the Builder interface

Product
* represents the complex object under construction
* ConcreteBuilder builds the product's internal representation and defines the process by which it is assembled
* includes classes that define the constituent parts, including interfaces for assembling the parts into the final result


The client creates the Director object - configures it with the desired Builder object

The Director notifies the builder whenever a part of the product should be built

The Builder handles requests from the director and adds parts to the product 

The client retrieves the product from the builder

Builder
``` java
public interface BuilderInterface {
    void buildBody();
    void insertWheels();
    void addHeadlights();
    Product getVehicle();
}
```

ConcreteBuilder
``` java
class Car implements BuilderInterface{
    private Product product = new Product();

    @Override
    public void buildBody() {
        product.add("This is a body of a car");
    }

    @Override
    public void insertWheels() {
        product.add("4 wheels are added");
    }

    @Override
    public void addHeadlights() {
        product.add("2 headlights are added");
    }

    @Override
    public Product getVehicle() {
        return product;
    }
}

class MotoCycle implements BuilderInterface{
    private Product product = new Product();

    @Override
    public void buildBody() {
        product.add("This is a body of a motocycle");
    }

    @Override
    public void insertWheels() {
        product.add("2 wheels are added");
    }

    @Override
    public void addHeadlights() {
        product.add("1 headlights are added");
    }

    @Override
    public Product getVehicle() {
        return product;
    }
}
```

Product
``` java
public class Product {
    private LinkedList<String> parts;

    public Product() {
        this.parts = new LinkedList<>();
    }

    public void add(String part){
        parts.addLast(part);
    }

    public void show(){
        System.out.println("\nProduct completed as below");
        for(String s : parts){
            System.out.println(s);
        }
    }
}
```

Director
``` java
public class Director {
    BuilderInterface myBuilder;

    public void construct(BuilderInterface builder){
        myBuilder = builder;

        myBuilder.buildBody();
        myBuilder.insertWheels();
        myBuilder.addHeadlights();
    }
}
```

Client
``` java
public class Client {
    public static void main(String[] args) {
        System.out.println("***Builder Pattern***\n");

        Director director = new Director();

        BuilderInterface carBuilder = new Car();
        BuilderInterface motorBuilder = new MotoCycle();

        // making a car
        director.construct(carBuilder);
        Product p1 = carBuilder.getVehicle();
        p1.show();

        // making a motocycle
        director.construct(motorBuilder);
        Product p2 = motorBuilder.getVehicle();
        p2.show();

    }
}
```
___

## **5. Prototype**
Prototype pattern refers to creating a duplicate object while keeping performance in mind
* specifies the kinds of objects to create using a prototypical instance, and creates new objects by copying this prototype

Used when creation of an object is costly, requires a lot of time and resources and you have a similar object already existing
* creating a new instance is normally treated as an expensive operation
* focus here is to reduce the expense of this creational process of a new instance

Provides a mechanism to copy the original object to a new object and then modify it according to our needs 
* uses java cloning to copy the object (shallow) or de serialization when you need deep copies

A key aspect of this pattern is that the client code can make new instances without knowing which specific class is being instantiated

Mandates that the Object which you are copying should provide the copying feature
* should not be done by any other class
* whether to perform a shallow or deep copy of the Object depends on the requirements and design

**When should we use a prototype**
When a system should be independent of how its products are created, composed, and represented
* does not care about the creational mechanism of the products

We can use this pattern when we need to instantiate classes at runtime - dynamic loading

When a system must create new objects of many types in a complex class hierarchy
* you want to build a class hierarchy of factories that parallels the class hierarchy of products

When instances of a class can have one of only a few different combinations of state
* may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state

**Advantages**
Hides the complexities of making new instances from the client

Provides the option for the client to generate objects whose type is not known

In some circumstances, copying an object can be more efficient than creating a new object

We can include or discard products at runtime

We can create new instances with a cheaper cost

**Disadvantages**
Each subclass has to implement the cloning mechanism

Implementing the cloning mechanism can be challenging
* if the objects under consideration do not support copying
* if there is any kind of circular reference

The Java cloneable interface has some problems

**Implementation Overview**
When implementing the prototype pattern the following participants are included

**Prototype** - declares an interface for cloning itself

**ConcretePrototype** - implements an operation for cloning itself

**Client** - creates a new object by asking a prototype to clone itself


Prototype
``` java
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
```

Concrete Prototype
``` java

class Rectangle extends Shape {
    public Rectangle() {
        type = "Rectangle";
    }

    public void draw() {
        System.out.println("Inside Rectangle::draw() method");
    }
}

class Square extends Shape {
    public Square() {
        type = "Square";
    }

    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}

class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }

    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}
```

``` java
public class ShapeCache {

    private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId){
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);


        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);


        Rectangle rectangle  = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
```

Client
``` java
public class Client {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape cloneShape1 = (Shape) ShapeCache.getShape("1");
        System.out.println(cloneShape1.getType());
        Shape cloneShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println(cloneShape2.getType());
        Shape cloneShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println(cloneShape3.getType());
    }
}
```

**Overview**
Prototype design pattern involves implementing the cloneable interface

Java provides a mechanism for cloning of objects that is very easy to implement

Your first need to implement the Cloenable interface

You then need to define a clone() method that should handle CloneNotSupportedException
* returns a shallow copy of the object - a shallow copy means if the copied object contains references to other objects, these objects are not cloned
* a deep copy would clone even referenced objects

Lastly, we call the clone() method of the superclass

``` java
class Person implements Cloneable{ // Step 1
    private String name;
    private City city; // deep copy

    // no @override means we are not overriding clone
    public person clone() throws CloneNotSupportedException { // Step 2
        Person clonedObj = (Person) super.clone(); // Step 3
        clonedObj.city = this.city.clone(); // Making deep copy of city
        return clonedObj;
    }
}
```

**Problems with the Cloneable interface**
Some academics think that cloning is deeply broken in Java

The Cloneable interface lacks the clone() method
* Cloneable is a marker interface and does not have any methods in it
* We still need to implement it just to tell the JVM that we can perform clone() on our object

Object.clone() is protected
* We have to provide our own clone() and indirectly call Object.clone() from it

We do not have any control over object construction because Object.clone() does not invoke any constructor
* there are no guarantees that it preserves the invariants established by the constructors

If we are writing a clone method in a child class then all of its superclasses should refine the clone() method - otherwise, the super.clone() chain will fail

Object.clone() supports only shallow copying
* does not clone the reference fields of the object to be cloned
* we need to implement clone() in every class whose reference our class is holding 
* then call their clone separately in our clone () method

We can not manipulate final fields in Object.clone()
* final fields can only be changed through constructors
* if we want every object to be unique by including an id, we will get the duplicate object
* if we use Object.clone() = will not call the constructor, and final id field can not be modified form invoking the clone() method

You can not do a polymorphic clone operation
* if I have an array of Cloneable, you would think that I could run down that array and clone every element to make a deep copy of the array
* this does not work, you cannot cast something to Cloneable and call the clone method
* Cloneable does not have a public clone method and neither does Object
* If you try to cast to Cloneable and call the clone method, the compiler will say you are trying to call the protected clone method on object

The clone generally shares state with the object being cloned
* If that state is mutable, you don not have two independent objects
* if you modify one, the other changes as well and all of a sudden, you get random behavior

Cloneable is a weak spot, and you should be aware of its limitations


**Alternatives to using Clonable (Copy Constructor)**
One option to provide copy functionality is to provide a copy constructor(s)
* like a regular constructor, which returns a new instance of the class
* as an input, it has an object, which is supposed to be copied
* inside the body of the constructor, you implement your custom cloning logic


This method of copying objects is one of the most popular among the developer community
* overcomes every design issue of Object.clone()
* provides better control over object construction

``` java
public Person(Person original){
    this.id = original.id + 1;
    this.name = new String(original.name);
    this.city = new City(original.city);
}
```


**Advantages of Copy Constructors vs clone()**
Does not force us to implement any interface or throw exception

Does not require any casts

Does not require us to depend on an unknown object creation mechanism

Does not require parent classes to follow any contract or implement anything

Allows us to modify final fields

Allows us to have complete control over object creation - we can write our own initialization login in it

We can also create conversion constructors - allow us to convert one object to another object


**Alternatives to using Clonable (Serialization)**
Another way to copy an object is to use a serialize/deserialize approach - instead of cloning, you can serialize an object and then immediately deserialize it - would result in a new instance created

We will still not be able to modify the final fields

We still do not have any control on object construction

We still need to implement Serialize which is similar to Cloneable

The serialization process is slower than Object.clone()

``` java
public Person copy(Person original){
    try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
    ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"))) {
        out.writeObject(original);
        return (Person) in.readObject();
    } catch (Exception e){
        throw new RuntimeException(e);
    }
}
```

**Advantages of cloning using serialization**
Simple alternative to cloning - especially when using library such as Apache Commons

Provides deep cloning

Suitable even for complex object graphs

Can be used on existing classes that currently provide just shallow copy

___

## **Structural Design Pattern**
Describes how classes and objects can be combined to form larger structures
* utilizes inheritance to compose interfaces or implementations
* structural object patterns describe ways to assemble objects 
* e.g complex user interfaces and accounting data

These design patterns concern class and object composition

The composite design pattern - describes how to build a class hierarchy made up of classes for two kinds of objects

The proxy design pattern acts as a convenient surrogate or placeholder for another object - provide a level of indirection to specific properties of objects

**Class Patterns vs Object Patterns (sub category)**
Class patterns describe how relationships between classes are defined
* use inheritance to compose interfaces or implementations
* relationships are established at compile time
* adapter

Object patterns describe relationships between objects
* describe ways to compose objects to realize new functionality 
* use composition
* relationships are typically created at runtime - more dynamic and flexible
* bridge, composite, decorator, facade, flyweight and proxy patterns


**7 Structural Patterns covered below**
* Adapter Pattern
* Bridge Pattern
* Composite Pattern
* Decorator Pattern
* Facade Pattern
* Flyweight Pattern
* Proxy Pattern

**(a) Adapter Design Pattern**
The adapter design pattern converts an interface of a class into another interface that clients expect
* works as a bridge between two incompatible interfaces = "adapter" does the conversion
* lets classes work together that could not otherwise
* also known as a "Wrapper"

Come under the structural pattern classification as this pattern combines the capability of two independent interfaces

The adapter acts to decouple the client form the implemented interface
* encapsulates any future changes
* client does not need to be modified each time it needs to operate against a different interface

Full of good OO design principles
* use of object composition to wrap the adaptee with an altered interface - can use an adapter with any subclass of the adaptee
* binds the client to an interface, not an implementation


**When to use the Adapter Pattern**
When you want to use an existing class, and its interface does not match the one you need

When you want to create a reusable class that cooperates with unrelated or unforseen classes - that do not necessarily have compatible interfaces

When you need to use several existing subclasses, but it is impractical to adapt their interface by sub-classing every one
* an object adapter can adapt the interface of its parent class


**Participants**
**Target** - defines the domain specific interface that Client uses
**Client** - collaborates with objects conforming to the Target interface
**Adaptee** - defines an existing interface that needs adapting
**Adapter** - adapts the interface of Adaptee to the Target interface, involves a single class which is responsible to join functionalities of independent or incompatible interfaces

**Adapter(Object) using composition - Example 1**
Target
``` java
public interface Duck {
    void quack();
    void fly();
}

class MallardDuck implements Duck{
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I am flying");
    }
}
```
Adaptee
``` java
public interface Turkey {
    void gobble();
    void fly();
}

class WildTurkey implements Turkey{
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I am flying a short distance");
    }
}
```

Adapter
``` java
public class TurkeyAdapter implements Duck {
    Turkey turkey; // adaptee

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for(int i=0; i<5 ;i++) {
            turkey.fly();
        }
    }
}
```
Client
``` java
public class Client {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();

        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        System.out.println("The turkey says....");
        turkey.gobble();
        turkey.fly();

        System.out.println("\nThe duck says");
        testDuck(duck);

        System.out.println("\nThe TurkeyAdapter says");
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck){
        duck.quack();
        duck.fly();
    }
}
```
``` text
The turkey says....
Gobble gobble
I am flying a short distance

The duck says
Quack
I am flying

The TurkeyAdapter says
Gobble gobble
I am flying a short distance
I am flying a short distance
I am flying a short distance
I am flying a short distance
I am flying a short distance
```


**Adapter(Object) using composition - Example 2**
``` java
public class Rectangle {
    public double length;
    public double width;
}

public class Triangle {
    public double base;
    public double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
}
```

``` java
public interface CalculatorInterface {
    // target interface
    double getArea(Rectangle r);
}

class Calculator implements CalculatorInterface {
    Rectangle rectangle;

    @Override
    public double getArea(Rectangle r) {
        rectangle = r;
        return rectangle.length * rectangle.width;
    }
}
```

Adapter
``` java
public class CalculatorAdapter implements CalculatorInterface {
    Calculator calculator;
    Triangle triangle;

    public CalculatorAdapter(Triangle triangle) {
        this.triangle = triangle;
    }

    @Override
    public double getArea(Rectangle r) {
        calculator = new Calculator();
        Rectangle rectangle = new Rectangle();

        rectangle.length = triangle.base;
        rectangle.width = 0.5 * triangle.height;

        return calculator.getArea(rectangle);
    }
}
```

Client
``` java
public class Client {
    public static void main(String[] args) {
        System.out.println("**Adapter Pattern**");

        Triangle t = new Triangle(20, 10);
        CalculatorInterface calculatorInterface = new CalculatorAdapter(t);

        System.out.println("Aread of Triangle: " + calculatorInterface.getArea(null));
    }
}
```

**Class Adapter Implementation**
``` java
public interface IntegerValueInterface {
    int getInteger();
}

class IntegerValue implements IntegerValueInterface{
    @Override
    public int getInteger() {
        return 5;
    }
}

class ClassAdapter extends IntegerValue {
    @Override
    public int getInteger() {
        return 2 + super.getInteger();
    }
}

class ObjectAdapter {
    private IntegerValueInterface myInt;

    public ObjectAdapter(IntegerValueInterface myInt) {
        this.myInt = myInt;
    }

    public int getInteger(){
        return 2 + this.myInt.getInteger();
    }
}
```
Client
``` java
public class Client {
    public static void main(String[] args) {

        System.out.println("Class and Object Adapter Demo");
        ClassAdapter cal = new ClassAdapter();
        System.out.println("Class Adapter is returning: " + cal.getInteger());

        ObjectAdapter oa = new ObjectAdapter(new IntegerValue());
        System.out.println("Object Adapter is returning: " + oa.getInteger());
    }
}
```

**(b) Bridge Design Pattern**

**(c) Composite Design Pattern**

**(d) Decorator Design Pattern**

**(e) Facade Design Pattern**

**(f) Flyweight Design Pattern**

**(g) Proxy Design Pattern**


___