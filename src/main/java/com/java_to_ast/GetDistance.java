package com.java_to_ast;

import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import com.opencsv.CSVWriter;

public class GetDistance {

    public static void main(String[] args) throws Exception {
        generateDistance();
    }

    /**
     * This method generates the distance between each wrong code to its closest right algorithm, and write the result
     * to a csv file for further analysis.
     *
     * @throws Exception
     */
    public static void generateDistance() throws Exception {
        String dir1 = "C:/Users/Elijah/Desktop/ELITE/Research-creativity/Files 1";
        String dir2 = "C:/Users/Elijah/Desktop/ELITE/Research-creativity/Files 2";
        List<String> files1 = Tree2.importfiles(Paths.get(dir1), "");
        List<String> files2 = Tree2.importfiles(Paths.get(dir2), "");
        int curDis;
        double[][] score_distance = new double[files2.size()][1];
        int count = 0;
        List<String> files1Names = new ArrayList<>();
        for (String file1 : files1) {
            int min = Integer.MAX_VALUE;
            String minFile;
            for (String file2 : files2) {
                Tree2 file_1_tree = new Tree2(file1);
                Tree2 file_2_tree = new Tree2(file2);
                curDis = Tree2.ZhangShasha(file_1_tree, file_2_tree);
                if (curDis < min) {
                    min = curDis;
                    minFile = file2;
                }
            }
            String[] filepath = file1.split("\\");
            files1Names.add(filepath[filepath.length-1]);
            score_distance[count][0] = min * 1.0;
            count++;
        }

        CSVWriter writer = new CSVWriter(new FileWriter("C:/Users/Elijah/Desktop/ELITE/Research-creativity/Results/data.csv", false));
        writer.writeNext(new String[]{"File Path", "Distance"});
        for (int i = 0; i < score_distance.length; i++) {
            String[] tmp = {files1Names.get(i) + "", score_distance[i][0] + ""};
            writer.writeNext(tmp);
        }
        writer.close();

    }
}