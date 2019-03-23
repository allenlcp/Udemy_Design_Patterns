## **0. Overview**
23 design patterns 
* Divided into 3 types (gang of four) - organised by purpose (reflects what a pattern does)
    * Creational - concern the process of object creation
    * Structural - deal with the composition of classes or objects
    * Behavioral - characterize the ways in which classes or objects interact and distribute responsibility

* Design patterns can also be organised by scope (whether the pattern applies primarily to classes or to objects)
    * class patterns deal with relationships between classes and their subclasses - these relationships are established through inheritance
    * object patterns deal with object relationships, which can be changed at run-time and are more dynamic - describes how objects can be composed into larger structure using object composition or by including objects within other objects


## **0.1 Software Design Principles**

**0.1.1 Programming to an interface**
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
**0.1.2 Abstract Classes vs Interfaces**
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
**0.1.3 Composition vs Inheritance**

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
**0.1.4 Delegation Principles**
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

## **1.1 Single Responsibility**
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

## **1.2 Open Closed Principle**

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

## **1.3 Liskov Substition Principle**
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

## **1.4 Interface Segregation Principle**
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

## **1.5 Dependency Inversion**
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

## **1.5 Dependency Injection**

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

## **2. Creational Design Patterns**
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

## **2.1 Factory**
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

## **2.2 Abstract Factory**
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
## **2.3 Singleton**
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
## **2.4 Builder**
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

## **2.5 Prototype**
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

## **3. Structural Design Pattern**
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

## **3.1 Adapter Design Pattern**
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
___

## **3.2 Bridge Design Pattern**
The bridge pattern will decouple an abstraction from its implementation so that the two can vary independently
* decouples implementation class and abstract class by providing a bridge structure between them

We already understand the benefits of decoupling and abstraction
* decoupling means to have things behave independently from each other
* abstraction is how different things are related to each other conceptually (hiding details)

Implementations here mean the objects that the abstract class and its derivations use to implement themselves - not the derivations of the abstract class (concrete classes)

This pattern help us to make concrete class functionalities independent from the interface implementer class - can alter these different kind of classes structurally without affecting each other

**When to use the Bridge pattern**
When you want to avoid a permanent binding between an abstraction and its implementation
* when the implementation must be selected or switched at run-time

When both the abstractions and their implementations should be extensible by subclassing 
* lets you combine the different abstraction and implementations and extend them independently

When changes in the implementation of an abstraction should have no impact on clients 
* clients code should not have to be recompiled

When you want to hide the implementation of an abstraction completely from clients

When you have a ton of implementation classes
* a class hierarchy indicates the need for splitting an object into two parts


**Advantages**
Decouples an implementation so that it is not bound permanently to an interface

Abstraction and implementation can be extended independently
* allows you to vary the implementation and the abstraction by placing the two in separate class hierarchies

Changes to the concrete abstraction classes do not affect the client

Adds one more method level redirection to achieve the objective

One **drawback** is that id does slightly increase complexity

**Compared to the Adapter**
The Adapter patter is geared toward making unrelated classes work together - usually applied to systems after they have been designed

In contrast, the Bridge is used up-front in a design - lets abstractions and implementations vary independently


**Implementation Overview**
There are 2 parts in the Bridge design pattern implementation
* abstraction - an interface or an abstract class
* implementation - an interface or abstract class

Allows the abstraction and the implementation to be developed independently
* the client code can access only the abstraction part
* client not concerned about the implementation part

The abstraction contains a reference to the implementer

Children of the abstraction are referred to as refined abstractions

Children of the implementer are concrete implementers

Since we can change the reference to the the implementer in the abstraction, we are able to change the abstraction's implementer at run-time

Changes to the implementer do not affect client code
* increases the loose coupling between class abstraction and its implementation

Implementer
``` java
public abstract class Workshop {
    abstract void work();
}

// Concrete implementers
class Produce extends  Workshop{
    @Override
    void work() {
        System.out.println("Produced");
    }
}

// Concrete implementers
class Assemble extends  Workshop{
    @Override
    void work() {
        System.out.println("And Assembled");
    }
}
```
Abstraction
``` java
public abstract class Vehicle {
    protected Workshop workshop1; // Ref to implementor
    protected Workshop workshop2; // Ref to implementor

    public Vehicle(Workshop workshop1, Workshop workshop2) {
        this.workshop1 = workshop1;
        this.workshop2 = workshop2;
    }

    abstract void manufacture();
}

// Refined abstraction
class Car extends Vehicle{
    public Car(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    void manufacture() {
        System.out.println("Car ");
        workshop1.work();
        workshop2.work();
    }
}

// Refined abstraction
class Bike extends Vehicle{
    public Bike(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    void manufacture() {
        System.out.println("Bike ");
        workshop1.work();
        workshop2.work();
    }
}
```
Client
``` java
public class Client {
    public static void main(String[] args) {
        Vehicle vehicle1 = new Car(new Produce(), new Assemble());
        vehicle1.manufacture();

        Vehicle vehicle2 = new Bike(new Produce(), new Assemble());
        vehicle2.manufacture();

    }
}
```

**(c) Composite Design Pattern**
The composite design pattern composes objects into tree structures to represent part-whole hierarchies
* lets clients treat individual objects and compositions of objects uniformly

A composite is an object designed as composition of one-or-more similar objects that all exhibit similar functionality
* i.e a group of objects that is treated the same way as a single instance of the same type of object

When we have many objects with common functionalities we create a composite object 
* creates a class that contains a group of its own objects

**Participants**
**Component** 
* declares the interface for objects in the composition
* implements default behavior for the interface common to all classes
* declares an interface for accessing and managing its child components

**Leaf** 
* represents leaf objects in the composition - a leaf has no children
* defines behavior for primitive objects in the composition

