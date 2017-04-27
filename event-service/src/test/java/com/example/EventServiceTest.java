package com.example;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by 42278 on 2017/4/19.
 */
@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("EventService") // Set up name of tested provider
@PactBroker(host = "localhost",port = "8888") //location for test use ' @PactFolder("pacts") ' --Point where to find pacts (See also section Pacts source in documentation,pacts folder in resources)
public class EventServiceTest {

    @BeforeClass //Method will be run once: before whole contract test suite
    public static void setUpService() {
        //Run DB, create schema
        //Run service
        //...
    }

    @Before //Method will be run before each test of interaction
    public void before() {
        // Rest data
        // Mock dependent service responses
        // ...
    }

    // Method will be run before testing interactions that require "default" or "no-data" state
    @State("default")
    public void toDefaultState() {
        // Prepare service before interaction that require "default" state
        // ...
        System.out.println("Now service in default state");
    }

    @TestTarget // Annotation denotes Target that will be used for tests
    public final Target target = new HttpTarget(9000); // Out-of-the-box implementation of Target (for more information take a look at Test Target section)
}