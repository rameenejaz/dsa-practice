// class Position {
//     int x, y;
//     Position(int x, int y) {
//         this.x = x;
//         this.y = y;
//     }
// }

// class Stack {
//     private Position[] arr;
//     private int top;
//     private int capacity;

//     Stack(int size) {
//         arr = new Position[size];
//         capacity = size;
//         top = -1;
//     }

//     boolean isEmpty() {
//         return top == -1;
//     }

//     boolean isFull() {
//         return top == capacity - 1;
//     }

//     void push(Position val) {
//         if (!isFull()) {
//             arr[++top] = val;
//         }
//     }

//     Position pop() {
//         if (!isEmpty()) {
//             return arr[top--];
//         }
//         return null;
//     }

//     Position peek() {
//         if (!isEmpty()) {
//             return arr[top];
//         }
//         return null;
//     }

//     void printStack() {
//         for (int i = 0; i <= top; i++) {
//             System.out.print("(" + arr[i].x + "," + arr[i].y + ")");
//             if (i < top) System.out.print(" -> ");
//         }
//         System.out.println();
//     }
// }

// public class MazeSolver {

//     public static void main(String[] args) {
//         char[][] maze = {
//             {'S', '1', '0', '0'},
//             {'0', '1', '0', 'E'},
//             {'0', '0', '0', '1'},
//             {'1', '1', '0', '1'}
//         };

//         int rows = maze.length;
//         int cols = maze[0].length;

//         boolean[][] visited = new boolean[rows][cols];

//         // Find start and exit
//         Position start = null, exit = null;
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (maze[i][j] == 'S') start = new Position(i, j);
//                 if (maze[i][j] == 'E') exit = new Position(i, j);
//             }
//         }

//         if (start == null || exit == null) {
//             System.out.println("Invalid maze: missing S or E");
//             return;
//         }

//         boolean pathFound = solveMaze(maze, visited, start, exit);
//         if (!pathFound) {
//             System.out.println("No Path Exists");
//         }
//     }

//     static boolean solveMaze(char[][] maze, boolean[][] visited, Position start, Position exit) {
//         int rows = maze.length;
//         int cols = maze[0].length;

//         Stack stack = new Stack(rows * cols);
//         stack.push(start);
//         visited[start.x][start.y] = true;

//         // Directions: UP, DOWN, LEFT, RIGHT
//         int[] dx = {-1, 1, 0, 0};
//         int[] dy = {0, 0, -1, 1};

//         while (!stack.isEmpty()) {
//             Position current = stack.peek();

//             // If exit found
//             if (current.x == exit.x && current.y == exit.y) {
//                 System.out.println("Path Found:");
//                 stack.printStack();
//                 return true;
//             }

//             boolean foundNext = false;

//             // Try each direction
//             for (int dir = 0; dir < 4; dir++) {
//                 int newX = current.x + dx[dir];
//                 int newY = current.y + dy[dir];

//                 if (isValid(newX, newY, maze, visited)) {
//                     stack.push(new Position(newX, newY));
//                     visited[newX][newY] = true;
//                     foundNext = true;
//                     break; // go deeper
//                 }
//             }

//             // Dead end, backtrack
//             if (!foundNext) {
//                 stack.pop();
//             }
//         }

//         return false; // No path
//     }

//     static boolean isValid(int x, int y, char[][] maze, boolean[][] visited) {
//         int rows = maze.length;
//         int cols = maze[0].length;
//         return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] != '1' && !visited[x][y];
//     }
// }