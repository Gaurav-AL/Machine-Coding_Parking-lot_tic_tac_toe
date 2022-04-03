import java.util.*;
// For operation we need to use Database....Make this one none 

class Floors{
    
    int no_of_slots_for_cars,
    no_of_slots_for_bikes,
    no_of_slots_for_trucks,
    floor_number;
    Map<Integer,Boolean> cars_slot_no = new HashMap<>();
    Map<Integer,Boolean> bikes_slot_no = new HashMap<>();
    Map<Integer,Boolean> truck_slot_no = new HashMap<>();
    Floors(Map<Integer,Boolean> cars_slot_no,Map<Integer,Boolean>  bikes_slot_no,Map<Integer,Boolean>  truck_slot_no,int floor_number){
        this.cars_slot_no = cars_slot_no;
        this.bikes_slot_no = bikes_slot_no;
        this.truck_slot_no = truck_slot_no;
        no_of_slots_for_cars = cars_slot_no.size();
        no_of_slots_for_bikes = bikes_slot_no.size();
        no_of_slots_for_trucks = truck_slot_no.size();
        this.floor_number = floor_number;
    }
    
}
class ParkingLot{
    HashMap<Integer,Floors> floors = new HashMap<>();
    HashMap<String,HashMap<String,String>> ticket_info = new HashMap<>();
   
    
    public void createParkingLot(int no_of_floors, int no_of_slots){  
        Map<Integer,Boolean> cars_slot_no = new HashMap<>();
        Map<Integer,Boolean> bikes_slot_no = new HashMap<>();
        Map<Integer,Boolean> truck_slot_no = new HashMap<>();
        for(int i = 1; i <= no_of_slots;i++){
            if(i > 3){
                
                cars_slot_no.put(i,false);
            }
            if(i == 1){
                
                truck_slot_no.put(i,false);
            }
            if(i > 1 && i <= 3){
                
                bikes_slot_no.put(i,false);
            }
        }
        for(int i = 0; i < no_of_floors; i++){
            floors.put((i+1),new Floors(cars_slot_no, bikes_slot_no,truck_slot_no,(i+1)));
        }
        
        System.out.println("Created Parking lot with "+no_of_floors +" floors and "+no_of_slots+" slots per floor.");
    }
    public void displayFreeCount(String vehicle_type){
        int len = floors.size();
        
        vehicle_type = vehicle_type.toLowerCase();
        
        for(int i = 0;i < len; i++){
            Floors floor = floors.get(i+1);
            if(vehicle_type.equals("truck")){
                int free_slots_for_truck = 0;
                int length = floor.truck_slot_no.size();
                if(floor.truck_slot_no.get(length) == false){
                    free_slots_for_truck = 1;
                }
                System.out.println("No. of free slots for TRUCK on Floor "+(i+1)+ " :" + free_slots_for_truck);
            }
            if(vehicle_type.equals("car")){
                int free_slots_for_cars = 0;
                int length = floor.cars_slot_no.size();
                for(int j = 4; j< (length + 4);j++){
                    if(floor.cars_slot_no.get(j) == false){
                        free_slots_for_cars += 1;
                    }
                }
                System.out.println("No. of free slots for CAR on Floor "+(i+1)+ " :" + free_slots_for_cars);
            }
            if(vehicle_type.equals("bike")){
                int free_slots_for_bikes = 0;
                int length = floor.bikes_slot_no.size();
                for(int j = 2; j< length+2;j++){
                    if(floor.bikes_slot_no.get(j) == false){
                        free_slots_for_bikes += 1;
                    }
                }
                System.out.println("No. of free slots for BIKE on Floor "+(i+1)+ " :" + free_slots_for_bikes);
            }
        }
    }
    public void displayFreeSlots(String vehicle_type){
        int len = floors.size();
        
        
        vehicle_type = vehicle_type.toLowerCase();
        for(int i = 0;i < len; i++){
            ArrayList<Integer> free_slots = new ArrayList<>();
            Floors floor = floors.get(i+1);

            if(vehicle_type.equals("truck")){
                
                int length = floor.truck_slot_no.size();
                if(floor.truck_slot_no.get(length) == false){
                    free_slots.add(length);
                }
                System.out.println("Free slots for TRUCK on Floor "+(floor.floor_number)+ " :" + free_slots);
            }
            if(vehicle_type.equals("car")){
                int length = floor.cars_slot_no.size();
                for(int j = 4; j< length+4;j++){
                    if(floor.cars_slot_no.get(j) == false){
                        free_slots.add(j);
                    }
                }
                System.out.println("Free slots for CAR on Floor "+(floor.floor_number)+ " :" + free_slots);
            }
            if(vehicle_type.equals("bike")){
                int length = floor.bikes_slot_no.size();
                for(int j = 2; j< length+2;j++){
                    if(floor.bikes_slot_no.get(j) == false){
                        free_slots.add(j);
                    }
                }
                System.out.println("Free slots for BIKE on Floor "+(floor.floor_number)+ " :" + free_slots);
                
            }
          
        }
    }
    public void displayOccupiedSlots(String vehicle_type){
        int len = floors.size();
        
        vehicle_type =  vehicle_type.toLowerCase();
        
        for(int i = 0;i < len; i++){
            ArrayList<Integer> occupied_slots = new ArrayList<>();
            Floors floor = floors.get(i+1);
            if(vehicle_type.equals("truck")){
                
                int length = floor.truck_slot_no.size();
                if(floor.truck_slot_no.get(length) == true){
                    occupied_slots.add(length);
                }
                System.out.println("Occupied slots for TRUCK on Floor "+(floor.floor_number)+ " :" + occupied_slots);
            }
            if(vehicle_type.equals("car")){
                int length = floor.cars_slot_no.size();
                for(int j = 4; j< length+4;j++){
                    if(floor.cars_slot_no.get(j) == true){
                        occupied_slots.add(j);
                    }
                }
                System.out.println("Occupied slots for CAR on Floor "+(floor.floor_number)+ " :" + occupied_slots);
            }
            if(vehicle_type.equals("bike")){
                int length = floor.bikes_slot_no.size();
                for(int j = 2; j< length+2;j++){
                    if(floor.bikes_slot_no.get(j) == true){
                        occupied_slots.add(j);
                    }
                }
                System.out.println("Occupied slots for BIKE on Floor "+(floor.floor_number)+ " :" + occupied_slots);
                
            }
        }
    }
    public void parkVehicle(String vehicle_type,String reg_no,String color){
        int floor_no = 0,slot_no = 0;
        String park_id ="";
        vehicle_type = vehicle_type.toLowerCase();
        int no_of_floor = floors.size();
        for(int i = 0 ;i < no_of_floor; i++){
            Floors floor = floors.get(i+1);
            // System.out.println(floor);
            int flag = 0;
            if(vehicle_type.equals("car")){
            int length = floor.cars_slot_no.size();
                for(int j = 4; j< length+4;j++){
                    if(floor.cars_slot_no.get(j) == false){
                        flag = 1;
                        floor_no = (floor.floor_number);
                        slot_no = j;
                        // System.out.println("floor "+floor_no +"  slot_no "+slot_no);
                        floor.cars_slot_no.replace(j,true);
                        break;
                    }
                }         
        }
        
        if(vehicle_type.equals("bike")){
            int length = floor.bikes_slot_no.size();
                for(int j = 2; j< length+2;j++){
                    if(floor.bikes_slot_no.get(j) == false){
                        flag= 1;
                        floor_no = (floor.floor_number);
                        slot_no = j;
                        floor.bikes_slot_no.replace(j,true);
                        break;
                    }
                }        
        }
        if(vehicle_type.equals("truck")){
            int length = floor.truck_slot_no.size();
                if(floor.truck_slot_no.get(length) == false){
                        flag = 1;
                        floor_no = (floor.floor_number);
                        slot_no = length;
                        floor.bikes_slot_no.replace(length,true);
                        break;
                }                                            
        }
        if(flag == 0 && i == (no_of_floor -1)){
            System.out.println("Parking Lot Full ");
        }
        if(flag == 1){ // for handling latest floor and slot
                HashMap<String,String> ticket_id = new HashMap<>();
                park_id = String.format("PR1234_%d_%d", floor_no,slot_no);
                ticket_id.put(reg_no,color);
                ticket_info.put(park_id,ticket_id);
                System.out.println("Parked vehicle. Ticket ID: "+park_id);
            break;
        }
    }
    


    if(floors.get(0) == floors.get(1)){
        System.out.println("true");
    }
    }
    public void unparkVehicle(String park_id){
        
        String[] ticket = park_id.split("_");
        int floor_no = Integer.valueOf(ticket[1]);
        int slot_no = Integer.valueOf(ticket[2]);
        
        if(ticket_info.containsKey(park_id)){
            Floors floor = floors.get(floor_no);
            if(slot_no == 1){
                floor.truck_slot_no.replace(slot_no,false);
            }
            if(slot_no >= 2 && slot_no < 4){
                floor.bikes_slot_no.replace(slot_no,false);
            }
            else{
                floor.cars_slot_no.replace(slot_no,false);
            }
            System.out.println("Unparked vehicle with Registration Number:"+ ticket_info.get(park_id).keySet()+" and Color: "+ ticket_info.get(park_id).values());
        }
        else{
            System.out.println("Invalid Ticket ID");
        }
    }
      public static void main(String... parking) throws Exception{
       
        ParkingLot parking_lot = new ParkingLot();
        String vehicle_type = "",reg_no= "",color = "";
        String input = "";
        String[] operation;
        Scanner read = new Scanner(System.in);
        while(!input.equals("exit")){
            input= read.nextLine();
            operation = input.split(" ");
            
            if(operation[0].equals("create_parking_lot")){
            
            parking_lot.createParkingLot(Integer.valueOf(operation[2]),Integer.valueOf(operation[3]));
            }
        
        if(operation[0].equals("display")){
            
            if(operation[1].equals("free_count")){
                vehicle_type = operation[2];
                parking_lot.displayFreeCount(vehicle_type);
            }

            if(operation[1].equals("free_slots")){
                vehicle_type = operation[2];
                parking_lot.displayFreeSlots(vehicle_type);
            }

            if(operation[1].equals("occupied_slots")){
                vehicle_type = operation[2];
                parking_lot.displayOccupiedSlots(vehicle_type);
            }
        }

        if(operation[0].equals("park_vehicle")){
            vehicle_type = operation[1];
            reg_no = operation[2];
            color = operation[3];
            parking_lot.parkVehicle(vehicle_type,reg_no,color);
        }

        if(operation[0].equals("unpark_vehicle")){
            parking_lot.unparkVehicle(operation[1]);
        }
        if(operation[0].equals("exit")){
            System.exit(0);
            }
        }
    }
}
