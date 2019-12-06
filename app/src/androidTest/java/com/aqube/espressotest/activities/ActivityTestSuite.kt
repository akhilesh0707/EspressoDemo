package com.aqube.espressotest.activities

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginActivityTest::class,
    MainActivityTest::class
)
class ActivityTestSuite