package net.bitsrl.academia;

import net.bitsrl.academia.controller.DataBaseController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DataBaseController mydbc = new DataBaseController();
        mydbc.start();
        mydbc.close();
    }
}