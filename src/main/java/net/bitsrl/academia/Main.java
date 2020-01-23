package net.bitsrl.academia;

import net.bitsrl.academia.controller.DataBaseController;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DataBaseController mydbc = new DataBaseController();
        mydbc.start();
        mydbc.close();
    }
}
