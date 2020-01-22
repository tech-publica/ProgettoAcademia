package net.bitsrl.academia.database;

import net.bitsrl.academia.model.Agent;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class InFileRepositoryAgent implements RepositoryAgent {
    private DataBaseInMemory data = DataBaseInMemory.getInstance();
//    String path = getClass().getClassLoader().getResource("systemRepositoryAgent").getPath();
    String path = "src/main/resources/systemRepositoryAgent";

    @Override
    public void creaFile(){
        try {
            File file = new File(path);
            if (file.exists())
                System.out.println("Il file " + path + " esiste");
            else if (file.createNewFile())
                System.out.println("Il file " + path + " è stato creato");
            else
                System.out.println("Il file " + path + " non può essere creato");
            scriviFile();
            leggiFile2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scriviFile(){
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            String stringaOut = String.valueOf(data.getAgent().values());
            stringaOut = stringaOut
                    .replaceAll("\\[Agent\\{", "")
                    .replaceAll("Agent\\{","\n")
                    .replaceAll("\\{","")
                    .replaceAll("\\}","")
                    .replaceAll("\\]",",");
            bw.write(stringaOut);
            bw.newLine();
            bw.flush();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void leggiFile(){
        char[] in = new char[1000];
        int size = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            size = br.read(in);
            System.out.print("Caratteri presenti: " + size + "\n");
            System.out.print("Il contenuto del file è il seguente:\n");String line = br.readLine();
            while(line!=null) {
                System.out.println(line);
                line = br.readLine();
            }
            for(int i=0; i<size; i++)
                System.out.print(in[i]);
            br.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void leggiFile2(){
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (IOException e){
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
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("PROVA");
        return toInsert;
    }

    @Override
    public boolean delete(int agentId) {
        Map<Integer, Agent> agents = data.getAgent();
        Agent x = agents.remove(agentId);
        return x != null;
    }

    @Override
    public boolean update(int agentId, Agent toUpdate) {
        Map<Integer, Agent> agents = data.getAgent();
        Agent old = agents.replace(agentId, toUpdate);
        return old != null;
    }

    @Override
    public Collection<Agent> getAll() {
        Map<Integer, Agent> agents = data.getAgent();
        return agents.values();
    }

    @Override
    public Collection<Agent> getByLastnameLike(String pattern) {
        return data.getAgent().values().stream()
                .filter(a -> a.getLastname().contains(pattern))
                .collect(Collectors.toList());
    }
}
