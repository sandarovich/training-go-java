package com.sandarovich.kickstarter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Olexander Kolodiazhny
 * 
 *         Output\Read text to user console
 *
 */

public class ConsoleIO implements IO {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        String resultStr = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            resultStr = reader.readLine();
        }
        catch (IOException e) {
            new ConsoleIO().write(">> Exception.Unable to read input");
        }
        
        return resultStr;
    }

}