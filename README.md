# santaclaus-acs-part2

321CA - Mihai Daniel Soare \
Object Oriented Programming Course

Project Stage 2 - Santa Claus is coming to ACS

warning: i hate uppercases so i won't use them too much

January 2022
----------------------------------------------------------------------------------------------------

* Santa's workshop (aka Database)
    -  the program is an implementation of a singleton Database of a Santa's workshop
    that simulates the delivery of gifts to children in a noYears given in function of
    a corresponding budget for every children & a list of available gifts in the workshop

__Classes__

`fileio` 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- InputLoader  -> input loader used to extract the input data from every JSON object and collect it
                into input classes such as:
              -> Input --> noYears (field)
                       --> santasBudget (field)
                       --> field of InitialDataInput (class)   --> ChildrenInput
                                                               --> GiftInput
                       --> field of AnnualChangesInput (class) --> ChangeOfTheYear --> GiftInput
                                                                                --> ChildrenInput
                                                                                --> ChildrenUpdates

all the input classes mentioned have the basic parameters for the real classes

- Writer -> used to open an output file and write into it a JSONObject 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`main`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Main -> implements 2 methods, this is actually the main program where the test files are opened
        the output tests are created and then every test is checked by the checker
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`workshop`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Santa -> implemented with lazy instantiation a Singleton that is going to be used as the main
            program. it simulates a delivery of gifts over a noYears to children in function
            of a given list of gifts.
        -> every year changes are made to the database and a simulation of delivery starts again
        in function of a given strategy
        -> contains the following fields:
                -> noYears of the over-all delivery simulation
                -> santasBudget the overall budget of santa
                -> initialData the initial Data extracted from the input (the first simulation)
                -> annualChanges the following noYears extracted data from the input (next noYears
                deivery)
                -> children -- a list of children that can receive gifts
                -> availableGifts -- a list of all available gifts that can be given to children
                -> actualYear -- field that contains the actual Year of the simulation to know
                what data should we use in the simulation
                -> budgetUnit -- something that is used to calculate every children budget
                -> citiesMap -- something that is used to store the cities in which every
                children live and the corresponding niceScore of the children from the same city
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`memory`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- InitialData -> contains the initial data extracted from the input that is going to be added
                to the annual changes as "first change"

- AnnualChanges -> contains all the changes across the noYears
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`updates`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- ChangeOfTheYear -> the actual change of the year that contains: a new budget for santa, 
                    a list of new children, a list of new gifts and a list of new children
                    updates
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`children`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Child -> contains the following:
            -> the fields representative for a child
            -> a niceScore list that is updated every year
            -> a strategy that calculates the averageScore of every children
            -> the initialBudget assigned to the child an auxiliary field for calculating
            the delivery of gifts
            -> a receivedGifts in every year
            -> a type of elf that has a special ability that influences the delivery of the
            specified child
            -> a score that is based on the average scores of all the children from the same
            city as the respective child
            -> basic methods
            ->  these fields are now built in a ChildrenBuilder static class that has
            the methods to set the optional fields for a Child instance

