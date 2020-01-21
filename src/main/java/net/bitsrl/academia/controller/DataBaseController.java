package net.bitsrl.academia.controller;

import java.sql.SQLOutput;
import java.util.Scanner;

import net.bitsrl.academia.database.InMemoryRepositoryAgent;
import net.bitsrl.academia.database.RepositoryAgent;
import net.bitsrl.academia.model.Agent;

public class DataBaseController {
    private RepositoryAgent repAgent = new InMemoryRepositoryAgent();
    Scanner userInput = new Scanner(System.in);

    private void controlAgent() {
        System.out.println("--AGENT MENU--\n0. Go Back\n1.Read Alls\n2.Read by LastName\n3.Create\n4.Update\n5.Delete");
        int num = userInput.nextInt();
        switch (num) {
            case 0:
                start();
                break;
            case 1:
                System.out.println("-READ ALLS-");
                System.out.println(repAgent.getAll());
                break;
            case 2:
                System.out.println("-READ BY LASTNAME-");
                break;
            case 3:
                System.out.println("-CREATE-");
                System.out.print("ID: ");
                int inputid = userInput.nextInt();
                System.out.print("Name: ");
                String inputName = userInput.next();
                System.out.print("LastName: ");
                String inputLastName = userInput.next();
                repAgent.create(new Agent(inputid, inputName, inputLastName));
                break;
            case 4:
                System.out.println("-UPDATE-");

                break;
            case 5:
                System.out.println("-DELETE-");
                break;
            default:
                System.out.println("-DEFAULT-");
                break;
        }
    }

    private void controlCourse() {
    }

    public void start() {
        int num = userInput.nextInt();
        System.out.println("Inserisci un numero per accedere al menu richiesto: ");
        System.out.println("1. Agente\n2.Corso");
        switch (num) {
            case 1: //Agent
                controlAgent();
                break;
            case 2://Course
                controlCourse();
                break;
            default:
                System.out.println("DEFINIRE");
                break;
        }
    }
}
