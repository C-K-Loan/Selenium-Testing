package com.x24.frontend.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

//Put the test classes to run inside this annotation
@Suite.SuiteClasses({
	//HomepageAvailable.class,
	//OneProductLoaded.class
	//OneProductLoadedCatDuschen.class
    //FirstProductCorrectSorted.class,	
    brokenImagesCatTest.class,
	//FilterTestCatBetten.class,
	//URLupdatedByFilter.class
})

public class TestSuite {   
}  	