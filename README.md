# Software Programming Project 1 - Memory Game/Snap
MemoryGameJava.java is the file for this project and should come with this README
## Instructions for Play
1. You will be asked for a row and column selection which will be asked on separate lines e.g. "first row number: " and on the line below "first column number:  ".
If your response to each of these is an integer from 0-3, that is not already revealed, the corresponding spot on the board will then be revealed. This step will repeat for your second chosen spot on the board.
2. If both of these spots on the board are a match they will stay face up, and if not, they will both be flipped over, hidden and you will be asked to choose another spot on the board.
This process will repeat until all matches on the board have been found.
## Implemenation on top of Base Code
### Overview
The code for the game is complete with error handling, a measurement of elapsed time and an attempt counter. 
 
### Measuring Elapsed Time
The elapsed time counter is a set lines very similar to option 3.2 from "https://www.baeldung.com/java-measure-elapsed-time" webpage.
The first "startTime" (line 68) variable records the time that variable created and so does the "finishTime" (line 93) variable. The difference between the two times is then calculated in "Duration.between(startTime, finishTime)" (line 94) and then converted to seconds.

### Attempt Counter
This has been defined as a class attribute of MemoryGameJava which is incremented by one for each attempt by the player. For this game an 'attempt' is any valid (as in it passes all validity checks; lines 103, 112, 121, 130) pair of guesses the user has made, regardless if those guesses are correct or not.

### Error and Exception Handling
Error handling has been broken into two main criteria for this project: checking the user has typed a number and not some other character via. try, catch, and that the user has typed the correct number.

## Problems Encountered
### Closing Scanner While in a Try, Catch Loop
The greatest problem was when trying to close the scanner in the getCoords1() and getCoords2() functions (lines 119 and 101 respectively). When typing a close statement at the end of each loop a message would pop up saying “convert to try-with-resources”. This meant to put the opening scanner statement within the try, catch block and Java is supposed to automatically close the scanner like so:

try (Scanner sc=new Scanner(System.in)) {
//code
//automatically closes scanner when the try block is finished
}
catch (InputMismatchException e){
//code
}

However this completely broke my code in a way that I still do not quite understand; the code would run to take the users first input, but if that input failed any condition, the try, catch block would loop and immediately crash with a NoSuchElementException. Even when Java was able to print the value of whatever it was supposed to be evaluating, immediately after it would return a NoSuchElement Exception. This post on stackoverflow,
https://stackoverflow.com/questions/47588704/java-using-scanner-with-try-with-resources
was the only forum to post the exact same issue and there is a solution/explanation however it was a bit difficult to understand, in particular what exactly was going wrong.

---

# budget-calculator-with-ui
Second Software Programming Project using Java
