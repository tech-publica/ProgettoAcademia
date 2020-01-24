package net.bitsrl.academia.database;

import net.bitsrl.academia.model.Agent;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class InFileRepositoryAgent implements RepositoryAgent {
    private DataBaseInMemory data = DataBaseInMemory.getInstance(); //ELIMINARE DOPO LA CONVERSIONE
    //    String path = getClass().getClassLoader().getResource("systemRepositoryAgent").getPath();
    String path = "src/main/resources/systemRepositoryAgent";

    //RIDEFINIRE TUTTI I METODI INIZIANDO DALLA LETTURA

    public void creaFile() {
        File file = new File(path);
        if (!file.exists())
            System.out.println("Il file " + path + " non può essere creato");
        scriviFile();
    }

    public void scriviFile() {
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String stringaOut = String.valueOf(data.getAgent().values());
            stringaOut = stringaOut
                    .replaceAll("\\[Agent\\{", "")
                    .replaceAll("Agent\\{", "\n")
                    .replaceAll("\\{", "")
                    .replaceAll("\\}", "")
                    .replaceAll("\\]", ",");
            bw.write(stringaOut + "\n");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Agent create(Agent toInsert) {
        Map<Integer, Agent> agents = data.getAgent();
        int maxKey = agents.keySet()
                .stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
        maxKey++;
        toInsert.setId(maxKey);
        agents.put(toInsert.getId(), toInsert);
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(String.valueOf(agents.values()));
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toInsert;
    }

    @Override
    public boolean delete(int agentId) {
        Map<Integer, Agent> agents = data.getAgent();
        Agent x = agents.remove(agentId);
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(String.valueOf(agents.values()));
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return x != null;
    }

    @Override
    public boolean update(int agentId, Agent toUpdate) {
        Collection<Agent> agents = getAll();
        System.out.println(toUpdate);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
//            bw.write(String.valueOf(agents.values()));
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toUpdate != null;
    }

    @Override
    public Collection<Agent> getAll() {
        Collection<Agent> agents = new ArrayList<>();
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] campi;
            while ((line != null)) {
                if (line.length() > 0) {
                    campi = line.split(",");
                    agents.add(new Agent(Integer.parseInt(campi[0]), campi[1], campi[2]));
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agents;
    }

    @Override
    public Collection<Agent> getByLastnameLike(String pattern) {
        Collection<Agent> agents = new ArrayList<>();
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] campi = null;
            while (line != null) {
                if ((line.length() > 0) & line.contains(pattern)) {
                    campi = line.split(",");
                    agents.add(new Agent(Integer.parseInt(campi[0]), campi[1], campi[2]));
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agents;
    }
}
