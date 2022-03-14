import java.util.ArrayList;
import java.util.*;
class PuzzleConfig{
    ArrayList<ArrayList<String>> puzzle;
    int levels = Integer.MAX_VALUE,heuristics = Integer.MAX_VALUE,total;
    // f = levels + heuristics
    PuzzleConfig(ArrayList<ArrayList<String>> puzzle){
        this.puzzle = puzzle;
    }
}
class EightPuzzle {
    ArrayList<ArrayList<String>> goal_state = new ArrayList<>();
    EightPuzzle(ArrayList<ArrayList<String>> goal_state){
        this.goal_state = goal_state;
    }
    // Very Important Method to avoid call by value nature of ArrayList
    public ArrayList<ArrayList<String>> copyArrayList(ArrayList<ArrayList<String>> in_puzzle){
        ArrayList<ArrayList<String>> out_puzzle = new ArrayList<>();
        for(int i= 0;i < in_puzzle.size();i++){
            ArrayList<String> config = new ArrayList<>();
            for(int j = 0; j < in_puzzle.size();j++){
                config.add(in_puzzle.get(i).get(j));
            }
            out_puzzle.add(config);
        }
        return out_puzzle;
    }
    // It get Heurisitics at each level
    public int getHeuristics(ArrayList<ArrayList<String>> ques_puzzle){
        int heuristics = 0;
        for(int i = 0; i < ques_puzzle.size();i++){
            for(int j = 0; j < ques_puzzle.get(i).size();j++){
                if(!ques_puzzle.get(i).get(j).equals(goal_state.get(i).get(j)) && ques_puzzle.get(i).get(j).length() != 0){
                    heuristics += 1;
                }
            }
        }
        return heuristics;
    }
    
