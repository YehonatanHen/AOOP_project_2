package components;

import utilities.Timer;
import utilities.Utilities;
import GUI.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class simulate driving of number of vehicles based on the map.
 *
 * @author Yehonatan Hen-207630112
 * @author Rotem Librati-307903732
 * @see Timer
 * @see Utilities
 * @see Map
 * @see Vehicle
 */
public class Driving extends Thread implements Utilities, Timer {
    private Map map; //Map for current running
    private ArrayList<Vehicle> vehicles; //The vehicles who part of the running
    private int drivingTime; // Accumulate the time/number of pulses from the start
    private ArrayList<Timer> allTimedElements; //Keep the whole elements who affected by the time pulses.
    mainFrame mainFrame;

    /**
     * Driving constructor: receive number of junctions and
     * number of vehicles.
     * The constructor make new map with existed number of junctions,make number of
     * vehicles as demanded and initialize all of the fields.
     *
     * @param numOfJunctions
     * @param numOfVehicles
     */
    public Driving(int numOfJunctions,int numOfVehicles,mainFrame GUIFrame) {
        map = new Map(numOfJunctions);
        vehicles = new ArrayList<>();
        allTimedElements = new ArrayList<>();
        drivingTime = 0;
        Random r = new Random();
        mainFrame = GUIFrame;
        //Make random starting roads to vehicles based on the map.
        //Add the vehicles to allTimedElements
        System.out.println("================= CREATING VEHICLES =================");
        for (int i = 0; i < numOfVehicles; i++) {
            vehicles.add(new Vehicle(map.getRoads().get(r.nextInt(map.getRoads().size()))));
            allTimedElements.add(vehicles.get(i));
        }
        //Add the lights to allTimedElements only if junction is LightedJunction
        for (Junction j : map.getJunctions()) {
            if (j instanceof LightedJunction) allTimedElements.add(((LightedJunction) j).getLights());
        }
    }
    //getters


    public ArrayList<Timer> getAllTimedElements() {
        return allTimedElements;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getDrivingTime() {
        return drivingTime;
    }

    public Map getMap() {
        return map;
    }
     //setters


    public void setAllTimedElements(ArrayList<Timer> allTimedElements) {
        this.allTimedElements = allTimedElements;
    }

    public void setDrivingTime(final int drivingTime) {
        this.drivingTime = drivingTime;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Receive number of turns and run incrementDrivingTime function for each.
     *
     * @param numOfTurns
     */

    public void drive(int numOfTurns){
        System.out.println("\n"+toString()+"\n");
            for (Timer t : allTimedElements) {
                if (t instanceof Vehicle) ((Vehicle) t).start();
                if (t instanceof TrafficLights) ((TrafficLights) t).start();
            }
        while (numOfTurns >= drivingTime) {
            synchronized (this) {
                System.out.println("***************TURN" + drivingTime + "***************");
                incrementDrivingTime();
                drivingTime++;
                //suppose to update graphics every 100 millis
                mainFrame.run();
                }
            }
        }

    /**
     * Advance the pulses for Objects who affect by that.
     */
    public synchronized void incrementDrivingTime(){
        for(int i=0;i<allTimedElements.size();i++){
            if(i<vehicles.size()) {
                vehicles.get(i).setObjectCount(i+1);
                System.out.println(allTimedElements.get(i).toString());
            }
            if(i<allTimedElements.size() && i>=vehicles.size())
            if(allTimedElements.get(i) instanceof TrafficLights) ((TrafficLights)allTimedElements.get(i)).changeLights();
            allTimedElements.get(i).incrementDrivingTime();
            System.out.println();
        }
    }

    public String toString(){
        return "=====================START DRIVING====================";
    }


    public boolean equals(Object o){
        if(o instanceof Driving){
            return map.equals(((Driving)o).map) &&
            vehicles.equals(((Driving) o).vehicles) &&
            drivingTime==((Driving) o).drivingTime &&
            allTimedElements.equals(((Driving) o).allTimedElements);
        }
        return false;
    }
}
