package com.cyk;

import java.util.ArrayList;
import java.util.List;

public class Build {

    public static List<Grammar> Grammars = new ArrayList<>();

    private static String[][] CykTable =
            new String[Input.InputString.length() + 1][Input.InputString.length()];

    private static List<String> nonTerminals = new ArrayList<>();

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

    private static void Success(){
        if(CykTable[0][Input.InputString.length() - 1].contains("S")){
            System.out.println("Levezetheto a\t" + Input.InputString);
        }else {
            System.out.println("Nem vezetheto le a\t" + Input.InputString);
        }
    }

    private static void NonTerminal(){
        for(char c = 'a'; c <= 'z'; c++){
            for(Grammar g : Grammars){
                if(g.getValue().equals(c + "")){
                    nonTerminals.add(g.getValue());
                }
            }
        }
    }

    private static void SetUpCykTable(){
        for(int i = 0; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                CykTable[i][j] = "+";
            }
        }
    }

    private static void SetUpInputLine(){
        for(int i = 1; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                if(i == j + 1){
                    CykTable[i][j] = Input.InputString.charAt(i - 1) + "";
                }
            }
        }
    }

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

    private static void Write(){
        for(int i = 0; i < Input.InputString.length() + 1; i++){
            for(int j = 0; j < Input.InputString.length(); j++){
                System.out.print(CykTable[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