    public ArrayList<Integer> getFreeSpace(ArrayList<ArrayList<String>> puzzle){
        ArrayList<Integer> pair = new ArrayList<>(2);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(puzzle.get(i).get(j).length() == 0){
                    pair.add(i);
                    pair.add(j);
                    return pair;
                }
            }  
        }
        return pair;
    }
    public ArrayList<ArrayList<String>> leftShiftingBlocks(ArrayList<ArrayList<String>> new_puzzle,int row, int col){
        
     
      
        String rep_val = new_puzzle.get(row).get(col-1); 
        new_puzzle.get(row).set(col,rep_val);
        new_puzzle.get(row).set(col-1,"");
        // System.out.print("New Puzzle left:"+new_puzzle);
        return new_puzzle;
    }
    public  ArrayList<ArrayList<String>> rightShiftingBlocks(ArrayList<ArrayList<String>> new_puzzle,int row,int col){
   
        
        String rep_val = new_puzzle.get(row).get(col+1); 
        new_puzzle.get(row).set(col,rep_val);
        new_puzzle.get(row).set(col+1,"");
        // System.out.print("New Puzzle right:"+new_puzzle);
        return new_puzzle;
    }
    public  ArrayList<ArrayList<String>> upShiftingBlocks(ArrayList<ArrayList<String>> new_puzzle,int row,int col){
        
        
        
        String rep_val = new_puzzle.get(row-1).get(col); 
        new_puzzle.get(row).set(col,rep_val);
        new_puzzle.get(row-1).set(col,"");
        // System.out.print("New Puzzle up:"+new_puzzle);
        
        return new_puzzle;
    }
    public  ArrayList<ArrayList<String>> downShiftingBlocks(ArrayList<ArrayList<String>> new_puzzle,int row,int col){
      
     
        
        String rep_val3 = new_puzzle.get(row+1).get(col); 
        new_puzzle.get(row).set(col,rep_val3);
        new_puzzle.get(row+1).set(col,"");
        // System.out.print("New Puzzle down:"+new_puzzle);
        return new_puzzle;
    }
    public void aStarAlgo(ArrayList<ArrayList<String>> initial_config){
        
        ArrayList<Integer> pair = new ArrayList<>();
        int heuristics = getHeuristics(initial_config);
        int levels = 0;
        int intital_heu = Integer.MAX_VALUE;
        PuzzleConfig puzzle = new PuzzleConfig(initial_config);
        puzzle.levels = levels;
        puzzle.heuristics = heuristics;
        puzzle.total = puzzle.levels + puzzle.heuristics;
        pair = getFreeSpace(puzzle.puzzle);
        System.out.print("Initial Configuration :"+puzzle.puzzle);
        int row = pair.get(0);
        int col = pair.get(1);
        while(!puzzle.puzzle.equals(goal_state)){
            ArrayList<ArrayList<String>> init_puzzle = new ArrayList<>();
            PuzzleConfig final_p = new PuzzleConfig(puzzle.puzzle);
            levels += 1;
            if(row+1 < 3){
                init_puzzle = copyArrayList(puzzle.puzzle);
                System.out.println("\nBefore Down Shift :"+init_puzzle+" row :" + row+" col :"+col);
                
                ArrayList<ArrayList<String>> down = new ArrayList<>();
                down = downShiftingBlocks(init_puzzle, row, col);
                
                PuzzleConfig down_p = new PuzzleConfig(down);
                int heuristic1 = getHeuristics(down_p.puzzle);
                down_p.levels = levels;
                down_p.heuristics = heuristic1;
                down_p.total = down_p.levels + down_p.heuristics; 

                System.out.println("\nAfter Down Shift :"+down + "Total Heuristics :" + down_p.total);
                if(down_p.heuristics < intital_heu){
                    final_p = down_p;
                    final_p.heuristics = down_p.heuristics;
                }
                
                System.out.println();                   
            }
            if(row-1 >= 0){
                init_puzzle = copyArrayList(puzzle.puzzle);
                System.out.println("\nBefore Up Shift :"+init_puzzle+" row :" + row+" col :"+col);
                ArrayList<ArrayList<String>> up = new ArrayList<>();
                up = upShiftingBlocks(init_puzzle, row, col);
                
                PuzzleConfig up_p = new PuzzleConfig(up);
                int heuristic2 = getHeuristics(up);
                
                up_p.levels = levels;
                up_p.heuristics = heuristic2;
                up_p.total = up_p.levels + up_p.heuristics; 
                System.out.println("After Left Shift :"+up + "Total Heuristics :" + up_p.total);
                if(up_p.heuristics < intital_heu && up_p.heuristics < final_p.heuristics){
                    final_p.heuristics = up_p.heuristics;
                    final_p = up_p;
                }
                System.out.println();   
                                
            }
            if(col-1 >= 0){
                init_puzzle = copyArrayList(puzzle.puzzle);
                System.out.println("\nBefore Left Shift :"+init_puzzle+" row :" + row+" col :"+col);
                ArrayList<ArrayList<String>> left = new ArrayList<>();
                left = leftShiftingBlocks(init_puzzle,row, col);
                
                PuzzleConfig left_p = new PuzzleConfig(left);
                int heuristic3 = getHeuristics(left_p.puzzle);
                
                left_p.levels = levels;
                left_p.heuristics = heuristic3;
                left_p.total = left_p.levels + left_p.heuristics; 
                
                System.out.println("After Left Shift :"+left + "Total Heuristics :" + left_p.total); 
                if(left_p.heuristics < intital_heu && left_p.heuristics < final_p.heuristics){
                    final_p.heuristics = left_p.heuristics;
                    final_p = left_p;
                }
                System.out.println();                
            }
            if(col+1 < 3){
                init_puzzle = copyArrayList(puzzle.puzzle);
                System.out.println("\nBefore Right Shift :"+init_puzzle+ " row :" + row+" col :"+col);
                ArrayList<ArrayList<String>> right = new ArrayList<>();
                right = rightShiftingBlocks(init_puzzle,row, col);
                
                PuzzleConfig right_p = new PuzzleConfig(right);
                int heuristic4 = getHeuristics(right_p.puzzle);
                
                right_p.levels = levels;
                right_p.heuristics = heuristic4;
                right_p.total = right_p.levels + right_p.heuristics; 
                System.out.println("After Right Shift :"+right + "Total Heuristics :" + right_p.total);
                if(right_p.heuristics < intital_heu && right_p.heuristics < final_p.heuristics){
                    final_p.heuristics = right_p.heuristics;
                    final_p = right_p;
                }
                System.out.println();
                                     
            }
            
            // We have to get the right configuration after Each step.....Code is not up to the mark
            // After this we have to take execute it till new state become equals to goal state
            puzzle.puzzle = final_p.puzzle;
            pair = getFreeSpace(puzzle.puzzle);
            row = pair.get(0);
            col = pair.get(1);

            if(puzzle.heuristics == 0)
            break;
            System.out.println("############################################ ::::::"+final_p.puzzle);

            
        }


    }
  
    public static void main(String[] args) throws Throwable {
        ArrayList<ArrayList<String>> goal_state = new ArrayList<>();
        ArrayList<ArrayList<String>> initial_state = new ArrayList<>();
        Scanner read = new Scanner(System.in);
        System.out.println("Enter Goal State :");
        for(int i = 0; i < 3;i++){
            ArrayList<String> config = new ArrayList<>();
            for(int j = 0; j < 3; j++){   
                config.add(read.nextLine());
            } 
            goal_state.add(config); 
             
        }
        System.out.println("Defined Goal State :"+goal_state);
        EightPuzzle eight_puzzle = new EightPuzzle(goal_state);
        
        System.out.println("Enter Intitial State :");
        for(int i = 0; i < 3;i++){
            ArrayList<String> config = new ArrayList<>();
            for(int j = 0; j < 3; j++){
                config.add(read.nextLine());
            }
            initial_state.add(config);
               
        }
        eight_puzzle.aStarAlgo(initial_state);
       
    }
}
