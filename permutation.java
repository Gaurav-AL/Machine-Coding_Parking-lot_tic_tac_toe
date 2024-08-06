import java.util.*;
class Permutation{
    static long time = 0;
    public static void permutations(String str, String prefix){
        if(str.length() == 0){
            System.out.println(prefix);
            time += 1;
        }   
        else{
            for(int i = 0 ; i < str.length(); i++){
                String rem = str.substring(0,i) + str.substring(i+1);
                permutations(rem,prefix+str.charAt(i));
                time += 1;
            }
        }
    }
    public static void main(String...  args) throws Exception{
        Scanner read = new Scanner(System.in);
        permutations("Ramesh", "");
        System.out.print("total time taken = "+ time);
    }
}

