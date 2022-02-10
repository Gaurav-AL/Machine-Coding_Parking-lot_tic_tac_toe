import java.lang.reflect.Array;
import java.util.*;
class City{
    int index;
    double longitude,latitude;
    String name;
    City(double x,double y, String name,int index){
        this.longitude = x;
        this.longitude = y;
        this.name = name;
        this.index = index;
    }
}
class HillClimbing{
    
}
class Tsp{
    ArrayList<City> cities;
    Tsp(ArrayList<City> cities){
        this.cities  = cities;
    }
    public double euclidean_distance(double x1,double x2,double y1,double y2)
    {
        double res = Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
        return res;
    }
    public ArrayList<ArrayList<Double>> matrixgeneration(){
        ArrayList<ArrayList<Double>> distances = new ArrayList<>();
        
        double dist;
        for(int i = 0 ; i < cities.size();i++){
            ArrayList<Double> distance = new ArrayList<>();
            for (int j = 0 ; j < cities.size();j++){
                if(i == j){
                    dist = 0;
                }
                else{
                     dist = euclidean_distance(cities.get(i).latitude, cities.get(j).latitude, cities.get(i).longitude,cities.get(j).longitude);
                }
                distance.add(dist);
            }
            distances.add(distance);
        }
        return distances;
    }
    public double calculateDistance(ArrayList<Integer> traverse){
        double total_dist = 0.0;
        for(int i = 0; i < traverse.size()-1;i++){
            int index = traverse.get(i);
            System.out.print(cities.get(index).name +"-->");
            total_dist = euclidean_distance(cities.get(i).longitude,cities.get(i+1).latitude, cities.get(i).longitude,cities.get(i+1).longitude);    
        }
        System.out.print(total_dist);
        System.out.println();
        return total_dist;
    }
    public double minimizeDistance(){
        double total_dost = Double.MAX_VALUE;
        
    }
    public static void main(String[] args) {
        ArrayList<City> cities = new ArrayList<>();
        City c1 = new City(160.00,170.00,"Gurgaon",0);
        City c2 = new City(180.00,1767.09,"Chambal",1);
        City c3 = new City(196.60,180.890,"Fulgaon",2);
        City c4 = new City(360.00,270.00,"Mahrajgang",3);
        City c5 = new City(900.00,890.89,"Guwahati",4);
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);
        cities.add(c4);
        cities.add(c5);

        Tsp t = new Tsp(cities);
        System.out.println(t.matrixgeneration());
    }
}
