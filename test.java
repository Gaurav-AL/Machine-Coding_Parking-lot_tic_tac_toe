import java.util.*;
class Test{
    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0 ; i < 9;i++)
            words.add(read.next());
        System.out.println(words);
    }
}
