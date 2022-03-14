class City{
    double longitude;
    double latitude;
    String name;
    City(double longitude, double lat, String name){
        this.latitude = lat;
        this.longitude = longitude;
        this.name= name;
    }
}
class StochasticHillClimbing{
    public double euclidean_distance(double x1,double x2, double y1, double y2){
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y2),2));
    }
    
    public static void main(String[] args) {
        
    }
}