**Composite** 
* defines behavior for components having children (add, remove, etc)
* stores child components (some data structure, list)
* implements child-related operations in the Component interface

**Client**
* Manipulate objects in the composition through the Component interface (does the grouping) 

Component
``` java
public interface Employee {
    void showEmployeeDetails();
}
```

Leaf
``` java
class Developer implements Employee{
    private String name;
    private long empId;
    private String position;

    public Developer(String name, long empId, String position) {
        this.name = name;
        this.empId = empId;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(empId + " - " + name + " - " + position);
    }

}

class Manager implements Employee{
    private String name;
    private long empId;
    private String position;

    public Manager(String name, long empId, String position) {
        this.name = name;
        this.empId = empId;
        this.position = position;
    }

    @Override
    public void showEmployeeDetails() {
        System.out.println(empId + " - " + name + " - " + position);
    }

}
```

Composite
``` java
public class Directory implements Employee {
    private List<Employee> employeeList = new ArrayList<>();

    @Override
    public void showEmployeeDetails() {
        for(Employee emp: employeeList){
            emp.showEmployeeDetails();
        }
    }

    public void addEmployee(Employee emp){
        employeeList.add(emp);
    }

    public void removeEmployee(Employee emp){
        employeeList.remove(emp);
    }
}
```

Client
``` java
public class Company {
    public static void main(String[] args) {
        Employee dev1 = new Developer("Josh", 100, "Pro developer");
        Employee dev2 = new Developer("Jane", 101, "Jnr developer");

        Directory engDirectory = new Directory();
        engDirectory.addEmployee(dev1);
        engDirectory.addEmployee(dev2);

        Employee man1 = new Manager("Jen", 200, "SEO Manager");
        Employee man2 = new Manager("David", 201, "Linux Manager");

        Directory accDirectory = new Directory();
        accDirectory.addEmployee(man1);
        accDirectory.addEmployee(man2);

        Directory companyDirectory = new Directory();
        companyDirectory.addEmployee(engDirectory);
        companyDirectory.addEmployee(accDirectory);
        companyDirectory.showEmployeeDetails();
    }
}
```
___

## **3.3 Decorator Design Pattern**
The decorator pattern will allow you to attach additional responsibilities to an object dynamically 
* allows a user to add new functionality to an existing object without altering its structure

Decorators provide a flexible alternative to sub-classing for extending functionality

The main principle of this pattern says that we cannot modify existing functionalities but we can extend them - open for extension by closed for modification

The core concept applies when we want to add some specific functionalities to some specific object instead of to the whole class

Decorator is used to modify the functionality of an object at runtime
* Other instance of the same class will not be affected by this, so individual object gets the modified behavior

The decorator pattern will create a set of decorator classes that are used to wrap concrete components - provides additional functionality keeping class methods signature intact

Decorator classes minor the type of the components they decorate - they are the same type as the components they decorate

Decorators change the behavior of their components by adding new functionality before and/or after method calls to the component

You can wrap a component with any number of decorators

Decorators are typically transparent to the client of the component - unless the client is relying on the component's concrete type

**Participants**
**Components**
* defines the interface for objects
* can have responsibilities added to them dynamical

**Concrete Components**
* defines an object to which additional responsibilities can be attached

**Decorator**
* maintains a reference to a Component object
* defines an interface that conforms to Component's interface

**Concrete Decorator**
* adds responsibilities to the component

The decorator forwards requests to its Component object
* may optionally perform additional operations before and after forwarding the request

**Example 1**
``` java
public abstract class Component {
    public abstract void doJob();
}

class ConcreteComponent extends Component{
    @Override
    public void doJob() {
        System.out.println("I am from a Concrete Component - I am closed for modification");
    }
}
```
``` java
public abstract class AbstractDecorator extends Component{
    protected Component com;

    public void setTheComponent(Component c){
        com = c;
    }

    @Override
    public void doJob() {
        if(com != null){
            com.doJob();
        }
    }
}

class ConcreteDecorator1 extends AbstractDecorator {
    @Override
    public void doJob() {
        super.doJob();
        System.out.println("I am explicitly from Example_1");
    }
}

class ConcreteDecorator2 extends AbstractDecorator {
    @Override
    public void doJob() {
        System.out.println("");
        System.out.println("**Start Ex-2**");
        super.doJob();
        System.out.println("Explicitly from Example_2");
        System.out.println("** End, Ex_2**");
    }
}
```

``` java
public class Client {
    public static void main(String[] args) {
        ConcreteComponent cc = new ConcreteComponent();

        ConcreteDecorator1 cd1 = new ConcreteDecorator1();
        ConcreteDecorator2 cd2 = new ConcreteDecorator2();

        cd1.setTheComponent(cc);
        cd1.doJob();

        cd2.setTheComponent(cd1);
        cd2.doJob();
    }
}
```
``` text
I am from a Concrete Component - I am closed for modification
I am explicitly from Example_1

**Start Ex-2**
I am from a Concrete Component - I am closed for modification
I am explicitly from Example_1
Explicitly from Example_2
** End, Ex_2**
```

**Example 2**
``` java
public class LowerCaseInputStream extends FilterInputStream {
    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException{
        int c = in.read();
        return (c==-1 ? c : Character.toLowerCase((char)c));
    }

    public int read(byte[] b, int offset, int len) throws IOException{
        int result = in.read(b, offset, len);
        for(int i=offset; i<offset+result ;i++){
            b[i] = (byte) Character.toLowerCase((char)b[i]);
        }
        return result;
    }
}
```
``` java
public class Client {
    public static void main(String[] args) {
        int c;

        StringBuffer stringBuffer = new StringBuffer("Jason Freddie aaaBBBcccDDDeFF");
        byte[] bytes = stringBuffer.toString().getBytes();

        try(InputStream in = new LowerCaseInputStream(new ByteArrayInputStream(bytes))){
            while((c = in.read()) >= 0){
                System.out.print((char)c);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
```

