import java.util.*;
class Maze {
    public int getFood(char[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        boolean[][] closed_list = new boolean[rows][columns];
        int startRow = -1, startColumn = -1;
        int total = rows * columns;
        for (int i = 0; i < total; i++) {
            int row = i / columns, column = i % columns;
            if (grid[row][column] == 'S') {
                startRow = row;
                startColumn = column;
                break;
            }
        }
        closed_list[startRow][startColumn] = true;
        Queue<int[]> open_list = new LinkedList<int[]>();
        open_list.offer(new int[]{startRow, startColumn});
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        int steps = 0;
        while (!open_list.isEmpty()) {
            
            int size = open_list.size();
            System.out.println();
            for (int i = 0; i < size; i++) {
                int[] cell = open_list.poll();
                int row = cell[0], column = cell[1];
                System.out.println("Steps "+steps +"  row "+row+" col "+column);
                if (grid[row][column] == 'G'){
                    return steps;
                }
                for (int[] direction : directions) {
                    
                    int newRow = row + direction[0], newColumn = column + direction[1];
                    System.out.println("Direction :"+newRow+"  "+newColumn);
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && grid[newRow][newColumn] != '#' && !closed_list[newRow][newColumn]) {
                        closed_list[newRow][newColumn] = true;
                        open_list.offer(new int[]{newRow, newColumn});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception{
        Scanner read = new Scanner(System.in);
        Maze maze = new Maze();
        System.out.println("Enter Size of the Grid :");
        int row = read.nextInt();
        int col = read.nextInt();

        char[][] grid = new char[row][col];
        System.out.println("Fill the Grid :");
        for(int i= 0;i < row;i++){
            for(int j = 0; j < col;j++){
                grid[i][j] = read.next().charAt(0);
            }
        }
        System.out.println("Shortest path is : "+ maze.getFood(grid));

    }
}
