# GUI Backtracking Recursive Snake

<img src="gif/demo.gif" width="600" >

## Intro:
As a fun side hobby, I built this project to help a friend in Holland to better understand recursion. It is a gui demonstration of a snake algorithm that when dropped anywhere into a maze, it will find the red circle and show the longest path. The code can be manipulated to also show the shortest path. This project aims to demonstrate a naive brute force/flood fill solution to this problem. The solution uses backtracking via the stack used by recursion to check every possible valid path to the apple. Note: this was built in only 9 hours, hence is not suited for scalability.

## Run it yourself
git clone https://github.com/KhangNguyen99/GUI-Recursive-Snake.git

cd GUI-Recursive-Snake/

// Edit the LongestPath.java line 175 so that it points to your respective directory holding the file LongestPathInput1.txt

./run.sh

If run.sh permissions doesn't work, do the following:

chmod 700 run.sh

