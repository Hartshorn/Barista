package com.palmer.sample;

import java.util.logging.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
 * BaristaTestRunner
 *
 * Author: Michael Palmer
 * 
 * 7/20/2015
 * 
 * This is a runner class for the JUnit tests, allowing them to be run directly
 * from the command line.
 */
public class BaristaTestRunner {
    
    private static final Logger LOGGER = Logger.getLogger(BaristaTest.class.getName());
    
    public static void main(String[] args)
    {
        LOGGER.info("BaristaTestRunner: RUNNING TEST CASES");
        
        Result result = JUnitCore.runClasses(BaristaTest.class);
        
        for (Failure failure : result.getFailures()) {
            LOGGER.info(failure.toString());
        }
        
        LOGGER.info("BaristaTestRunner: TEST CASES COMPLETE");
    }
}