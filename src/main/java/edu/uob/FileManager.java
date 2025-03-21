package edu.uob;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileManager {

    //--todo: Reading .dot file --------------------------------------------------------
    public static LinkedList<String> readFile(String filePath) {
        File file = new File(filePath);
        LinkedList<String> lines = new LinkedList<>();

        // Check if the file exists before reading
        if (!file.exists()) {
            System.err.println("❌ Error: File not found at " + file.getAbsolutePath());
            return lines; // Return empty list
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line); // Store each line in LinkedList instead of ArrayList
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading .dot file: " + e.getMessage());
        }

        return lines;
    }
}