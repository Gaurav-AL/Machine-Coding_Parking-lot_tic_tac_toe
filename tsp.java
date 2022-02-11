
import java.util.*;
import java.util.Collections;
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
        int i=0;
        for( i = 0; i < traverse.size()-1;i++){
            int index = traverse.get(i);
            System.out.print(index + " ");
            System.out.print(cities.get(index).name +"-->");
            total_dist = euclidean_distance(cities.get(traverse.get(i)).longitude,cities.get(traverse.get(i+1)).latitude, cities.get(traverse.get(i)).longitude,cities.get(traverse.get(i+1)).longitude);    
        }
        System.out.print(traverse.get(i)+"  "+cities.get(traverse.get(i)).name + " :");
        System.out.print(total_dist);
        System.out.println();
        return total_dist;
    }
    public double minimizeDistance(){
        double total_dist = Double.MAX_VALUE;
        int temp = 150;
        
        while(temp-- != 0){
            System.out.print(temp +"  ");
            ArrayList<Integer> traverse = new ArrayList<>();
            for(int i = 0;  i < cities.size();i++){
                traverse.add(i);
            }      
            Collections.shuffle(traverse);
            //Our Minimum Distance is Stored in total_dist
          total_dist = Math.min(total_dist,calculateDistance(traverse));
        }
        return total_dist;
    }
    public static void main(String[] args) {
        ArrayList<City> cities = new ArrayList<>();
        City c1 = new City(160.00,170.00,"Gurgaon",0);
        City c2 = new City(180.00,1767.09,"Chambal",1);
        City c3 = new City(196.60,180.890,"Fulgaon",2);
        City c4 = new City(360.00,270.00,"Mahrajgang",3);
        City c5 = new City(185.0411,457.8824,"Guwahati",4);
        City c6 = new City(247.5023,299.4322,"Saravasti",5);
        City c7 = new City(701.3532,369.7156,"Kauapur",6);
        City c8 = new City(563.2718,442.3282,"Pachpedwa",7);
        City c9 = new City(383.8523,458.4757,"Gainsari",8);
        City c10 = new City(329.9402,740.9576,"Laibudwa",9);
        City c11 = new City(254.9820,302.2548,"Gainjhwa",10);
        City c12 = new City(195.8296,456.6559,"Butwal",11);
        City c13 = new City(193.0671,450.2405,"Farinda",12);
        City c14 = new City(200.7237,426.3461,"Barhni",13);
        City c15 = new City(200.5698,422.6481,"Koila Badsha",14);
        City c16 = new City(217.4682,434.3839,"Ramnagar",15);
        City c17 = new City(223.1549,439.8027,"Daulatpur",16);
        City c18 = new City(221.5852,442.8863,"DayaalPur",17);
        City c19 = new City(186.8032,449.9557,"Laal Ganj",18);
        City c20 = new City(264.57,410.328,"Pechaura",19);
        City c21 = new City(221.5852,442.8863,"Pani Ganj",20);

        
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);
        cities.add(c4);
        cities.add(c5);
        cities.add(c6);
        cities.add(c7);
        cities.add(c8);
        cities.add(c9);
        cities.add(c10);
        cities.add(c11);
        cities.add(c12);
        cities.add(c13);
        cities.add(c14);
        cities.add(c15);
        cities.add(c16);
        cities.add(c17);
        cities.add(c18);
        cities.add(c19);
        cities.add(c20);
        cities.add(c21);

        Tsp t = new Tsp(cities);
        System.out.println("Distance Matrix "+t.matrixgeneration());
        System.out.print("Minimized Distance is :"+t.minimizeDistance());
    }
}
