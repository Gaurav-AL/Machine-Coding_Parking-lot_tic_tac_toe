import java.util.*;
class Maze {
    public int getFood(char[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        boolean[][] visited = new boolean[rows][columns];
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
        visited[startRow][startColumn] = true;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{startRow, startColumn});
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        int steps = 0;
        while (!queue.isEmpty()) {
            System.out.println("Steps"+steps);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                if (grid[row][column] == 'G'){
                    return steps;
                }
                for (int[] direction : directions) {
                    
                    int newRow = row + direction[0], newColumn = column + direction[1];
                    // System.out.println("Direction :"+newRow+"  "+newColumn);
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && grid[newRow][newColumn] != '#' && !visited[newRow][newColumn]) {
                        visited[newRow][newColumn] = true;
                        queue.offer(new int[]{newRow, newColumn});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    public static void main(String[] args) throws Exception{
        Scanner read = new Scanner(System.in);
        SPF spf = new SPF();
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
        System.out.println("Shortest path is : "+ spf.getFood(grid));

    }
}