**Example 3**
``` java
public interface Shape {
    void draw();
}


class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}

class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
```
Decorator
``` java
public abstract class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}

class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    public void setRedBorder(Shape decoratedShape){
        System.out.println("Border color: Red");
    }
}
```
Client
``` java
public class Client {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        circle.draw();
        redCircle.draw();
        redRectangle.draw();
    }
}
```
``` text
Circle::draw()
Circle::draw()
Border color: Red
Rectangle::draw()
Border color: Red
```

## **3.4 Facade Design Pattern**
The facade design pattern provides a unified interface to a set of interfaces in a system
* defines a higher-level interface that makes teh subsystem easier to use - hide the complexities of the subsystem interfaces
* does not add any functionality

As the name suggests, it means the face of the building
* people walking past the road can only see this glass face of the building
* they do not know anything about it, the wiring, the pipes and other complexities
* hides all the complexities of the building and displays a friendly face

The pattern is basically saying that we need to interact with a system that is easier than the current method, or we need to use the system in a particular way - such as using a 3D drawing program in a 2D way

Facade design pattern is more like a helpder for client applications - does not hide subsystem interfaces from the client

Whether to use Facade or not is completely dependent on client code

Can be applied at any point of development, usually when the number of interfaces grow ans system gets complex

Subsystem interfaces are not aware of Facade and they should not have any reference to the Facade interface

**Why the facade?**
Subsystems are groups of classes, or group of classes and other subsystems

Structuring a system into subsystems help reduce complexity

The interface exposed by the classes in a subsystem or set of subsystems can become quite complex

One way to reduce this complexity is to introduce a facade object
* provides a single, simplified interface to the more general facilities of a subsystem

If you need the power of the complex subsystem, it is still there for you to use

**Advantages**
Shields clients from subsystem components
* reduces the number of objects that clients deal with
* makes the subsystem easier to use

The pattern supports loose coupling
* we emphasize the abstraction and hide the complex details by exposing a simple interface
* decouples a client form a subsystem of components

Facades help layer a system and the dependencies between objects
* can eliminate complex or circular dependencies

Reduces compilation dependencies in large software systems

Simplifies porting systems to other platforms

Does not prevent applications from using subsystem classes if they need to - can choose between ease of use and generality

**When to use the facade**
When you want to provide a simple interface to a complex subsystem

When there are many dependencies between clients and the implementation classes of an abstraction
* introdice a facade to decouple the subsystem from clients and other subsystems
* promotes subsystem independence and portability

When you want to layer you subsystems
* use a facade to define an entry point to each subsystem level


**Facade vs Adapter**
When you need to use an existing class and its interface is not the one you need, use an adapter

When you need to simplify and unity a large interface or complex set of interfaces, use a facade

An adapter changes an interface into one a client expects

A facade decouples a client from a complex subsystem

An adapter wraps an object to change its interface
A decorator wraps an object to add new behaviours and responbilities
A facade wraps a set of objects to simplify


Facade
``` java
public class HomeTheaterFacade {
    Amplifier amp;
    Tuner tuner;
    DvdPlayer dvd;
    CdPlayer cd;
    Projector projector;
    TheaterLights lights;
    Screen screen;
    PopcornPopper popper;

    // Constructor for setup
    public HomeTheaterFacade(Amplifier amp,
                             Tuner tuner,
                             DvdPlayer dvd,
                             CdPlayer cd,
                             Projector projector,
                             Screen screen,
                             TheaterLights lights,
                             PopcornPopper popper) {

        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
        this.cd = cd;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    // Call all the required sub-system objects
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");

        popper.on();
        popper.pop();

        lights.dim(10);
        screen.down();

        projector.on();
        projector.wideScreenMode();

        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);

        dvd.on();
        dvd.play(movie);
    }

    // Call all the required sub-system objects
    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }
}
```
Client
``` java
public class Client {
    public static void main(String[] args)
    {
        Amplifier amp = new Amplifier("Top-O-Line Amplifier");
        Tuner tuner = new Tuner("Top-O-Line AM/FM Tuner", amp);
        DvdPlayer dvd = new DvdPlayer("Top-O-Line DVD Player", amp);
        CdPlayer cd = new CdPlayer("Top-O-Line CD Player", amp);
        Projector projector = new Projector("Top-O-Line Projector", dvd);
        TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
        Screen screen = new Screen("Theater Screen");
        PopcornPopper popper = new PopcornPopper("Popcorn Popper");

        // Setup facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, dvd, cd,
                                                                projector, screen, lights, popper);
        
        // Call facade method which does all the complex steps
        // Othwerwise, client would have had to implement all the steps
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}
```

## **3.5 Flyweight Design Pattern**

The flyweight pattern uses sharing to support a large number of fine-trained objects efficiently

The pattern is primarily used to reduce the number of objects created
* less number of objects reduces the memory usage
* memory usage is also minimized by sharing data as much as possible - crucial for low memory devides, such as mobile devices or embedded systems
* performance is also increased

Tries to reuse already existing similar kind objects by storing them
* one instance of a class can be used to provide many "virtual instances"
* creates a new object when no matching object is found

Flywieght objects are shared and are immutable
* cannot be modified once they have been constructed

Flyweight objects are used in multiple contexts simultaneously and acts as an independent object in each context
* indistinguishable from an instance of the object that is not shared

**Intrinsic vs Extrinsic State**
Two common terms are used when learning about the flyweight pattern
* intrinsic state/properties - can be stored in the flyweight object and is shareable
* extrinsic state/properties - depends on the flyweight's context and is not shareable - client objects define state and pass the extrinsic state to the flyweight

