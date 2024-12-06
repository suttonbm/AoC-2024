Let's add some additional functionality to the PatrolSimulator.  The additional functionality will identify locations where an Obstacle can be placed to prevent a Guard from exiting the Courtyard (create an infinite loop).

Additional output from the Component should include a list and total count of unique coordinates where a single Obstacle could be placed to prevent the Guard from exiting.

Review the following example:

<example>
....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...
</example>

New output from the Component should be:

<output>
....#.....
....XXXXX#
....X...X.
..#.X...X.
..XXXXX#X.
..X.X.X.X.
.#XXXXXXX.
.XXXXXXX#.
#XXXXXXX..
......#X..

Total spaces occupied: 41

Total loop-inducing placements: 6
- (6,3)
- (7,6)
- (7,7)
- (8,1)
- (8,3)
- (9,7)
</output>
