package com.auctix.auctx.tests.repository;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ProductRepositoryTests.class, UserRepositoryTests.class})
public class AllRepositoriesTests {
}
