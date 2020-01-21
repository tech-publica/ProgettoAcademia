package net.bitsrl.academia.model;

public class Course {
    private int id;
    private String title;
    private int durHour;

    public Course(int id, String title, int durHour) {
        this.id = id;
        this.title = title;
        this.durHour = durHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurHour() {
        return durHour;
    }

    public void setDurHour(int durHour) {
        this.durHour = durHour;
    }
}
