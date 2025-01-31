package com.example.avjindersinghsekhon.minimaltodo.test;

import android.os.Bundle;
import android.support.test.runner.MonitoringInstrumentation;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;

@CucumberOptions(
        features = "features",
        glue = "com.example.avjindersinghsekhon.minimaltodo.test")
public class Instrumentation extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

//        String tags = BuildConfig.TEST_TAGS;
//        if (!tags.isEmpty()) {
//            arguments.putString("tags", tags.replaceAll(",", "--").replaceAll("\\s",""));
//        }
        instrumentationCore.create(arguments);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();

        waitForIdleSync();
        instrumentationCore.start();
    }
}

//import android.os.Bundle;
//import android.support.test.runner.MonitoringInstrumentation;
//
//import cucumber.api.android.CucumberInstrumentationCore;
//
//public class Instrumentation extends MonitoringInstrumentation {
//    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);
//    @Override
//    public void onCreate(Bundle arguments) {
//        super.onCreate(arguments);
//
//        instrumentationCore.create(arguments);
//        start();
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        waitForIdleSync();
//        instrumentationCore.start();
//    }
//}