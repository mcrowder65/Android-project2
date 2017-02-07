package com.matthewjcrowder.project2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private GestureDetectorCompat mDetector;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);// Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Abstract to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

        RecyclerView rv = (RecyclerView)findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeData();
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }



    private List<Data> persons;

    // This method creates an ArrayList that has three Data objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        persons = new ArrayList<>();
        EnglishNumberToWords converter = new EnglishNumberToWords();
        for(int i = 0; i < 1000; i++) {

            persons.add(new Data(converter.convert(i)));
        }
    }
    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    private void makeSnackBar(String message) {
        Snackbar mySnackBar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), message, Snackbar.LENGTH_SHORT);
        mySnackBar.show();
    }

    public void movingToSnowman(MenuItem item) {
        makeSnackBar("snowman button clicked!");
        Intent i = new Intent(getApplicationContext(), SnowmanActivity.class);
        startActivity(i);
    }

    public void movingToList(MenuItem item) {
        makeSnackBar("You are already on the list!");
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        makeSnackBar("onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        makeSnackBar("onDoubleTapEvent");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        makeSnackBar("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

        makeSnackBar("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        makeSnackBar("onFling");
        return true;
    }

    public class EnglishNumberToWords {

        private  final String[] tensNames = {
                "",
                " ten",
                " twenty",
                " thirty",
                " forty",
                " fifty",
                " sixty",
                " seventy",
                " eighty",
                " ninety"
        };

        private  final String[] numNames = {
                "",
                " one",
                " two",
                " three",
                " four",
                " five",
                " six",
                " seven",
                " eight",
                " nine",
                " ten",
                " eleven",
                " twelve",
                " thirteen",
                " fourteen",
                " fifteen",
                " sixteen",
                " seventeen",
                " eighteen",
                " nineteen"
        };

        private EnglishNumberToWords() {}

        private String convertLessThanOneThousand(int number) {
            String soFar;

            if (number % 100 < 20){
                soFar = numNames[number % 100];
                number /= 100;
            }
            else {
                soFar = numNames[number % 10];
                number /= 10;

                soFar = tensNames[number % 10] + soFar;
                number /= 10;
            }
            if (number == 0) return soFar;
            return numNames[number] + " hundred" + soFar;
        }


        public String convert(long number) {
            // 0 to 999 999 999 999
            if (number == 0) { return "zero"; }

            String snumber = Long.toString(number);

            // pad with "0"
            String mask = "000000000000";
            DecimalFormat df = new DecimalFormat(mask);
            snumber = df.format(number);

            // XXXnnnnnnnnn
            int billions = Integer.parseInt(snumber.substring(0,3));
            // nnnXXXnnnnnn
            int millions  = Integer.parseInt(snumber.substring(3,6));
            // nnnnnnXXXnnn
            int hundredThousands = Integer.parseInt(snumber.substring(6,9));
            // nnnnnnnnnXXX
            int thousands = Integer.parseInt(snumber.substring(9,12));

            String tradBillions;
            switch (billions) {
                case 0:
                    tradBillions = "";
                    break;
                case 1 :
                    tradBillions = convertLessThanOneThousand(billions)
                            + " billion ";
                    break;
                default :
                    tradBillions = convertLessThanOneThousand(billions)
                            + " billion ";
            }
            String result =  tradBillions;

            String tradMillions;
            switch (millions) {
                case 0:
                    tradMillions = "";
                    break;
                case 1 :
                    tradMillions = convertLessThanOneThousand(millions)
                            + " million ";
                    break;
                default :
                    tradMillions = convertLessThanOneThousand(millions)
                            + " million ";
            }
            result =  result + tradMillions;

            String tradHundredThousands;
            switch (hundredThousands) {
                case 0:
                    tradHundredThousands = "";
                    break;
                case 1 :
                    tradHundredThousands = "one thousand ";
                    break;
                default :
                    tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                            + " thousand ";
            }
            result =  result + tradHundredThousands;

            String tradThousand;
            tradThousand = convertLessThanOneThousand(thousands);
            result =  result + tradThousand;

            // remove extra spaces!
            return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
        }


    }
}
