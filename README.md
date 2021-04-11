# CaseStudyWithSpring
New repo with same project as bootcampCaseStudy, except this project has been initialized as a spring boot project instead of using spring data jpa
## User Stories
#### Login/Registration page
- As a user, I want to be able to register a new account if I don't have one, so that I can gain access to the application
- As a user, I want to be able to login to my account, so that I can use the application

#### Profile Page
- As a user I want to be able to see all the games in my profile, so that I can have an overview of my gaming history
- As a user I want to be able to see all hours played, times completed, and current list all in one area, so I can make quick comparisons for myself

#### Add Game Page
- As a user I want to be able to add a new game to my profile, so I can keep track of progress on the new game
- As a user I want to be able to add hours played when adding a game, so I can add games I have played in the past
- As a user I want to be able to add times completed when adding a game, so I can add games I have played in the past
- As a user I want to be able to add a game to either my backlog, currently playing, or completed games lists when adding a game, so that I can add my entire gaming history and planned future to my profile
- As a user I want to be able to see the games that are already in the database of games, so I can avoid spelling mistakes and get full functionality out of the rest of the application

#### Play Game Page
- As a user I want to be able to select any game on my currently playing list, and keep track of playing time in real time
- As a user I want to be able to start and stop a timer, so that I can get the most accurate time added to the play time of a game in my list
- As a user I don't want to see games that aren't in my currently playing list, so I can avoid clutter on the page
- As a user I want to be able to see how much time will be added to the game before updating, so I can know for sure how much time is being added to the game per session

#### Edit Game Page
- As a user I want to be able to edit games that are in my profile, so that I can make changes incase I make a mistake or want to change the list of a game
- As a user I want to see a list of all the games in my profile, so I can edit any game whenever I need to
- As a user I want to be able to select what list I want to move a game to
- As a user I want to be able to change the hours played when editing, in the case that I played without using the application to track play time.
- As a user I want to be able to change the times completed, so that when I finish a game I can update my profile every time I finish a game
- As a user I want to be able to leave hours played and times completed blank, so that I can keep them unchanged in my profile

#### Delete Game Page
- As a user I want to be able to see all the games in my list, so that I can delete the game from my profile.
- As a user I want to be able to delete games from my list, so that if I don't want them on my profile anymore I can remove them

#### Compare Game Page
- As a user I want to be able to compare all the games on my profile to other users of the app, so I can see how I stack up against other players
- As a user I want to be able to sort the compare list by hours, so I can see if I've played more or less than other users
- As a user I want to be able to sort the compare list by times completed, so I can see if I've finished a game more or less than the others

#### Logout
- As a user I want to be able to logout of the application, so that I can leave the application


## Technical Challenges
#### Entity Relationshiips
- Deciding how many entitites I needed to create
- How to create them in tandem with each other, and when that would be necessary
- Getting my join tables to work together correctly with the correct key contraints

#### Spring
- First time ever using or seeing spring
- Learning how to query tables correctly with Spring Data JPA.
- Spring boot conversion/for JUnit

#### Front End
- Not my strong suit
- Getting it to look somewhat decent
- Making checks on the front end so users can't insert the wrong kind of data
- Creating a well thought out intuitive design
- Form resubmission

#### Back End Logic
- Ran into some issues with deleting entities from the database as well as changing certain lists an entity might be on
- Deciding what methods/functions I needed and didn't need

## How I tackled the challenges
#### Entity Relationships
- A little trial and error with a couple different entity layouts.
- Knowing that certain objects will only be created at certain times
- Trial and error with OneToMany and ManyToMany realationships to create the correct key contraints on the join Table

#### Spring
- Using what I learned in class in conjunction with the knowledge of other students who have used spring before as well as looking up and reading about ways to make Spring function better
- Writing custom method queries came with time and doing it a few times. Learning the keywords that need to be used was critical here
- Once I got the main functionality I needed Spring Boot to make JUnit testing easier. Having never done Spring boot I leaned on knowledge of other classmates to help me set up my project correctly

#### Front End
- I used a design I like that I had used for an earlier project and carried that over to this to reduce the burden of designing something new
- Learning key words that can be used in input tags so the user can't insert bad data
- Wireframes helped to make designing the basic layouts of each page much easier
- When returning a MAV in the view form resubmission would create duplicate data in certain places in the database. To fix this I started returning redirects after submitting a for as to reset the hidden URL submission

#### Back End Logic
- My change list function had some weird side effects where entire lists would get deleted. To fix this I made each of the lists that a user hold its own spereate JoinTable
- This brought on another issue when using OneToMany where the game entity would be a unique key in the JoinTables. This meant multiple users could not put the same game on the same list. Changing the entity relationship to OneToMany fixed this, and gave voth users and games a foreign key contrainst
- UserGame objects/entitites are only created when a user adds a game to the database. But in the initial creation of that method I forgot to add the game to a list in the User entity. I had to go back and make changes to a few methods to make sure that all the necessary changes to both the User entity and the UserGame entity were being made whenever necessary. 
