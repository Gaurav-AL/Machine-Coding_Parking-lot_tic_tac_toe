import java.util.*;
class FindPath{
    public static int shortestPath(String[][] maze,int i,int j,int row, int col){
        if(i < 0 || j < 0 || i >= row || j >= col){
            return Integer.MAX_VALUE-1;
        }
        if(maze[i][j].equals("#") || maze[i][j].equals("X")){
            return Integer.MAX_VALUE-1;
        }
        if(maze[i][j].equals("G")){
            return 1;
        }
        if(maze[i][j].equals("S")  || maze[i][j].equals("1")){
            maze[i][j] = "X";
            return 1 + Math.min(Math.min(shortestPath(maze,i-1,j,row,col),shortestPath(maze,i,j-1,row,col)),
                        Math.min(shortestPath(maze,i+1,j,row,col),shortestPath(maze,i,j+1,row,col)));
        }
        return 0;
    }
    public static void main(String... args) throws Throwable{
        int row=0,col=0,start_row= 0,start_col = 0;
        Scanner read = new Scanner(System.in);
        System.out.println("Enter the row and column");
        row = read.nextInt();
        col = read.nextInt();
        String[][] maze = new String[row][col];

        for(int i = 0 ; i < row; i++){
            for(int j = 0 ; j < col ;j++){
                maze[i][j] = read.next();
            }
        }
        for(int i = 0 ; i < row; i++){
            for(int j = 0 ; j < col ;j++){
                if(maze[i][j].equals("S")){
                    start_row = i;
                    start_col = j;
                    break;
                }
            }
        }
        System.out.println("Shortest path :"+shortestPath(maze,start_row,start_col,row,col));

    }
}