Example:
A text editor application where we enter characters
* an object of the Character class is created
* the attributes of the Character class are name, font and size
* we do not need to create an object every time a client enters a character since letter 'B' is no different form another 'B'
* if a client again types a 'B' we simple return the object which we have already created before
> all of these are intrinsic states (name, font, size)
> they can be shared amont the different objects as they are similar to each other

If we add more attributes to the Character class
* row and column - specify the position of a character in the document
* these attributes will not be similar even for the same characters
* these states are termed as extrinsic states and cannot be shared amonst objects

**When to use the flyweight**
When an application uses a large number of objects

When storage costs are high because of the sheer quantity of objects

When many groups of objects may be replaced by relatively few share dobjects (once extrinsic state is removed)

When the application does not depend on object identity - since flyweight objects may be shared, identiy tests will return true for conceptually distinct objects

**Participants**
**Flyweight** - declares an interface through which flyweights can receive and act on extrinsic state

**ConcreteFlyweight** 
* implements the Flywieght interface and adds storage (if any)
* must be sharable
* any state it stores must be intrinsic - must be independent of the Concreteflyweight object's context

**UnsharedConcrete Flyweight** 
* not all Flyweight subclasses need to be shared
* the Flyweight interface enables sharing: it does not enforce it
* common for UnsharedConcreteFlyweight objects to have ConcreteFlyweight objects as children - at some level in the flyweight object structure

**Flyweight Factory** 
* creates and manages flyweight objects
* ensures that flyweights are shared properly
* when a client requests a flyweight, the FlyweightFactory object supplies an existing instance or creates one, if non exists

**Client** 
* Maintains a reference to flyweight(s)
* Computes or stores the extrinsic state of flyweight(s)

**Example 1**
Flyweight
``` java
public interface RobotInterface {
    void print();

    // extrinsic data is passed as arguments
    void setColor(String colorOfRobot);
}
``` 
ConcreteFlyweight
``` java
class Robot implements RobotInterface  {

    String robotType;
    public String colorOfRobot;

    public Robot(String robotType) {
        this.robotType=robotType;
    }
    public void setColor(String colorOfRobot) {
        this.colorOfRobot=colorOfRobot;
    }
    @Override
    public void print() {
        System.out.println(" This is a " +robotType+ " type robot with "+colorOfRobot+ "color");
    }
}
```
Factory
``` java
class RobotFactory {
    Map<String, RobotInterface> shapes = new HashMap<>();

    public int totalObjectsCreated() {
        return shapes.size();
    }

    public RobotInterface getRobotFromFactory(String robotType) throws Exception {
        RobotInterface myRobot = null;

        if (shapes.containsKey(robotType)) {
            myRobot = shapes.get(robotType);
        } else {
            switch (robotType) {
                case "King":
                    System.out.println("We do not have a King Robot.  So we are creating a King Robot now");
                    myRobot = new Robot("King");
                    shapes.put("King", myRobot);
                    break;
                case "Queen":
                    System.out.println("We do not have Queen Robot. So we are creating a Queen Robot now .");
                    myRobot = new Robot("Queen");
                    shapes.put("Queen", myRobot);
                    break;
                default:
                    throw new Exception(" Robot Factory can create only King and Queen Robots");
            }
        }

        return myRobot;

    }
}
```
Client
``` java
public class Client {

    static String getRandomColor() {
        Random r=new Random();
        /*You can supply any number of your choice in nextInt argument.
         * we are simply checking the random number generated is an even number
         * or an odd number. And based on that we are choosing the color.
         * For simplicity, we’ll use only two colors—red and green
         */
        int random=r.nextInt(20);
        if(random%2==0) {
            return "red";
        }
        else {
            return "green";
        }
    }

    public static void main(String[] args) throws Exception
    {
        RobotFactory myfactory = new RobotFactory();
        System.out.println("\n***Flyweight Pattern Example***\n");

        Robot shape = null;

        /*Here we are trying to get 3 king type robots*/
        for (int i = 0; i < 3; i++) {
            shape =(Robot) myfactory.getRobotFromFactory("King");
            shape.setColor(getRandomColor());
            shape.print();
        }

        /*Here we are trying to get 3 queen type robots*/
        for (int i = 0; i < 3; i++) {
            shape =(Robot) myfactory.getRobotFromFactory("Queen");
            shape.setColor(getRandomColor());
            shape.print();
        }

        int NumOfDistinctRobots = myfactory.totalObjectsCreated();
        System.out.println("\n Finally no of Distinct Robot objects created: "+ NumOfDistinctRobots);
    }
}
```
**Example 2**
Flyweight
``` java
public interface Player {
    // extrinsic data
    void assignWeapon(String weapon);
    void mission();
}
```
ConcreteFlyweight
``` java
class T implements Player{
    // intrinsic attribute
    private final String TASK;
    // extrinsic attribute
    private String weapon;

    public T() {
        this.TASK = "Task is to plant a b";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("T with weapon " + this.weapon + " | " + this.TASK);
    }

}

class CT implements Player{
    // intrinsic attribute
    private final String TASK;
    // extrinsic attribute
    private String weapon;

    public CT() {
        this.TASK = "Task is to diffuse a b";
    }

    @Override
    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    @Override
    public void mission() {
        System.out.println("CT with weapon " + this.weapon + " | " + this.TASK);
    }
}
```
Factory
``` java
public class PlayerFactory {
    private static HashMap<String, Player> playerList = new HashMap<>();

    public static int getNumberOfDistinctPlayer(){
        return playerList.size();
    }

    public static Player getPlayer(String playerType){
        Player myPlayer = null;

        if(playerList.containsKey(playerType)){
            myPlayer = playerList.get(playerType);
        } else {
            switch (playerType){
                case "T":
                    System.out.println("T created");
                    myPlayer = new T();
                    break;
                case "CT":
                    System.out.println("CT created");
                    myPlayer = new CT();
                    break;
            }
            playerList.put(playerType, myPlayer);
        }
        return myPlayer;
    }
}
```

