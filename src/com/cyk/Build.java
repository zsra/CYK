package com.cyk;

import java.util.ArrayList;
import java.util.List;

public class Build {

    /**
     * Contains Grmmars elements
     */
    public static List<Grammar> Grammars = new ArrayList<>();

    /**
     * This Matrix will be represt the CYK table. The elements
     * in the matrix make an uppper matrix
     */
    private static String[][] CykTable =
            new String[Input.InputString.length() + 1][Input.InputString.length()];

    /**
     * Collect all Non-Terminals in the grammar.
     */
    private static List<String> nonTerminals = new ArrayList<>();

    /**
     * Call all functions to set up the table.
     */
    public static void Run(){
        NonTerminal();
        SetUpCykTable();
        SetUpInputLine();
        ClearBottomTriangle();
        FirstRow();
        NextRows();
        Write();
        Success();
    }

    /**
     * Check to user's input accepted by grammar
     */
    private static void Success(){
        if(CykTable[0][Input.InputString.length() - 1].contains("S")){
            System.out.println("Levezetheto a\t" + Input.InputString);
        }else {
            System.out.println("Nem vezetheto le a\t" + Input.InputString);
        }
    }

    /**
     * Collecting all Non-Terminals in the grmmar
     * and add to {@link #NonTerminal()}
     */
    private static void NonTerminal(){
        for(char c = 'a'; c <= 'z'; c++){
            for(Grammar g : Grammars){
                if(g.getValue().equals(c + "")){
                    nonTerminals.add(g.getValue());
                }
            }
        }
    }

    /**
     * Init the Table. Fill all space with "+" characters
     */
    private static void SetUpCykTable(){
        for(int i = 0; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                CykTable[i][j] = "+";
            }
        }
    }

    /**
     * Input as Diagonal to make fancy the output table :)
     * and make +1 width table
     */
    private static void SetUpInputLine(){
        for(int i = 1; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                if(i == j + 1){
                    CykTable[i][j] = Input.InputString.charAt(i - 1) + "";
                }
            }
        }
    }

    /**
     * Delete all charachers on the bottom triangle, we don't need that.
     */
    private static void ClearBottomTriangle(){
        for(int i = 1; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                try {
                    if(i >=  j + 2){
                        CykTable[i][j] = "";
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }

            }
        }
    }

    /**
     *  Set the first line of the cyk table, in manually.
     */
    private static void FirstRow(){
        for(int i = 0; i < Input.InputString.length() + 1; i++){
            for(int j = 0; i < Input.InputString.length(); j++){
                try {
                    if(nonTerminals.contains(CykTable[i + 1][j])){
                        CykTable[i][j] = "";
                        for(Grammar g : Grammars){
                            if(g.getValue().equals(CykTable[i + 1][j])){
                                CykTable[i][j] += g.getKey();
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    break;
                }

            }
        }
    }

    /**
     * Generate all Combinations of Non-Terminals
     *
      * @param First Non-Terminals
     * @param Second Non-Teminals
     * @return return First Non-TerminalsSecond Non-Teminals string
     */
    private static List<String> AllCombinations(String s1, String s2){
        String[] arr1 = s1.split("");
        String[] arr2 = s2.split("");
        List<String> result = new ArrayList<>();
        for(int i = 0; i < s1.length(); i++){
            for(int j = 0; j < s2.length(); j++){
                result.add(arr1[i] + arr2[j]);
            }
        }
        return result;
    }

    /**
     * Based on first level and make a cyk table.
     */
    private static void NextRows(){
        for(int j = 0; j < Input.InputString.length(); j++){
            int stepper = 0;
            for(int i = 0; i < Input.InputString.length(); i++){
                if(CykTable[stepper][i].equals("+")){
                    int index_helper = 1;
                    String Builder = "";
                    while(!nonTerminals.contains(CykTable[stepper + index_helper][i])){
                        List<String> temp
                                = AllCombinations(
                                CykTable[stepper][i - index_helper],
                                CykTable[i + 1 - index_helper][i]);
                        index_helper++;
                        for(Grammar g : Grammars){
                            if(temp.contains(g.getValue())){

                                Builder+=g.getKey();
                            }
                        }
                    }
                    if(Builder.equals("")){
                        CykTable[stepper][i] = "-";
                    }
                    else {
                        CykTable[stepper][i] = Builder;
                    }
                    stepper++;
                }

            }
        }
    }

    /**
     * Print the Table to console.
     */
    private static void Write(){
        for(int i = 0; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                System.out.print(CykTable[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
