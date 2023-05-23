package com.auctix.auctx.tests.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({UserServiceTests.class, ProductServiceTests.class})
public class AllServicesTests {
}