- ChildUpdates -> contains updates of a corresponding child every year
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`scores`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- AverageScoreStrategy -> an interface used to get the averageScore for every child
- AverageScoreStrategyFactory -> class used to create 4 different types of strategies
                                to calculate children averageScore
                              -> they are the following:
                                    -> BabyStrategy: they get a 10 out of 10
                                    -> KidStrategy: normal average
                                    -> TeenStrategy: some weird average based on a formula
                                    -> YoungAdultStrategy: they have a null strategy
                                    cause they arent wanted in the children list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`gift`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Gift -> contains basic gifts information
       -> new field called quantity (when quantity's 0, the gift isnt delivered anymore)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`utils`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Utils -> contains a convertJSONArray method that transforms an array of JSONs into an array
        of strings
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`output`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- ChildrenOutput -> contains a method to get a JSONArray that contains all the JSONOBjects
            corresponding to all the children on the santa's list
            -> a getJSON method to get an output JSONObject that contains all the data of
            the child
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`delivery`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- DeliveryStrategy -> an interface to make more strategies for the delivery of the gifts
- DeliveryStrategyFactory -> class used to create 3 different types of strategies
                                to deliver the gifts in one year
                                (!!) actually just sets the children's list in a state
                                so that they are ordered in function of some comparators
                              -> they are the following:
                                    -> DeliveryByID: the children's list is sorted in function
                                    of IDs
                                    -> DeliveryByNiceScore: the children's list is sorted
                                    descending in function of their niceScore, then by ID
                                    -> DeliveryByNiceScoreCity: the children's list is sorted
                                    in function of a new niceScoreCity calculated as the average
                                    of niceScores for children that lives in the same city
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`elves`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Elf -> an abstract class that defines an Elf (a skillful helper of Santa's) that executes
        its skilled when the "Commander" says so...
- ElvesFactory -> class used to create 4 different types of elves
               -> they are the following:
                    -> BlackElf: reduce the children's budget with 30%
                    -> PinkElf: increase the children's budget with 30%
                    -> WhiteElf: lazy piece of sh$$; does nothin'
                    -> YellowElf: gives the children the lowest gift in the preferred category
                    if the children didnt get already a gift after the delivery
                    (!!) their skills are called to action at some point 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
`common`
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
- Constants -> storing constants used in the implementation
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
----------------------------------------------------------------------------------------------------

* How to use the program

__Entry Point__

- A new fileWriter is instantiated to write in every output file made & a new JSONObject ALSO
  that will contain the output written in the output files;

- Santa is instatiated firstly in `Main`, then the input of the Database is imported from
  <InputLoader> to the corresponding fields from the Santa.

- The delivery starts by creating an JSONObject that is returned by the delivery method;

__Input__

- it was used the model used from the first Homework, gets data from JSONObjects extracted from
  the tests and they are added to input classes;
- then data from the input classes are imported in the database, uhm santa's workshop (my bad);

__Tricks__

- Used some streams & comparators to sort children & gifts;
- Used LinkedHashSet to remove duplicates from a list in the old order;
- Used divide & impera to put my tasks into tiny task and so i've done the first stage in 2 days
  and the second one in less than 1;

__Design Patterns__

- used Singleton so I could get the santa instance everywhere, but anyways the delivery is
  simulated in this class;
- used Strategy to create strategies of calculating averageScore in function of age category;  
- used Factory to instantiate Strategies in function of age category;                          

- used Strategy to create strategies of sorting children in such a manner that the delivery    
  would be done accordingly with the type of the strategy;                                    
- used Factory to instantiate Strategies in function of their name;                            

- used a so-so Commander that doesnt really have a invoker:
    * we could say the Santa is the invoker and the actions are stored in the list of children
    * the actions are represented by Elves that have different skills, all mentioned before.
    * all of the elves except the yellow ones does their job before the actual delivery of the
      gifts, while the yellow ones does it after the delivery
    * by doing this we could implement a lot of new elves and would be really scalable for updates
- used Factory to instantiate Elves objects in function of their name

- used Builder for Child class, now there are some mandatory & optional fields that are built using
  this class to create a new instance of a Child

__Delivery__

- iterates through all the noYears changes and starts the delivery for every year

There are 9 big steps to do the delivery in one year:

(1) -> makes children changes

(2) -> makes gift changes

(3) -> calculates the averageScore & initializes the budget assigned to every children

(4) -> executes elves skills, except yellow ones

(5) -> delivering the gifts to every children of the list

(6) -> executes yellow elves skills

(7) -> sort the children list by their IDs

(8) -> extracts the list of children and prepare it for output

(9) -> increments the age of kiddos (for the next year)

[ (1) ]
- adds new children to the list (if it is the initial year it wouldnt happen)
- delete young adults from the list of children
- sort the children (the children might be unordered, but in the tests wouldnt happen)
- update every child with the new info (if the child is found in the list)

[ (2) ]
- add new gifts to the list (if it is the initial year it wouldnt happen)
- sort gifts in function of category and ascending price cause the children gets the lowest
priced gifts from the category preferred by them

[ (3) ]
- calculates the averageScore of every type of children by using a Strategy & Factory pattern
mentioned before
- initialize the budget from where a child can get a priced gift
(!!) NOW there is an optional nice Score Bonus for children added to the old averageScore
of the corresponding child

[ (4) ]
- put all the elves except the yellow ones at work:
- BlackElf => decrease with 30% the children's budget
- PinkElf => increase with 30% the children's budget
- WhiteElf => does nada

[ (5) ]
A. sorting the children in a manneer so that the delivery process would be the same
and only the children's list would be different:

!! byID !!
- the children are sorted in function of their IDs

!! byNiceScore !!
- the children are sorted in descending order of their nice Score
then by the ID

!! byNiceScoreCity !!
- the children are sorted in descending order of their nice
Score of their city they are living in;
- niceScores per City are stored in the Santa's workshop in a map
- firstly, the niceScore are added to the map
- then every children gets the niceScoreCity of their city

B. the actual delivery
- calculates the actual delivery of gifts to every children in that year
- the actual delivery works like this:
- iterate through the list of kiddos
- clear the list of received gifts of every kiddo (cause this is a new year)
- iterate through preferrences of every kiddos & then through the list of available
gifts
- if the gift is wanted, has lower price than the child's budget
& has the quantity != 0, then add it to the children received gifts list
- decrease the assigned budget of that child to give him another gift and
the overall quantity of the gift

[ (6) ]
- the yellow elves put their skill to work:

- every active yellow elf can deliver a gift to the child they are assigned to,
if they didnt get one already and the gift is the lowest priced gift from the
preferred category and its amount isn't zero

[ (7) ]

-> the list of the children is sorted again by their IDs

[ (8) ]

-> extract the output of the list of children to a JSONObject

[ (9) ]

-> if there is another year the kiddos years will be incremented

----------------------------------------------------------------------------------------------------

* Feedback

Stage 2:
- this project was done in ~1.5 day ish aka 8 hours of work, and 2-4 hours of increasing the
aesthetics of this project
- it was so easy to implement new things to my first stage project because every step of the
delivery was kinda separated from the rest of them, so i've really liked to see how scalable oop
can really be
- finally i got to use commander & strategy for the little tasks of delivery
- congrats for this project, i really appreciated it.

Stage 1:
- this project was done in ~2 days ish aka 15hours of work. it was clearly easier than the first
homework;
- sincerely i would really use commander in this project, but for real there is no point in doing
that, you could just create as many methods to actually do the specified little task for you
and we also do not need redo / undo;
- learnt how an input loader works, not so bad;
- learnt how to use a file writer;
- learnt how to organize my tasks more carefully;
- i appreciate the extra time on this deadline cause last months were horrible;
- let you some easter eggs, out there :);

----------------------------------------------------------------------------------------------------
