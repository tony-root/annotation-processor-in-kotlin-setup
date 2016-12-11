package com.rutkevich.processorsample;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeneratedByProcessorInJava.main(new String[]{});
        GeneratedByProcessorInKotlin.main(new String[]{});
    }
}