Client
``` java
public class Client {

    private static String[] playerType = {"T", "CT"};
    private static String[] weapon = {"AK", "Maverick", "Gut", "Flane", "Stone", "Desert Eagle"};

    private static Player generatePlayer(){
        Random r = new Random();
        int index = r.nextInt(playerType.length);
        return PlayerFactory.getPlayer(playerType[index]);
    }

    private static String generateWeapon(){
        Random r = new Random();
        int index = r.nextInt(weapon.length);
        return weapon[index];
    }

    public static void main(String[] args) {

        for (int i=0; i<10; i++){
            Player p = generatePlayer();
            p.assignWeapon(generateWeapon());
            p.mission();
        }

        System.out.println(PlayerFactory.getNumberOfDistinctPlayer());

    }
}
```

## **3.5  Proxy Design Pattern**
The proxy design pattern provides a surrogate or placehlder for another object to control access to it - used when we want to provide controlled access of a functionality

The formal definiton of a proxy is a person authorized to act for another person
* an agent or substitute
* the authority to act for another

There are situations in which a client does not or can not reference an object directly, but wants to stull interact with the object
* introduces a level of indirection when accessing an object

A proxy object can act as teh intermediary between the client and the target object

Another common use ase is to provide a wrapper implementation for better performance

**Types of Proxies:**  
**remote** 
* manages interaction between a client and a remote object
* provides a reference to an object located in a different address space on the same or different machine

**virtual**
* controls access to an object that is expensive to instantiate
* allows for the creation of a memory intensive object on demand - object will not be created until it is really needed

**copy-on-write** 
* defers copying(cloning) a target object until required by client actions
* a form of a virtual proxy

**cache** 
* provides temporary storate of the results of expensive target operatios so that multiple clients can share the results

**firewall** 
* protects targets from bad clients (or vice versa)

**synchronization** 
* provides multiple accesses to a target object

**smart reference** 
* provides addtional actions whenere a target object is referenced such as counting the number of references to the object


**Participants:**  
**Subject** - defines the common interface for RealSubject and Proxy - a proxy can be used anwehere a RealSubject is expected
**Proxy**  
* clients interact with the Realsubject through the Proxy
* maintains a reference that lets the proxy access the real subject
> controls access the real subject and may be responsible for creating and deleting it
>* may be needed if the Subject is running on a remote machine
>* may be needed if the Subject is expensive to create in some way or if access to the subject needs to be protected in some way
> forwards requests to RealSubject when appropriate (delegatin)
>* depending on type of proxy
* provides an interface identical to Subject's so that a proxy can be substituted for the real subject
* may refer to a Subject if the RealSubject and Subject interfaces are the same

* other responsibilities depend on the kind of proxy:
> remote proxies are responsible for encoding a request and its arguments and for sending the encoded request to the real subject in a different address space
> virtual proxies may cache additional information about the real subject so that they can postpone accesing it
> protection proxies check that the caller has the access permissions required to perform a request

* has the authority the act on behalf of the client to interact with the target object


Subject
``` java
public interface Internet {
    void connectTo(String serverhost) throws Exception;
}

class RealInternet implements Internet{
    @Override
    public void connectTo(String serverhost) throws Exception{
        System.out.println("Connecting to " + serverhost);
    }
}
```

Proxy
``` java
public class ProxyInternet implements Internet {
    private Internet internet = new RealInternet();

    private static List<String> bannedSites;

    static {
        bannedSites  = new ArrayList<>();
        bannedSites.add("whatever.com");
        bannedSites.add("yup.com");
        bannedSites.add("hello.com");
        bannedSites.add("first.com");
    }

    @Override
    public void connectTo(String serverhost) throws Exception{

        if(bannedSites.contains(serverhost.toLowerCase())){
            throw new Exception("Access Denied");
        } else {
            internet.connectTo(serverhost);
        }
    }
}
```

Client
``` java
public class Client {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        try {
            internet.connectTo("google.com");
            internet.connectTo("hello.com");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
```

___

## **Summary - section 19** 
// todo
___


## **4. Behavioral Design Patterns**  
These design patterns are specifically concerned with communication between objects
* characterize complex control flow that is difficult to follow at run-time
* shift the focus away from flow of control to let you concentrate just on the way objects are interconnected

These patterns increase flexibility in carrying out this communication

Provide solutions on how to segregate object to be both dependent and independent

Concerned with algorithms and the assignment of responsibilities between objects

**Behavioral Patterns covered below:**  
* Chain of Responsibility 
* Command
* Interpreter
* Iterator
* Mediator
* Memento
* Observer
* State
* Strategy
* Template
* Visitor

## **4.01 Chain of Responsiblity**  
The chain of responsiblity pattern avoids coupling the sender of a request to its receiver by giving more than one object a change to handle the request - the chain receiving objects and pass the request along the chain until an object handles it

This pattern processes a series of objects one by one (in a sequential manner) - a source will initiate this processing

Lets you send requests to an object implicitly through a chain of candidate objects 
* after one's processing is done, if anything is still pending, it can be forwarded to the next object in the chain - each receiver contains reference to another receiver
* we can add new objects anytime (run-time) at the end of a chain

**When to use this pattern?** 
When you want to decouple a request's sender and receiver

when multiple objects, determined at runtime, are candidates to handle a request

When you do not want to specify handlers explicitly in your code

When you want to issue a request to one of several objects without specifying the receiver explicitly - we expect any of our receivers to handle that request

When multiple objects can handle a request and the handler doesn't have to be a specific object

