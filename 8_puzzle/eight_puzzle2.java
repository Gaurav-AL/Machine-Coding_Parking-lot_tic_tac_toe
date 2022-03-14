import java.util.*;
class EightPuzzle{
	
		public static ArrayList<ArrayList<String>> copyArrayList(ArrayList<ArrayList<String>> in_puzzle){
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
	public static void main(String[] args) {
		 ArrayList<ArrayList<String>> initial_state = new ArrayList<>();
		 ArrayList<ArrayList<String>> initial = new ArrayList<>();
		 Scanner read = new Scanner(System.in);
		for(int i = 0; i < 3;i++){
            ArrayList<String> config = new ArrayList<>();
            for(int j = 0; j < 3; j++){
                config.add(read.nextLine());
            }
            initial_state.add(config);
               
        }
		System.out.println(initial_state);
		initial = copyArrayList(initial_state);
		for(int i= 0;i < 3;i++){
            
            for(int j = 0; j < 3;j++){
                initial.get(i).set(j,"3");
            }
            
        }
        System.out.println(initial_state);
        System.out.println(initial);

	}
}
