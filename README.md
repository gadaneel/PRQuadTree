# PRQuadTree
PRQuadTree Project Implemented
This project implements two data structures:
1. SkipList -> To organize the points by name. Used for searching the points by name.
2. PR Quad Tree variant - To organize the points by position in the rectangular region ((0,0) -> (1023,1023))
The PR Quadtree will organize the points by position, and will be used for spatial queries such as locating points within a query rectangle, and determining the duplicate points.

The command file may contain any mix of the following commands. In the following description, terms in { } are parameters to the command.

1. insert {name} {x} {y}

Insert a point named name with at the location (x, y). 
It is permissible for two or more points to have the same name, and it is permissible for two or more points to have the same spatial position, but not both. 
The name must begin with a letter, and may contain letters, digits, and underscore characters. Names are case-Sensitive. 
A point should be rejected for insertion if either of its coordinates are not greater than 0. 
All points must exist within the “world box” that is 1024 by 1024 units in size and has upper left corner at (0, 0). 
If a point coordinates is outside of this box, it should be rejected for insertion.
  
2. remove {name}

Remove the point with name name. If two or more points have the same name, then any one such point may be removed. If no point exists with this name, it should be so reported.

3. remove {x} {y}

Remove the point at the point {x} {y}. If two or more points have the same location, then any one such point may be removed. 
If no point exists with this name, it should be so reported.

4. regionsearch {x} {y} {w} {h}

Report all points currently in the database that are contained in the query rectangle specified by the 'regionsearch' parameters. 
For each such point, list out its name and coordinates. A regionsearch command should be rejected if the height or width are not greater than 0. 
However, it is (syntactically) acceptable for the regionsearch rectangle to be all or partly outside of the 1024 by 1024 world box. 
Additionally, you must report the number of QuadTree nodes visited by the search.

5. duplicates

Report all points that have duplicate coordinates.

6. search {name}

Return the information about the points(s), if any, that have name {name}.

7. dump

Return a “dump” of the Skip List and the QuadTree.

The Skip List dump should print out each Skip List node, from left to right. For each Skip List node, print that node’s value and the number of pointers that it contains.

The QuadTree dump should print the nodes of the QuadTree in preorder traversal order, one node per line, 
with each line indented by 2 spaces for each level in the tree (the root will indent 0 spaces since it is considered to be at level 0). 
These lines should appear after the Skip List nodes are printed.
