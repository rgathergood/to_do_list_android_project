# wk9_project

# Java/Android Project Definitions - Solo Project

## To-Do List

https://trello.com/b/SWRyP6Zr/android-project

Goal: Practice what we learned in the Java / Android weeks and research how to persist data.

### MVP
You are required to write an Android app which will allow the user to maintain a task list. The user should be able to enter tasks and display them in a list. The list should only show the bare details of each task. Selecting a task from the list should bring up another screen which shows full details of the task.
Users should also be able to mark tasks as completed.

### Project Extensions

* Allow the user to prioritise and/or categorise tasks
* Use the calendar to set due dates on tasks.


## Card game

Goal: Create a card game in Android.

2 weeks ago you were assigned a lab to create a high/low  card game using objects modelled in Java. You are now being asked to take that Java code and use it as the basis for an Android app which plays the same game of cards.

***HINT*** Think of the lessons where we wrote regular Java classes in Android Studio, ran unit tests on them, and then used them in our Eight Ball app.

### MVP
The game does not need to have much or any interaction. The aim is to display the results of the Java logic already written. For example, if you had two players being dealt two cards each your screen might look something like:

```
  Player 1: Ace of Hearts, 9 of Diamonds
  Player 2: 3 of Clubs, 2 of Hearts

  Player 1 Wins!
```

### Project Extensions

* Possibly add another card game
* Improve the UI


## Jurassic Park

Goal: Practice OO modelling  in Java (unit tests, no UI)

You have been asked to create an app to manage the dinosaur population and visitor tracking of Jurassic Park. (No expense spared)

### MVP

The user must be able to add paddocks, add / remove dinosaurs to paddocks, feed dinosaurs. You should also be able to transfer Herbivores between paddocks. :/

### Project Extensions

* Dinosaurs marked as herbivores can live in the same paddock but those marked as carnivores can only be placed with dinosaurs of the same type.
* Dinosaurs can randomly rampage and break out of their paddocks.
* Visitors can enter / exit the park if the dinosaurs are not rampaging.
* View the number of visitors in the park at a given time.
* Anything else you can dream up!

## Food Tracker

Goal: Practice OO and UI design with what we learned in Java / Android weeks

### MVP
You are required to write an Android app that allows a user to track the food they eat. Users should be able to enter what they eat and when (date/time) and for what meal.

### Project Extension
The app should show the user a record of what they have eaten over a given period e.g. In March I drank lots of Irn-Bru.


## Shop

Goal: Practice OO modelling in Java (unit tests, no UI)

You are required to build an app that allows a Shop to sell goods to a Customer. Stock and items are not important.

### MVP
The Shop must be able to:

* Make a Sale
  - The customer funds go down, shop sales go up
* Give a refund
  - The customer funds go up, shop refunds go up
* Report on income
  - Total sales minus total refunds

The Customer must:

* Have a collection of possible Payment Methods:
  - CreditCard (default), DebitCard
* Be able to select a Payment Method to pay at any Shop
* Be able to select a Payment Method to recieve a refund onto a given Payment Method

(You may find HashMaps useful for this project)

### Project Extensions

* Add stock tracking

## Fruit Machine - Java

Goal: Create a fruit machine in Java.

You have been asked to create a simple piece of software that will operate a fruit machine.

Your FruitMachine class should be well tested, and should have a spin() method that returns an integer value - the amount of winnings from the Symbol's value, if three identical symbols are spun.

If three identical symbols aren't spun, you should return 0.

### MVP
The aim is to display the results of the Java logic. For example, if the user spins and lands 3 matching symbols a winning message is displayed and their winnings updated and stored. Users should also be allowed to nudge and hold at random times.

### Project Extensions

* Allow users to choose from Symbol Packs (Different games.)
* Extend to 5 reels
* Turn into an Android app.


## General Rules (Applies to ALL projects)

* ***DO NOT USE ANY FRAMEWORKS*** - this includes Java Swing and similar packages. If in any doubt ask an instructor.
* There will still be stand-up at 9am every morning (10am on Friday) - you are still expected to be in every day during the project.
* If you have an idea for a project outwith the specs defined above then please discuss this with the instructors before going ahead.
