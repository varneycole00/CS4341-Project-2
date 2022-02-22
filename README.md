# CS4341-Project-2
Genetic algorithm project for Introduction to Artificial Intelligence


CS4341
Assignment #2:  Genetic algorithms


This assignment will familiarize you with genetic algorithms (GA).  You will solve a variety of problems to learn about representation (the biggest challenge for GA).  You will also conduct experiments with a couple of variants of GA to see how they affect performance.


Puzzle #1:  number allocation


In this puzzle, you will be given a series of 40 numbers, one per line.  Your agent’s job is to allocate each number into 1 of 4 bins:
Bin #1:  the numbers are all multiplied together
Bin #2:  the numbers are all added together
Bin #3:  the smallest number is subtracted from the largest
Bin #4:  these numbers are ignored

Each bin must have 10 numbers allocated (i.e., the same number in each bin).  For this task, numbers will be floating point in the range [-10, 10]
The score for the task is the summed score of bins #1, #2, and #3. 



Puzzle #2:  tower building


Towers are composed of pieces.  Each piece is represented in one line (tab delimited).  Pieces have the following properties:
1) Type:  either door, wall, or lookout (String)
2) Width:  how wide the piece is (Natural)
3) Strength:  how many pieces can be stacked on top of it (Natural)
4) Cost:  how expensive it is to use this piece in a tower (Natural)

Each piece may be used at most once.  Towers are composed of a collection of pieces stacked on top of each other.  Useful (ADDED THE WORD “useful” FOR CLARIFICATION) Towers must obey the physics of the world:   
1) Towers must have a door as their bottom-most piece.
2) The top piece in a tower must be a lookout.
3) Pieces between the top and bottom of a tower, if any, must be wall segments.  I.e., towers can only have one door and one lookout.  
4) A piece in a tower can, at most, be as wide as the piece below it.
5) A piece in a tower can support its strength value in pieces placed above it.

Towers that do not follow those rules are worthless as they will either collapse or are not usable by their future owners.  Unfortunately, your GA does not know the physics of the world ahead of time or people’s preferences.  It must build a tower to see that that tower has a fitness of 0.  Therefore, you should not use those rules for the seeding the initial population.  Part of this problem is for your agent to discover the rules of the world.    


The score of a tower is (10+height2 – piece cost to build the tower).  A tower that violates any of the above 5 constraints receives 0 points.   A tower of height 2 with a door on the bottom and lookout on the top is a legal tower.
