public class MazeSolverCustomStack {
    static int rows, cols;
    public static void main(String[] args) {
        char[][] maze=createMaze();
        Position start=findStart(maze);
        Position exit=findExit(maze);
        if(start==null || exit==null) {
            System.out.println("Invalid Maze; missing S or E");
            return;
        }
        boolean found= solveMaze(maze, start, exit);
        if(!found) {
            System.out.println("No Path Exists");
        }
    }
    static char[][] createMaze() {
        return new char[][] {
            {'S', '1', '0', '0'},
            {'0', '1', '0', 'E'},
            {'0', '0', '0', '1'},
            {'1', '1', '0', '1'}
            
        };
    }
    static Position findStart(char[][] maze) {
        rows=maze.length;
        cols=maze[0].length;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (maze[i][j] == 'S') {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
    static Position findExit(char[][] maze) {
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (maze[i][j]=='E') {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
    static boolean solveMaze(char[][] maze, Position start, Position exit) {
        PositionStack stack=new PositionStack(rows*cols);
        boolean[][] visited=new boolean[rows][cols];
        stack.push(start);
        visited[start.row][start.col]=true;
        //directions: up,down left,right
        int [] dr={-1,1,0,0};
        int [] dc={0,0,-1,1};
        while (!stack.isEmpty()) {
            Position current=stack.peek();
            if (current.row==exit.row && current.col==exit.col) {
                System.out.print("Path Found: " );
                stack.printPath();
                return true;
            }
            boolean moved=false;
            for (int d=0; d<4;d++) {
                int newR=current.row+dr[d];
                int newC=current.col+dc[d];
                if (isValidMove(maze,newR,newC,visited)) {
                    visited[newR][newC]=true;
                    stack.push(new Position(newR, newC));
                    moved=true;
                    break; 
                }
            }
            if(!moved) {
                stack.pop();
            }

        }
        return false;
    }
    static boolean isValidMove(char[][] maze, int r, int c, boolean[][] visited) {
        return r>=0 & r<rows && c>=0 && c<cols && maze[r][c]!='1' && !visited[r][c];
    }
}
