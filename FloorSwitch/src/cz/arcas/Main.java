/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.arcas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ucebna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int gemPosition;
        int hp = 50;
        int hpUp = -3;
        int hpDown = -2;
        int floor = 0;
        int score = 0;
        int highScore = 0;
        boolean debug = true;        
        
        
        programLoop:
        while (true){
            gemPosition = (int )(Math.random() * 50 + 1);
            if(debug) System.out.println("gemPosition: " + gemPosition);

            
            System.out.println("Vyber si startovní patro od 1 do 50");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                floor = Integer.parseInt(br.readLine());
            } catch (IOException ex) {
            } catch(NumberFormatException nfe) {  
                System.out.println("Patro musí být číslo.");
                continue;
            }  
            if(floor > 50 || floor < 1){
                System.out.println("Patro musí být čislo mezi 1 a 50 (včetně)");
                continue;
            }
            
            if(floor == gemPosition){
                System.out.println("Vyhrál si!");
                score += 100;
                if(score > highScore) highScore = score;
                System.out.println("Skore: " + score);
                System.out.println("High score: " + highScore);
                System.out.println("Chceš pokračovat? (A/N)");
                while(true){
                    String input = "NULL";
                    try { input = br.readLine(); } catch (IOException ex) {}
                    switch (input.toUpperCase()){
                        case "A":
                            System.out.println("Přičítám 50 životů");
                            hp += 50;
                        continue programLoop;

                        case "N":
                            score += hp;
                            System.out.println("Skore: " + score);
                        break programLoop;

                        default:
                            System.out.println("Chceč pokračovat? (A/N)");
                        break;
                    }
                }
            }
            
            if (debug) System.out.println("Selected floor: " + floor);
            
            System.out.println("Patra procházíš klávesami W - nahorů a S - dolů");
            System.out.println("Cesta nahorů = " + hpUp + " HP, cesta dolů = " + hpDown +" HP");
            
            gameLoop:
            while (hp > 0){
                System.out.println("Životy: " + hp);
                System.out.println("Patro: " + floor);
                String input = "null";
                try { input = br.readLine(); } catch (IOException ex) {}
                
                if(debug) System.out.println(input);
                
                switch (input.toUpperCase()){
                    case "W":
                        if(floor >= 50){
                            System.out.println("Dosaženo 50. patro");
                            continue gameLoop;
                        }
                        floor++;
                        hp += hpUp;
                    break;
                    
                    case "S":
                        if(floor <= 1){
                            System.out.println("Dosaženo 1. patro");
                            continue gameLoop;
                        }
                        floor--;
                        hp += hpDown;
                    break;
                    
                    default:
                        System.out.println("Patra prochazis klavesami W - nahoru a S - dolů");
                    continue gameLoop;
                }
                
                if(floor == gemPosition){
                    System.out.println("Vyhral si!");
                    score += 100;
                    if (score > highScore) highScore = score;
                    System.out.println("Hight score: " + highScore);
                    System.out.println("Chceš pokračovat? (A/N)");
                    while(true){
                        input = "NULL";
                        try { input = br.readLine(); } catch (IOException ex) {}
                        switch (input.toUpperCase()){
                            case "A":
                                System.out.println("Pricitam 50 zivotu");
                                hp += 50;
                            continue programLoop;

                            case "N":
                                score += hp;
                                System.out.println("Skore: " + score);
                            break programLoop;

                            default:
                                System.out.println("Chces pokracovat? (A/N)");
                            break;
                        }
                    }
                }
                
            }
            
            if(hp < 1){
                System.out.println("Prohrál si. Skore: " + score);
                if(highScore < score) highScore = score;
                System.out.println("High score: " + highScore);
                
                System.out.println("Chceš začít znova? (A/N)");
                String input;
                while(true){
                    input = "NULL";
                    try { input = br.readLine(); } catch (IOException ex) {}
                    switch (input.toUpperCase()){
                        case "A":
                            hp = 50;
                            score = 0;                            
                        continue programLoop;

                        case "N":
                            System.out.println(highScore);
                        break programLoop;

                        default:
                            System.out.println("Chces pokracovat? (A/N)");
                        break;
                    }
                }
                
            }
        }        
        
        System.out.println("(C) ArcasCZ 2015");
    }    
}
