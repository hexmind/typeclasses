# Java typeclasses
Type classes example in Java. Inspired by 
[Scala example](https://github.com/rabbitonweb/scala_typeclasses)
([slides](http://www.slideshare.net/paulszulc/introduction-to-type-classes-in-30-min)),
[Typeclasses](http://www.slideshare.net/ekalyoncu/typeclasses-49347102)

* In Haskell, typeclass is the language feature.
* In Scala, typeclass is a pattern.
* In Java, typeclass is a experiment.
 
*A type class is a type system construct that supports **ad hoc polymorphism**.
This is achieved by adding constraints to type variables in parametrically polymorphic types. 
Such a constraint typically involves a type class T and a type variable a, 
and means that a can only be instantiated to a type whose members support the overloaded operations associated with T.* 
[Wikipedia](https://en.wikipedia.org/wiki/Type_class)

In this code Scala implicits are replaced by [Spring](https://projects.spring.io/spring-framework/) DI. 
Case classes are created with Lombok [@Value](https://projectlombok.org/features/Value.html) 
and matched by [javaslang-match](http://www.javaslang.io/).

## Requirements
* Calculate final checkout
    * [OrderEvaluation::evaluate](src/main/java/pl/hexmind/tc/evaluation/OrderEvaluation.java)
* Calculate average orders price
    * [Orders::average](src/main/java/pl/hexmind/tc/order/Orders.java)
      through [Stats::mean](src/main/java/pl/hexmind/tc/stats/Stats.java)

## Goals
* [DRY principle](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
* [Separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns)
    * Evaluation is delegated to [OrderEvaluation](src/main/java/pl/hexmind/tc/evaluation/OrderEvaluation.java)
      and [ProductEvaluation](src/main/java/pl/hexmind/tc/evaluation/ProductEvaluation.java)
* Decouple functionality form types
* Simple and clean domain model
    * Immutable case classes
          * Basic | Discounted | OutOfStock implements [Product](src/main/java/pl/hexmind/tc/order/Product.java)
          * GeneralOrder | ComplexOrder | CanceledOrder implements [Order](src/main/java/pl/hexmind/tc/order/Order.java)
* [Single responsibility] (https://en.wikipedia.org/wiki/Single_responsibility_principle)
      * additional domain features are delegated to specialized classes (Stats and Evaluable implementations)

## Run
* ./gradlew test



