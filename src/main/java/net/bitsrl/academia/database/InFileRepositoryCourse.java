package net.bitsrl.academia.database;

import java.io.*;

public class InFileRepositoryCourse {
    String path = "C:/lamiaprova.txt";
        try {
        File file = new File(path);
        if (file.exists())
            System.out.println("Il file " + path + " esiste");
        else if (file.createNewFile())
            System.out.println("Il file " + path + " è stato creato");
        else
            System.out.println("Il file " + path + " non può essere creato");

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Questo è il nostro primo file");
        bw.flush();
        bw.close();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
    readFile2();

}

    public static void readFile2() {
        String path = "C:/lamiaprova.txt";
        char[] in = new char[50];
        int size = 0;
        try {
            File file = new File(path);
            FileReader fr;
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            size = br.read(in);
            System.out.print("Caratteri presenti: " + size + "n");
            System.out.print("Il contenuto del file è il seguente:n");
            for (int i = 0; i < size; i++)
                System.out.print(in[i]);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
