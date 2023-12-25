# JDBC Sakilla database
## Description
An academic project written in Java, the code connects to the user's local Sakilla DB and using JDBC to retrieve information about actors and movies.

The class "FilmBrowser" recieves (and built) using two parameters - username and password.
After creating the object, the user is able to perform few functions - 
*  getTitles - this function returns an array that contains every film title that has the input given (also if its partially contained).
  
  for example (also showed in main file) - getTitles("lover") will return an array that contains - ["IDENTITY LOVER","LOVER TRUMAN","LOVERBOY ATTACKS"]

* getSimilarTitles - this function recieves an array of titles, and return for each item in the array (using the function above multiply times) the titles that contains the same input given as one large array.
if the item in the array has more than one word - it will check for similarity for every word in that location.
  for example (also showed in main file) - getSimilarTitles(["African Egg"]) will return an array that contains every movie that has the word 'African' and the word 'Egg' in it.
do notice - this function wont return the movie that has the same name that was given in the array (in our example "African Egg").

The functions above will return an empty array if there was no matching result found, and will throw exceptions in case wrong input was entered.
This small project used a prepared statement method in order to solve mysql injection issues, has private methods (and variables) that not supposed to be revealed and used by the user and has a proper variable, methods and class's names. we also made sure to write (inside the code) a further explaination about each function and how it is used using a proper documentation.

## Getting Started

### Executing program
There are several ways to run this system:

* Clone git project into your IDE.
* Manually download included files and open it as a new project in your IDE.

## Authors

Contributors:

[@Aviv Eliyahu](https://github.com/aviveliyahu)
