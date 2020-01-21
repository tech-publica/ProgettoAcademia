package net.bitsrl.academia;

import net.bitsrl.academia.controller.DataBaseController;

public class Main {
    public static void main(String[] args) {
        DataBaseController mydbc = new DataBaseController();
        mydbc.start();
    }
}