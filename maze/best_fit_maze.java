import java.util.*;
class Maps{
    int[] arr; int num;
    Maps(int[] arr,int num){
        this.arr = arr;
        this.num = num;
    }
    public int getnums(){
        return num;
    }
}
class Maze{
    ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
    Maze(ArrayList<ArrayList<Integer>> maze){
        this.maze = maze;
    }
    public int shortestPath(ArrayList<ArrayList<Integer>> maze, ArrayList<Integer> source,int row_size,int col_size){
            
            ArrayList<Maps> open_list = new ArrayList<>();
            System.out.println("Continue");
            boolean[][] closed_list = new boolean[row_size][col_size];
            int cur_row = source.get(0),cur_col = source.get(1);
            open_list.add(new Maps(new int[]{cur_row,cur_col},maze.get(cur_row).get(cur_col)));
            int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
            closed_list[cur_row][cur_col] = true;
            int steps = 0;
            while(!open_list.isEmpty()){
                int size = open_list.size();
                for (int i = 0; i < size; i++) {
                    Maps curr = open_list.get(i);
                    int row = curr.arr[0], col = curr.arr[1];
                    System.out.println(" No Error ");
                    open_list.remove(0);
                    System.out.println("Steps :"+steps +"  row "+row+" col "+col);
                    if(curr.num == 100){
                        System.out.println("Reached : row "+row+" col "+col);
                        return steps;
                    }
                    for (int[] direction : directions) {
                    
                        int newRow = row + direction[0], newColumn = col + direction[1];
                    System.out.println("row :"+newRow+" col :"+newColumn);
                if(newRow >= 0 && newColumn >= 0 && newRow < row_size && newColumn < col_size){
                    if(closed_list[newRow][newColumn] == false && maze.get(newRow).get(newColumn) != 10){
                        closed_list[newRow][newColumn] = true;
                        open_list.add(new Maps(new int[]{newRow,newColumn},maze.get(newRow).get(newColumn)));
                    }
                }
            }
            Collections.sort(open_list,Comparator.comparing(Maps::getnums));
            for(Maps m :open_list){
                System.out.println("arr row :"+m.arr[0]+" arr col :"+m.arr[1]+" val :"+m.num);
            }
            System.out.println(" open_list "+open_list);
            
            }
            steps++;
        }
        return steps;
    }

    public ArrayList<Integer> getSource(ArrayList<ArrayList<Integer>> maze,int row){
        ArrayList<Integer> source = new ArrayList<>();
        int col;
        for(int i = 0; i < row;i++){
            if(maze.get(i).contains(0)){
                col = maze.get(i).indexOf(0);
                source.add(i);
                source.add(col);
                break;
            }
        }
        return source;
    }
    public static void main(String... args) throws Throwable{
        Scanner read = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> maze = new ArrayList<>();
        System.out.println("Enter row and col of the maze :");
        int row = read.nextInt();
        int col = read.nextInt();
        ArrayList<Integer> source = new ArrayList<>();
        System.out.println("Enter the Maze :");
        for(int i = 0 ; i < row; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0; j < col;j++){
                temp.add(read.nextInt());
            }
            maze.add(temp);
        }
        Maze m = new Maze(maze);
        source = m.getSource(maze,row);
        System.out.println("Started");
        System.out.println("Shortest path :"+m.shortestPath(maze,source,row,col));
    }
}
