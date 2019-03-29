package com.cyk;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

    public static String StartSymbol;
    public static String InputString;

    /**
     * grammar location
     */
    private static String LocationOfGrammar = "grammar.txt";

    public static void Run(){
        GetInput();
        ReadFile();
    }

    /**
     * Get string input from console.
     */
    private static void GetInput(){
        System.out.println("Add input");
        Scanner scanner_input = new Scanner(System.in);
        InputString = scanner_input.next();
    }

    /**
     * Read grammar from text file
     */
    public static void ReadFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(LocationOfGrammar))) {
            StartSymbol = br.readLine();
            for(String line; (line = br.readLine()) != null; ) {
                String[] SplitedLine = line.split("->");
                Build.Grammars.add(new Grammar(SplitedLine[0], SplitedLine[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
