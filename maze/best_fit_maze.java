import java.util.*;
class Maze{
    ArrayList<ArrayList<String>> maze = new ArrayList<>();
    Maze(ArrayList<ArrayList<String>> maze){
        this.maze = maze;
    }
    public ArrayList<String> shortestPath(ArrayList<ArrayList<String>> maze, ArrayList<Integer> source,int row_size,int col_size){
            
        
    }
    public ArrayList<Integer> getSource(ArrayList<ArrayList<String>> maze,int row){
        ArrayList<Integer> source = new ArrayList<>();
        int col;
        for(int i = 0; i < row;i++){
            if(maze.get(i).contains("S")){
                col = maze.get(i).indexOf("S");
                source.add(i);
                source.add(col);
                break;
            }
        }
        return source;
    }
    public static void main(String... args) throws Throwable{
        Scanner read = new Scanner(System.in);
        ArrayList<ArrayList<String>> maze = new ArrayList<>();
        System.out.println("Enter row and col of the maze :");
        int row = read.nextInt();
        int col = read.nextInt();
        ArrayList<Integer> source = new ArrayList<>();
        System.out.println("Enter the Maze :");
        for(int i = 0 ; i < row; i++){
            ArrayList<String> temp = new ArrayList<>();
            for(int j = 0; j < col;j++){
                temp.add(read.next());
            }
            maze.add(temp);
        }
        Maze m = new Maze(maze);
        source = m.getSource(maze, row);

    }
}
