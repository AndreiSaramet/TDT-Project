package com.auctix.auctx.tests;

import com.auctix.auctx.tests.controller.AllControllersTests;
import com.auctix.auctx.tests.repository.AllRepositoriesTests;
import com.auctix.auctx.tests.service.AllServicesTests;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Suite
@SelectClasses({AllControllersTests.class, AllServicesTests.class, AllRepositoriesTests.class})
class AuctxApplicationTests {

//	@Test
//	void contextLoads() {
//	}

}
