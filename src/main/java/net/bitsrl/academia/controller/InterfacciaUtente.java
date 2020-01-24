package net.bitsrl.academia.controller;

import java.util.Collection;
import java.util.Scanner;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.services.abstractions.HRService;

public class InterfacciaUtente implements AutoCloseable {
    private HRService hrService;
    private Scanner userInput = new Scanner(System.in);

    public InterfacciaUtente(HRService hrService) {
         this.hrService = hrService;
    }

    private void controlAgent() throws DataException {
        System.out.print("--AGENT MENU--\n0.Go Back\n1.Read Alls\n2.Read by LastName\n3.Create\n4.Update\n5.Delete" +
                "\nScegli Numero: ");
        int num = userInput.nextInt();
        switch (num) {
            case 0: //Go Back
                break;
            case 1: // Read Alls
                System.out.println("-READ ALLS-");
                Collection<Agent> agents = hrService.getAllAgents();
//                for(Agent a : agents){
//                    System.out.println(a);
//                }
//                agents.forEach(a -> System.out.println(a));
                agents.forEach(System.out::println);
                System.out.println();
                break;
            case 2: //Read by LastName
                System.out.println("-READ BY LASTNAME-");
                System.out.print("Inserisci il cognome o parte di esso: ");
                String lastNameLike = userInput.next();
                Collection<Agent> agents1 = hrService.getAgentsByLastNameLike(lastNameLike);
                agents1.forEach(System.out::println);
                System.out.println();
                break;
            case 3: //Create
                System.out.println("-CREATE-");
//                System.out.print("ID: ");
//                int inputid = userInput.nextInt();
                System.out.print("Name: ");
                userInput.nextLine();
                String inputName = userInput.nextLine();
                System.out.print("LastName: ");
                String inputLastName = userInput.nextLine();
                hrService.createAgent(new Agent(0, inputName, inputLastName));
                System.out.println();
                break;
            case 4: //Update
                System.out.println("-UPDATE-\nLista Agents attuali");
                Collection<Agent> agents2 = hrService.getAllAgents();
                agents2.forEach(System.out::println);
                System.out.print("ID dell'Agent da sostituire: ");
                int id = userInput.nextInt();
                System.out.print("Nuovo nome: ");
                String newName = userInput.next();
                System.out.print("Nuovo cognome: ");
                String newLastName = userInput.next();
                hrService.updateAgent(id, new Agent(id, newName, newLastName));
                System.out.println();
                break;
            case 5: //Delete
                System.out.println("-DELETE-");
                Collection<Agent> agents3 = hrService.getAllAgents();
                agents3.forEach(System.out::println);
                System.out.print("Inserisci l'id dell'agente da eliminare: ");
                int agentIdToDelete = userInput.nextInt();
                hrService.deleteAgent(agentIdToDelete);
                break;
            default: //Default
                System.out.println("-Il menu arriva fino a 5-");
                break;
        }
    }

    private void controlCourse() throws DataException {
        System.out.print("--COURSE MENU--\n0.Go Back\n1.Read Alls\n2.Read by Tile\n3.Create\n4.Update\n5.Delete" +
                "\nScegli Numero: ");
        int num = userInput.nextInt();
        switch (num) {
            case 0: //Go Back
                break;
            case 1: // Read Alls
                System.out.println("-READ ALLS-");
                Collection<Course> courses = hrService.getAllCourses();
//                for(Course a : courses){
//                    System.out.println(a);
//                }
//                courses.forEach(a -> System.out.println(a));
                courses.forEach(System.out::println);
                System.out.println();
                break;
            case 2: //Read by Title
                System.out.println("-READ BY TITLE-");
                System.out.print("Inserisci il titolo o parte di esso: ");
                String titleLike = userInput.next();
                Collection<Course> course1 = hrService.getCoursesByTitleLike(titleLike);
                course1.forEach(System.out::println);
                System.out.println();
                break;
            case 3: //Create
                System.out.println("-CREATE-");
                System.out.print("Title: ");
                String inputTitle = userInput.next();
                System.out.print("Durata in ore: ");
                int inputHour = userInput.nextInt();
                hrService.createCourse(new Course(0, inputTitle, inputHour));
                System.out.println();
                break;
            case 4: //Update
                System.out.println("-UPDATE-\nLista Corsi attuali");
                Collection<Course> course2 = hrService.getAllCourses();
                course2.forEach(System.out::println);
                System.out.print("ID dell'Course da sostituire: ");
                int id = userInput.nextInt();
                System.out.print("Nuovo titolo: ");
                String newTitle = userInput.next();
                System.out.print("Nuova durata: ");
                int newHour = userInput.nextInt();
                hrService.updateCourse(id, new Course(id, newTitle, newHour));
                System.out.println();
                break;
            case 5: //Delete
                System.out.println("-DELETE-\nLista Corsi attuali");
                Collection<Course> course3 = hrService.getAllCourses();
                course3.forEach(System.out::println);
                System.out.print("Inserisci l'id del corso da eliminare: ");
                int courseIdToDelete = userInput.nextInt();
                hrService.deleteCourse(courseIdToDelete);
                break;
            default: //Default
                System.out.println("-Il menu arriva fino a 5-");
                break;
        }

    }

    public void start() throws DataException {
        System.out.println("Inserisci un numero per accedere al menu richiesto");
        System.out.print("0.Esci\n1.Agente\n2.Corso\nInserisci numero: ");
        int num = userInput.nextInt();
        switch (num) {
            case 0: //Esci
//                System.exit(0);
                return;
            case 1: //Agent
                controlAgent();
                break;
            case 2://Course
                controlCourse();
                break;
            default:
                System.out.println("-Il menu arriva fino a 2-\n");
                break;
        }
        start();

    }

    @Override
    public void close() {
        userInput.close();
    }
}
