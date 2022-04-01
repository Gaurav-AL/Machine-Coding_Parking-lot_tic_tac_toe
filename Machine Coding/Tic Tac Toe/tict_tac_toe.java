import java.util.*;
class Pairs{
    int row,col;
    Pairs(int row,int col){
        this.row = row;
        this.col = col;
    }
}
class TicTacToe{
    Map<String,String> tictactoe = new LinkedHashMap<>();
    TicTacToe(Map<String,String> tictactoe){
        this.tictactoe = tictactoe;
    }
    public boolean invalidMoves(){
        return (tictactoe.size() == 9)? false:true;
    } 
    public boolean won(ArrayList<String> players){
        // diagonal Checked
        if((players.contains("00") && players.contains("11") && players.contains("22")) || (players.contains("02") && players.contains("11") && players.contains("20"))){
        return true;
        }
        Map<Character,Integer> check_col = new HashMap<>();
        //column check
        if(players.size()>0){
        
        for(String moves:players){
            char curr_char = moves.charAt(1);
             check_col.put(curr_char, check_col.getOrDefault(curr_char,0)+1);
             
             if(check_col.get(curr_char) == 3) 
             return true;  
        }
        
        Map<Character,Integer> check_row = new HashMap<>();
        for(String moves:players){
            char curr_char = moves.charAt(0);
            check_row.put(curr_char, check_row.getOrDefault(curr_char,0)+1);
           
            if(check_row.get(curr_char) == 3) 
            return true; 
        }
    }
        return false;
    }
    public String winner(Map<String,String> tictactoe){
        int count = 0;
        Map<String,String> tictac = new LinkedHashMap<>();
        ArrayList<String> player1 = new ArrayList<String>();
        ArrayList<String> player2 = new ArrayList<String>();
        for ( Map.Entry<String, String> entry : tictactoe.entrySet()) {
            
            String key = entry.getKey();
            String value = entry.getValue();
            
            tictac.put(key,value);
            if(count%2 == 0){
                System.out.println("Player 1 :");
                player1.add(key);      
            }
            else{
                System.out.println("Player 2 :");
                player2.add(key);    
            }
            count += 1;
            dislayConfig(tictac);
            if(won(player1)==true){
                System.out.println("Player 1 Won");
                System.exit(0);
            }
            if(won(player2)==true){
                System.out.println("Player 2 Won");
                System.exit(0);
            }

        }
        return "Tied !";
    }
    public void dislayConfig(Map<String,String> tictactoe){
        System.out.println("\n--------------");
        for(int i = 0; i < 3;i++){
            System.out.print("| ");
            for(int j = 0; j < 3 ; j++){
                System.out.print(tictactoe.getOrDefault(i+""+j,"_"));
                System.out.print(" | ");
            }
            System.out.println("\n--------------");
        }
    }
    public static void main(String... args ) throws Exception{
        Scanner read = new Scanner(System.in);
        int init;
        String first,second;
        ArrayList<Pairs> moves = new ArrayList<>();
        Map<String,String> tictactoe = new LinkedHashMap<>();
        System.out.println("Player 1 : X\nPlayer 2 : O\nChoose Intiates First Player 1 or Player 2 \n(Enter 1 or 2)");
        init = read.nextInt();
        if(init == 1){
            first = "X";
            second = "O";
        }else{
            first = "O";
            second = "X";
        }
        System.out.println("Enter Moves of Both Player In Alternate One By One (Enter row and col value in Pairs (0-2)):");
        for(int i = 0; i  < 9; i++){
            moves.add(new Pairs(read.nextInt(),read.nextInt()));
        }
        for(int i =0 ;i < 9 ;i ++){
            if(i % 2 == 0)
            tictactoe.put(moves.get(i).row+""+moves.get(i).col,first);
            else
            tictactoe.put(moves.get(i).row+""+moves.get(i).col,second);
        }
        TicTacToe ttt = new TicTacToe(tictactoe);
        if(ttt.invalidMoves() == true){
            System.out.println("Don't Play in Already Filled Cell ");
            System.exit(0);
        }
        System.out.print(ttt.winner(tictactoe));        
    }
}