**Participants** 
**Handler**
* defines an interface for handling requests
* (optional) implments the successor link - dispatches the request to chain of handlers

**ConcreteHandler**
* handles requests it is responsible for
* can access its successor
* if the ConcreteHandler can handler the request, it does so; otherwise it forwards the request to its successor

**Client**
* initiates the request to a ConcreteHandler object on the chain

**Example 1**

Handler
``` java
public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency cur);
}
```

ConcreteHandler
``` java
class Dollar50 implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 50){
            int num = cur.getAmount() / 50;
            int remainder = cur.getAmount() % 50;
            System.out.println("Dispensing " + num + " 50$ note");

            if (remainder != 0){
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(cur);
        }
    }
}


class Dollar20 implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 20){
            int num = cur.getAmount() / 20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Dispensing " + num + " 20$ note");

            if (remainder != 0){
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(cur);
        }
    }
}

class Dollar10 implements DispenseChain {
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        chain = nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if (cur.getAmount() >= 10){
            int num = cur.getAmount() / 10;
            int remainder = cur.getAmount() % 10;
            System.out.println("Dispensing " + num + " 10$ note");

            if (remainder != 0){
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(cur);
        }
    }
}
```
Helper class
``` java
public class Currency {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
```
Client
``` java
public class Client {
    private DispenseChain c1;

    public Client() {
        // initialize the chain
        this.c1 = new Dollar50();
        DispenseChain c2 = new Dollar20();
        DispenseChain c3 = new Dollar10();

        // set the chain of responsibility;
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public static void main(String[] args) {
        Client atm = new Client();
        while(true){
            int amount = 0;

            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();

            if (amount % 10 != 0){
                System.out.println("Amount should be in multiple of 10s");
                return;
            }

            // process the request
            atm.c1.dispense(new Currency(amount));
        }
    }
}
```


**Example 2**

Handler
``` java
public interface ReceiverInterface {
    boolean processMessage(Message msg);
    void setNextChain(ReceiverInterface nextChain);
}
```

ConcreteHandler
``` java
class FaxErrorHandler implements ReceiverInterface{
    private ReceiverInterface nextReceiver;

    @Override
    public boolean processMessage(Message msg) {
        if (msg.text.contains("Fax")){
            System.out.println("FaxErrorHandler processed " + msg.priority + " priority issue: " + msg.text);
        } else {
            if (nextReceiver != null){
                nextReceiver.processMessage(msg);
            }
        }
        return false;
    }

    @Override
    public void setNextChain(ReceiverInterface nextChain) {
        this.nextReceiver = nextChain;
    }
}

class EmailErrorHandler implements ReceiverInterface{
    private ReceiverInterface nextReceiver;

    @Override
    public boolean processMessage(Message msg) {
        if (msg.text.contains("Email")){
            System.out.println("EmailErrorHandler processed " + msg.priority + " priority issue: " + msg.text);
        } else {
            if (nextReceiver != null){
                nextReceiver.processMessage(msg);
            }
        }
        return false;
    }

    @Override
    public void setNextChain(ReceiverInterface nextChain) {
        this.nextReceiver = nextChain;
    }
}
```
Helper class
``` java
public class Message {
    public String text;
    public MessagePriority priority;

    public Message(String text, MessagePriority priority) {
        this.text = text;
        this.priority = priority;
    }
}

public enum MessagePriority {
    Normal,
    High
}

public class IssueRaiser {
    public ReceiverInterface setFirstReceiver;

    public IssueRaiser(ReceiverInterface setFirstReceiver) {
        this.setFirstReceiver = setFirstReceiver;
    }

    public void raiseMessage(Message msg){
        if (setFirstReceiver != null){
            this.setFirstReceiver.processMessage(msg);
        }
    }
}
```
Client
``` java
public class Client {
    public static void main(String[] args) {
        System.out.println("Chain responsibility demo");

        // making the chain first: Fax -> email
        ReceiverInterface faxHandler, emailHandler;

        // end of chain
        emailHandler = new EmailErrorHandler();

        // fax handler is before email
        faxHandler = new FaxErrorHandler();
        faxHandler.setNextChain(emailHandler);

        // starting point: raiser will raise issues and set the first handler
        IssueRaiser raiser = new IssueRaiser(faxHandler);

        Message m1 = new Message("Fax afsafasfasfa", MessagePriority.Normal);
        Message m2 = new Message("Email afsafasfasfa", MessagePriority.High);
        Message m3 = new Message("afsafaFaxsfasfa", MessagePriority.Normal);
        Message m4 = new Message("afsafaEmailsfasfa", MessagePriority.High);

        raiser.raiseMessage(m1);
        raiser.raiseMessage(m2);
        raiser.raiseMessage(m3);
        raiser.raiseMessage(m4);
    }
}
```
``` text
Chain responsibility demo
FaxErrorHandler processed Normal priority issue: Fax afsafasfasfa
EmailErrorHandler processed High priority issue: Email afsafasfasfa
FaxErrorHandler processed Normal priority issue: afsafaFaxsfasfa
EmailErrorHandler processed High priority issue: afsafaEmailsfasfa
```

___

## **4.02 Command Design Pattern**
Command
``` java
interface Order {
    void execute();
}
```

ConcreteCommand
``` java
class BuyStock implements Order {
    private Stock myStock;

    public BuyStock(Stock someStock) {
        myStock = someStock;
    }

    public void execute() {
        myStock.buy();
    }
}

class SellStock implements Order {
    private Stock myStock;

    public SellStock(Stock someStock){
        this.myStock = someStock;
    }

    public void execute() {
        myStock.sell();
    }
}
```

