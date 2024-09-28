package repositories;

import entities.Admin;
import interfaces.IAdminRepo;

import java.util.ArrayList;
import java.util.List;

public class AdminRepo implements IAdminRepo {
    private final String file = "repositories/data/admins.txt";
    private final FileIO fio = new FileIO();

    @Override
    public void addAdmin(Admin a) {
        List<Admin> admins = this.getAllAdmin(); // Get existing admins
        admins.add(a); // Add new admin

        // Prepare data for writing back to the file
        String[] data = new String[admins.size()];
        for (int i = 0; i < admins.size(); i++) {
            data[i] = admins.get(i).toString(); // Convert Admin objects to strings
        }
        fio.writeFile(this.file, data); // Write to file
    }

    @Override
    public void updateAdmin(Admin a) {
        List<Admin> admins = this.getAllAdmin(); // Get existing admins
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getUserId().equals(a.getUserId())) {
                admins.set(i, a); // Update admin
                break; // Exit after updating the specified admin
            }
        }

        // Prepare data for writing back to the file
        String[] data = new String[admins.size()];
        for (int i = 0; i < admins.size(); i++) {
            data[i] = admins.get(i).toString(); // Convert Admin objects to strings
        }
        fio.writeFile(this.file, data); // Write to file
    }

    @Override
    public void deleteAdmin(String id) {
        List<Admin> admins = this.getAllAdmin(); // Get existing admins
        admins.removeIf(admin -> admin.getUserId().equals(id)); // Remove admin by userId

        // Prepare data for writing back to the file
        String[] data = new String[admins.size()];
        for (int i = 0; i < admins.size(); i++) {
            data[i] = admins.get(i).toString(); // Convert Admin objects to strings
        }
        fio.writeFile(this.file, data); // Write to file
    }

    @Override
    public Admin searchAdmin(String id) {
        List<Admin> admins = this.getAllAdmin(); // Get existing admins
        for (Admin admin : admins) {
            if (admin.getUserId().equals(id)) {
                return admin; // Return the found admin
            }
        }
        return null; // Return null if not found
    }

    @Override
    public List<Admin> getAllAdmin() {
        String[] data = fio.readFile(this.file); // Read data from file
        List<Admin> admins = new ArrayList<>(); // List to hold admin objects

        for (String line : data) {
            if (line != null) {
                Admin admin = new Admin(); // Create a new admin instance
                admins.add(admin.formAdmin(line)); // Populate and add it to the list
            }
        }
        return admins; // Return the list of admin objects
    }
}
