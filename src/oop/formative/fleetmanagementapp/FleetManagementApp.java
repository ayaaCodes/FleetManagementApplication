package oop.formative.fleetmanagementapp;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import oop.fa1.vehicle.Vehicle;

public class FleetManagementApp {

    public static void main(String[] args) {
        String[] vehicleData = {
                "AB 45 CD GP#120000#12.5#50",
                "EF 98 GH GP#85000#14.2#40",
                "IJ 32 KL GP#150000#10.8#30",
                "MN 67 OP GP#200000#9.5#25",
                "QR 21 ST GP#95000#13.0#60",
                "UV 89 WX GP#50000#15.3#70",
                "YZ 10 AB GP#180000#11.7#35",
                "CD 55 EF GP#75000#14.5#45",
                "GH 77 IJ GP#110000#12.0#55",
                "KL 33 MN GP#95000#13.8#20",
                "OP 44 QR GP#175000#10.0#40",
                "ST 66 UV GP#135000#12.2#50"
        };   
        
        ArrayList<Vehicle> vehicles = new ArrayList<>();
       
        for(String data : vehicleData){
            String[] parts = data.split("#");
            vehicles.add(new Vehicle(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]), Integer.parseInt(parts[3])));
        }

        // Test Adding
        JOptionPane.showMessageDialog(null, addVehicle(vehicles, new Vehicle("KW 78 PW GP", 12500, 16.8, 14)), "ADDING NEW VEHICLE", JOptionPane.PLAIN_MESSAGE);
        
        // Test Removing
        String delete = JOptionPane.showInputDialog(null, "Enter the vehicle license number to remove:", "LICENSE NUMBER", JOptionPane.QUESTION_MESSAGE);
        if (delete != null && removeVehicle(vehicles, delete)) {
            JOptionPane.showMessageDialog(null, "Vehicle was successfully removed", "REMOVE VEHICLE", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Vehicle not found in fleet.", "REMOVE VEHICLE", JOptionPane.ERROR_MESSAGE);
        }
        
        // Test Most Driven
        Vehicle most = getMostDrivenVehicle(vehicles);
        JOptionPane.showMessageDialog(null, "Vehicle with the most kilometers:\nLicense: " + 
                most.getLicensePlate() + "\nKilometers: " + most.getKilometers(), "MOST DRIVEN VEHICLE", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String addVehicle(ArrayList<Vehicle> vehicles, Vehicle newVehicle) {
        // Check if it exists first
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(newVehicle.getLicensePlate())) {
                return "Record of license plate found, vehicle could not be added:\n" + newVehicle.getLicensePlate();
            }
        }
        // If the loop finishes without returning, the car is unique
        vehicles.add(newVehicle);
        return "Vehicle added successfully:\n" + newVehicle.getLicensePlate();
    }

    public static boolean removeVehicle(ArrayList<Vehicle> vehicles, String deletePlate) {
        // Use an Iterator to safely remove items while looping
        Iterator<Vehicle> iterator = vehicles.iterator();
        while (iterator.hasNext()) {
            Vehicle v = iterator.next();
            if (v.getLicensePlate().equalsIgnoreCase(deletePlate)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static Vehicle getMostDrivenVehicle(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) return null;
        
        Vehicle most = vehicles.get(0);
        for (Vehicle v : vehicles) {
            if (v.getKilometers() > most.getKilometers()) {
                most = v;
            }
        }
        return most;
    }
}