Receiver
``` java
public class Stock {
    private String name;
    private int quantity;

    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void buy() {
        System.out.println("Stock [ Name: "+name+ " , Quantity: " + quantity +" ] bought");
    }

    public void sell(){
        System.out.println("Stock [ Name: "+name+" , Quantity: " + quantity +" ] sold");
    }
}
```

Invoker
``` java
class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
```

Client
``` java
public class Client {
    public static void main(String[] args) {
        Stock AAA = new Stock("AAA", 100);
        Stock BBB = new Stock("BBB", 200);
        Stock CCC = new Stock("CCC", 300);

        BuyStock buyStockAAA1 = new BuyStock(AAA);
        BuyStock buyStockAAA2 = new BuyStock(AAA);
        BuyStock buyStockBBB1 = new BuyStock(BBB);
        BuyStock buyStockCCC1 = new BuyStock(CCC);
        SellStock sellStockAAA1 = new SellStock(AAA);
        SellStock sellStockBBB1 = new SellStock(BBB);

        Broker broker = new Broker();
        broker.takeOrder(buyStockAAA1);
        broker.takeOrder(buyStockAAA2);
        broker.takeOrder(buyStockBBB1);
        broker.takeOrder(buyStockCCC1);

        broker.takeOrder(sellStockAAA1);
        broker.takeOrder(sellStockBBB1);

        broker.placeOrders();
    }
}
```
``` text
Stock [ Name: AAA , Quantity: 100 ] bought
Stock [ Name: AAA , Quantity: 100 ] bought
Stock [ Name: BBB , Quantity: 200 ] bought
Stock [ Name: CCC , Quantity: 300 ] bought
Stock [ Name: AAA , Quantity: 100 ] sold
Stock [ Name: BBB , Quantity: 200 ] sold
```
___

## **4.03 Interpreter Design Pattern**

AbstractExpression
``` java
public interface Expression {
    boolean interpret(Context context);
}
```
TerminalExpression
``` java
class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(Context context) {
        return context.getResult(data);
    }
}
```
NonterminalExpressions
``` java
public class AndExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Context context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}

class OrExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Context context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
```

Context
``` java
class Context {
    String input;

    public Context(String input) {
        this.input = input;
    }

    public boolean getResult(String data) {
        if(input.contains(data))
            return true;
        else
            return false;
    }
}
```

Client
``` java
public class Client {

    public static Expression getMaleExpression() {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main (String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        Context ic = new Context("John");
        System.out.println("John is male? " + isMale.interpret(ic));

        Context ic2 = new Context("Married Julie");
        System.out.println("Julie is a married women? " + isMarriedWoman.interpret(ic2));

        Context ic3 = new Context("Lucy");
        System.out.println("Lucy is male? " + isMale.interpret(ic3));
    }
}
```
``` text
John is male? true
Julie is a married women? true
Lucy is male? false
```
___

## **4.04 Iterator Design Pattern**

Used it when you want to provide standard way to iterate over a collection and hide the implementation logic from client program


``` java
public interface SubjectInterface {
    public IteratorInterface createIterator();
}

class Arts implements SubjectInterface {
    private String[] subjects;

    public Arts() {
        subjects = new String[2];
        subjects[0] = "Bengali";
        subjects[1] = "English" ;
    }

    public IteratorInterface createIterator() {
        return new ArtsIterator(subjects);
    }
}

class Science implements SubjectInterface {
    private LinkedList<String> subjects;

    public Science()  {
        subjects = new LinkedList<String>();
        subjects.addLast("Maths");
        subjects.addLast("Comp. Sc.");
        subjects.addLast("Physics");
    }

    @Override
    public IteratorInterface createIterator()  {
        return new ScienceIterator(subjects);
    }
}
```

``` java
interface IteratorInterface {
    void first();//Reset to first element
    String next();//get next element
    boolean isDone();//End of collection check
    String currentItem();//Retrieve Current Item
}

class ArtsIterator implements IteratorInterface {
    private String[] subjects;
    private int position;

    public ArtsIterator(String[] subjects)  {
        this.subjects = subjects;
        position = 0;
    }

    public void first()  {
        position = 0;
    }

    public String next()  {
        return subjects[position++];
    }

    public boolean isDone() {
        return position >= subjects.length;
    }

    public String currentItem()  {
        return subjects[position];
    }
}

class ScienceIterator implements IteratorInterface {
    private LinkedList<String> subjects;
    private int position;

    public ScienceIterator(LinkedList<String> subjects)  {
        this.subjects = subjects;
        position = 0;
    }

    public void first()  {
        position = 0;
    }

    public String next()  {
        return subjects.get(position++);
    }

    public boolean isDone() {
        return position >= subjects.size();
    }

    public String currentItem()  {
        return subjects.get(position);
    }
}
```

Client
``` java
public class Client {

    public static void print(IteratorInterface iterator)  {
        while (!iterator.isDone())  {
            System.out.println(iterator.next());
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println("***Iterator Pattern Demo***\n");
        SubjectInterface Sc_subject = new Science();
        SubjectInterface Ar_subjects = new Arts();

        IteratorInterface Sc_iterator = Sc_subject.createIterator();
        IteratorInterface Ar_iterator = Ar_subjects.createIterator();

        System.out.println("\nScience subjects :");
        print(Sc_iterator);

        System.out.println("\nArts subjects :");
        print(Ar_iterator);
    }
}
```
___

## **4.05 Mediator Design Pattern**

``` java

interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (User u: this.users) {
            // message should not be received by the user sending it
            if(u != user) {
                u.receive(msg);
            }
        }
    }
}
```

``` java
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}

class UserImpl extends User {
    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + ": Sending Message=" + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + ": Received Message:" + msg);
    }
}
```

``` java
public class Client {
    public static void main(String[] args) {

        ChatMediator mediator = new ChatMediatorImpl();

        User user1 = new UserImpl(mediator, "Jason");
        User user2 = new UserImpl(mediator, "Jennifer");
        User user3 = new UserImpl(mediator, "Lucy");
        User user4 = new UserImpl(mediator, "Ian");

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");

    }
}
```
___

