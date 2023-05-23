package com.auctix.auctx.tests.controller;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ProductControllerTests.class, UserControllerTests.class})
public class AllControllersTests {
}
