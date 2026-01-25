package model;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * Klasse für Professorin Majuntke, die letzte Hürde vor dem Abschluss.
 * @author Berkant Kaygan
 * @author Luca Weber
 */




public class ProfessorinMajuntke implements Serializable{

    private static final long serialVersionUID = 540082607047283589L;

    private Random randomNumber = new Random();

    // alle unterschrriften gesammelt erscheint Profess
    public void meetMajunke(){
        System.out.println("===== Professorin Majuntke =====");
        System.out.println("I see you have collected all signatures for the routing sheet.");
        System.out.println("Before I can sign it, you need to answer a few questions about programming basics.");
        System.out.println("You need to answer at least 2 questions correctly to get my signature. out of my 3 questions.");
        System.out.println("====================================");
        askQuestions();
    

    }

    

    //mutiple choice fragen zu grunddlagen der programmierung
    private void askQuestions(){
        
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        boolean alreadyasked1 = false;
        boolean alreadyasked2 = false;
        boolean alreadyasked3 = false;

        

        while (score < 1) {
            
            int r = randomNumber.nextInt(3);

            if(alreadyasked1 && alreadyasked2 && alreadyasked3){
                System.out.println("All Question asked.");
                System.out.println("You answered " + score + " out of 2 questions correctly.");
                System.out.println("Game Over.");
                System.out.println("Professorin Majuntke leaves in he Rocketship.");
                break;
            }
            
            if(r == 0 && alreadyasked1 == false){
                alreadyasked1 = true;
                System.out.println("Question : What is a class in object-oriented programming?");
                System.out.println("a) A blueprint for creating objects");
                System.out.println("b) A type of variable");
                System.out.println("c) A function that performs a specific task");
                System.out.println("d) A data structure for storing values");
                String answer1 = scanner.nextLine();
                if(answer1.equalsIgnoreCase("a")){
                    score++;
                }
                else if(alreadyasked2 == false && alreadyasked3 == false){
                    System.out.println("Wrong answer. You have second and last chance");
                    System.out.println("prepare for the next question.");        
                }
                else{
                    System.out.println("Wrong answer. Game Over. That was your last chance");
                    System.out.println("Professorin Majuntke leaves in he Rocketship.");
                    break;
                    }
             }
            else if(r == 1 && alreadyasked2 == false){
                alreadyasked2 = true;
                System.out.println("Question : What does 'inheritance' mean in programming?");
                System.out.println("a) A way to create new classes based on existing ones");
                System.out.println("b) A method of data storage");
                System.out.println("c) A type of loop structure");
                System.out.println("d) A way to handle errors");
                String answer2 = scanner.nextLine();
                if(answer2.equalsIgnoreCase("a")){
                    score++;
                }
                else if(alreadyasked1 == false && alreadyasked3 == false){
                    System.out.println("Wrong answer. You have second and last chance");
                    System.out.println("prepare for the next question.");
                }
                else{
                    System.out.println("Wrong answer. Game Over. That was your last chance.");
                    System.out.println("Professorin Majuntke leaves in he Rocketship.");
                    break;
                }
            }
            else if(r == 3 && alreadyasked3 == false){
                alreadyasked3 = true;
                System.out.println("Question : What is a variable?");
                System.out.println("a) A fixed value that cannot change");
                System.out.println("b) A container for storing data values");
                System.out.println("c) A type of function");
                System.out.println("d) A programming language");
                String answer3 = scanner.nextLine();
                if(answer3.equalsIgnoreCase("b")){
                    score++;
                }
                else if(alreadyasked1 == false && alreadyasked2 == false){
                    System.out.println("Wrong answer. You have second and last chance");
                    System.out.println("prepare for the next question.");
                }
                else{
                    System.out.println("Wrong answer. Game Over. That was your last chance");
                    System.out.println("Professorin Majuntke leaves in he Rocketship.");
                    break;
                }
            }

                
            if(score == 2){
                System.out.println("You answered " + score + " out of 2 questions correctly.");
                System.out.println("Well done! I will sign your routing sheet.");
                System.out.println("=== Routing Sheet Signed by Professorin Majuntke ===");
                System.out.println("Congratulations! You have completed your routing sheet and can now graduate!");
            
            }


                    
        }
        
    }


    
    
}
