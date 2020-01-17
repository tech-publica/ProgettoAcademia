package net.bitsrl.academia.course;

public class Course {
    private int id;
    private String titolo;
    private int durataOre;

    public Course(int id, String titolo, int durataOre) {
        this.id = id;
        this.titolo = titolo;
        this.durataOre = durataOre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getDurataOre() {
        return durataOre;
    }

    public void setDurataOre(int durataOre) {
        this.durataOre = durataOre;
    }
}
