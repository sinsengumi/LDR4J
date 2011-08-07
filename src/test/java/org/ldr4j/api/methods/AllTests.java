package org.ldr4j.api.methods;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    FeedMethodTest.class,
    FolderMethodTest.class,
    PinMethodTest.class
})
public class AllTests {
    public static void main(String[] args) {
        JUnitCore.main(AllTests.class.getName());
    }
}
