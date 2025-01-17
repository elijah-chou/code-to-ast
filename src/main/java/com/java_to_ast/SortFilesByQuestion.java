package com.java_to_ast;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
public class SortFilesByQuestion {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:/Users/ecool/Desktop/code-answers-scores/code-answers-scores"); //replace with directory w/ text files
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String childPath = child.getAbsolutePath();
                String childName = child.getName();
                String cwdPath = "C:/Users/ecool/Desktop/code-answers-scores/code-answers-scores/";
                String[] split = childName.split("\\.");
                String newFolderPath = cwdPath + split[6];

                boolean makeFolder = new File(newFolderPath).mkdirs();

                String finalPath = newFolderPath + "/" + childName;

                Path temp = Files.move(Paths.get(childPath), Paths.get(finalPath));
                if (temp == null) {
                    System.out.println("Failed to move file" + childName);
                }
            }
        }
    }
}

