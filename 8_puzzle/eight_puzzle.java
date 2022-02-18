import java.util.ArrayList;

class PuzzleConfig{
    ArrayList<ArrayList<Integer>> puzzle;
    PuzzleConfig(ArrayList<ArrayList<Integer>> puzzle){
        this.puzzle = puzzle;
    }
}
class EightPuzzle {
    public int getHeuristics(ArrayList<ArrayList<Integer>> ques_puzzle, ArrayList<ArrayList<Integer>> goal_puzzle){
        int heuristics = ques_puzzle.length;
        for(int i = 0; i < ques_puzzle.size();i++){
            for(int j = 0; j < ques_puzzle.get(i).size();j++){
                if(ques_puzzle.get(i).get(j) != goal_puzzle.get(i).get(j))
                    heuristics += 1;
            }
        }
        return heuristics;
    }
    
}