## **4.06 Memento Design Pattern**
Memento
``` java
public class Memento {
    private String state;

    public Memento(String state)
    {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
```

Originator
``` java
public class Originator {
    private String state;

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }

}
```

Caretaker
``` java
public class Caretaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
```

Client
``` java 
public class Client {
    public static void main(String [] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State #1");
        originator.setState("State #2");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #3");
        caretaker.add(originator.saveStateToMemento());

        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState());

        originator.getStateFromMemento(caretaker.get(0));
        System.out.println("First saved State: " + originator.getState());

        originator.getStateFromMemento(caretaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
}
```
``` text
Current State: State #4
First saved State: State #2
Second saved State: State #3
```
___

## **4.07 Observer Design Pattern**

___

## **4.08 State Design Pattern**

___

## **4.09 Strategy Design Pattern**
Passing strategy as parameter in method

``` java
public interface Strategy {
    int performOperation(int num1, int num2);
}

class Addition implements Strategy{
    @Override
    public int performOperation(int num1, int num2) {
        return num1 + num2;
    }
}


class Substration implements Strategy{
    @Override
    public int performOperation(int num1, int num2) {
        return num1 - num2;
    }
}


class Multiplication implements Strategy{
    @Override
    public int performOperation(int num1, int num2) {
        return num1 * num2;
    }
}
```

``` java
public class Context {
    public int execute(Strategy strat, int num1, int num2){
        return strat.performOperation(num1, num2);
    }
}
```

Client
``` java
public class Client {
    public static void main(String[] args) {
        Addition add = new Addition();
        Substration sub = new Substration();
        Multiplication mul = new Multiplication();

        Context context = new Context();
        System.out.println(context.execute(add, 10, 5));
        System.out.println(context.execute(sub, 10, 5));
        System.out.println(context.execute(mul, 10, 5));
    }
}
```
___

## **4.10 Template Design Pattern**

Template method, final so subclasses cannot override it

``` java
abstract class HouseTemplate {

    // template method, final so subclasses cannot override it
    public final void buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("House is built");
    }

    // default implementation, hook method
    private void buildWindows() {
        System.out.println("Building Glass Windows");
    }

    //methods to be implemented by subclasses
    public abstract void buildWalls();
    public abstract void buildPillars();

    private void buildFoundation() {
        System.out.println("Building foundation with cement, iron rods and sand");
    }
}

class WoodenHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        System.out.println("Building Wooden Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with Wood coating");
    }
}

class GlassHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        System.out.println("Building Glass Walls");
    }

    @Override
    public void buildPillars() {
        System.out.println("Building Pillars with glass coating");
    }
}
```

``` java
public class Client {
    public static void main(String[] args) {
        HouseTemplate houseType = new WoodenHouse();

        // using template method
        houseType.buildHouse();

        System.out.println("************");

        houseType = new GlassHouse();
        houseType.buildHouse();
    }
}
```

___

## **4.11 Visitor Design Pattern**

-> use overloading
``` java
public interface Visitable {
    void accept(Visitor visitor);
}

class Book implements Visitable {
    double price;
    double weight;

    public Book(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class CD implements Visitable {
    double price;
    double weight;

    public CD(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class DVD implements Visitable {
    double price;
    double weight;

    public DVD(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
```

``` java
public interface Visitor {
    void visit(Book book);
    void visit(CD cd);
    void visit(DVD dvd);
    double getTotalPostage();
}

class USPostageVisitor implements Visitor{
    private double totalPostageForCart = 0;

    @Override
    public void visit(Book book) {
        if(book.getPrice() < 20) {
            totalPostageForCart += book.getWeight() * 2;
        }
    }

    @Override
    public void visit(CD cd) {
        if(cd.getPrice() < 20) {
            totalPostageForCart += cd.getWeight() * 2.5;
        }
    }

    @Override
    public void visit(DVD dvd) {
        if(dvd.getPrice() < 20) {
            totalPostageForCart += dvd.getWeight() * 3;
        }
    }

    public double getTotalPostage(){
        return totalPostageForCart;
    }
}


class SouthAmericaSPostageVisitor implements Visitor{
    private double totalPostageForCart = 0;

    @Override
    public void visit(Book book) {
        if(book.getPrice() < 30) {
            totalPostageForCart += (book.getWeight() * 2) * 2;
        }
    }

    @Override
    public void visit(CD cd) {
        if(cd.getPrice() < 30) {
            totalPostageForCart += (cd.getWeight() * 2.5) * 2;
        }
    }

    @Override
    public void visit(DVD dvd) {
        if(dvd.getPrice() < 30) {
            totalPostageForCart += (dvd.getWeight() * 3) * 2;
        }
    }

    public double getTotalPostage(){
        return totalPostageForCart;
    }
}

```

Client

``` java
public class Client {

    public static double calculateTotalCost(List<Visitable> myList, Visitor visitor){
        for (Visitable el : myList){
            el.accept(visitor);
        }
        double postage = visitor.getTotalPostage();
        return postage;

    }

    public static void main(String[] args) {
        List<Visitable> myList = new ArrayList<>();
        myList.add(new Book(10,10));
        myList.add(new CD(20,20));
        myList.add(new Book(30,30));
        myList.add(new DVD(40,40));

        Visitor usVisitor = new USPostageVisitor();
        Visitor saVisitor = new SouthAmericaSPostageVisitor();

        double usCost = calculateTotalCost(myList, usVisitor);
        double saCost = calculateTotalCost(myList, saVisitor);

        System.out.println("US cost: " + usCost);
        System.out.println("South America cost: " + saCost);
    }
}
```