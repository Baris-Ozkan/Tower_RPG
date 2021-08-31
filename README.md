# Tower RPG

#### What will the application do?
The application is a RPG (Role Playing Game) that will allow the user to create a **hero** with various stats and use this **hero** to defeat the **monsters** of each level in a **tower** system where each following level is harder than the previous one and see how far they can go without being defeated. To aid the **hero**, levels can feature **items** that will support the **hero** in combat.
#### Who will use it?
The main user of the application will be me and a few friends after I finish the project, so that we can see who fares better in the tower. However, in theory it's an application that anyone who is a fan of the rpg genre can enjoy.
#### Why is this project of interest to you?
I'm interested in games and development and have always wanted to have my own project one day, so I thought I could make something simple for this term project that would have a close connection to me. RPGs are one of the oldest genre of games to date and their applications are so vast that they can manifest as a board game or a sheet of paper as well as a computer game. I thought making a simple RPG would help me understand the process of development and challenge myself to understand underlying systems of a game.

## User Stories:
- As a user, I want to be able to create a character with my desired stats
- As a user, I want to be able to see my hero's progress towards his next level
- As a user, I want to be able to see my hero's character status
- As a user, I want to be able to combat monsters
- As a user, I want to be able to add items to my inventory
- As a user, I want to be able to view my inventory
- As a user, I want to be able to use my items in the inventory
- As a user, I want to be able to save my progress with my created hero in the tower so far
- As a user, I want to be able to load a previously created hero with its tower progress

### Phase 4: Task 2
For Phase 4, I've done the type hierarchy option. For my GUI buttons, I've created an abstract class MenuOption which has base methods for the initialization of the button and its customization. It halso has abstract methods of createButton and addListener which are overridden in each subclass to provide distinct functionality for different buttons. In addition, for button clases that have another button built in to them, they are extended by that new button's class which again override methods to provide the distinct functionality of the button.
##### The type hierarchy is as follows:
- Abstract class MenuOption is extended by CombatMenu, FloorItem, InventoryMenu, LevelUpHero, SaveProgress and Status classes.
- Also, FloorItem is extended by FloorItemButton and InventoryMenu is extended by InventoryButton.
### Phase 4: Task 3
- If I were to refactor to improve the design of my project I wouldn't make the Hero and Floor class extend Inventory.
Functionally, it makes sense because I want both Hero and Floor objects to have their own Inventory and making Hero and Floor just extend the Inventory class allows me to have that function.
However, design wise it doesn't make much sense that Hero and Floor as a class extend the Inventory class in a type hierarchy. Instead, it would be better design to design the Hero and Floor classes so that they have an aggregation relationship with Inventory making Inventory a part of a Hero and Floor type.
- Additionally, when my GUI tries to play a sound for the first time there is a time gap where the GUI freezes while trying to initialize and load the sound. When using the app it seems as if the app has crashed and feels bad. I don't know if there is a way to make my app initialize and load sound faster. However, it would make sense to refactor code so that the sound is initialized as soon as the app launches. So, before the user sees any frame or interface it initializes sound and then displays the main frame. This way the down time of initialization would happen at a time where it wouldn't make the app seem like it has frozen and would improve the presentation of the app.
## Please Read:
The GUI functions are rough and doesn't feature full features. For a full experience you can comment-out "initializeGUI();" in "TowerRPG" class and "runTowerRPG()" function.