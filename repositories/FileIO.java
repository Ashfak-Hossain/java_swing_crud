package repositories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    // Method to create a new file if it doesn't already exist
    public void createFile(String file) {
        File f = new File(file);
        try {
            if (f.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    // Method to read a file and return its contents as an array of strings
    public String[] readFile(String file) {
        List<String> data = new ArrayList<>(); // Use List for dynamic sizing
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        // Convert List to Array
        return data.toArray(new String[0]);
    }

    // Method to write an array of strings to a file
    public void writeFile(String file, String[] data) {
        try (FileWriter fwr = new FileWriter(file)) {
            for (String line : data) {
                if (line != null) {
                    fwr.write(line + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
