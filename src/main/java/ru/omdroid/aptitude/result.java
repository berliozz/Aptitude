package ru.omdroid.aptitude;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

public class Result extends FragmentActivity {

    private MyAdapter mAdapter;
    private ViewPager mPager;
    private String completeUan[][];
    int uan[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Resources testCat = getResources();
        String categoryQuest[] = testCat.getStringArray(R.array.category);

        Bundle bndResult = this.getIntent().getExtras();
        uan = bndResult.getIntArray("strDef");

        completeUan = new String[categoryQuest.length][3];

        for (int i=0; i<categoryQuest.length; i++) {
            String s = categoryQuest[i];
            String st[] = s.split(":");

            System.arraycopy(st, 1, completeUan[i], 0, 2);
            completeUan[i][2] = String.valueOf(uan [i]);

        }

        mAdapter = new MyAdapter (getSupportFragmentManager(), completeUan);
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

    }

    public static class MyAdapter extends FragmentPagerAdapter {
        String completeUan[][];
        public MyAdapter (FragmentManager fm, String mUan[][]) {
            super (fm);
            this.completeUan = mUan;
        }

        @Override
        public int getCount()  {
            return 10;
        }

        @Override
        public Fragment getItem(int position) {


            switch (position) {
               case 0:
                   return new Detail(R.string.A1, completeUan[0][0], completeUan[0][2], completeUan[0][1]);
               case 1:
                   return new Detail(R.string.B1, completeUan[1][0], completeUan[1][2], completeUan[1][1]);
               case 2:
                   return new Detail(R.string.C1, completeUan[2][0], completeUan[2][2], completeUan[2][1]);
               case 3:
                   return new Detail(R.string.D1, completeUan[3][0], completeUan[3][2], completeUan[3][1]);
               case 4:
                   return new Detail(R.string.E1, completeUan[4][0], completeUan[4][2], completeUan[4][1]);
               case 5:
                   return new Detail(R.string.F1, completeUan[5][0], completeUan[5][2], completeUan[5][1]);
               case 6:
                   return new Detail(R.string.G1, completeUan[6][0], completeUan[6][2], completeUan[6][1]);
               case 7:
                   return new Detail(R.string.H1, completeUan[7][0], completeUan[7][2], completeUan[7][1]);
               case 8:
                   return new Detail(R.string.I1, completeUan[8][0], completeUan[8][2], completeUan[8][1]);
               case 9:
                   return new Detail(R.string.J1, completeUan[9][0], completeUan[9][2], completeUan[9][1]);

               default:
                   return null;
           }
        }

    }

 /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView txtTest;
//        txtQresult = (TextView)findViewById(R.id.txtQresult);
        txtTest = (TextView)findViewById(R.id.txtTest);

          Resources testCat = getResources();
          String[] categoryQuest = testCat.getStringArray(R.array.category);

        Bundle bndResult = this.getIntent().getExtras();
        int[] uan = bndResult.getIntArray("strDef");

//        for (int aUan : uan) {
//            txtQresult.append(" " + aUan);
//        }

        if ((uan[0] >=  -95 & uan[0] <= -38) &&
                (uan[1] >= -100 & uan[1] <= -65) &&
                (uan[2] >=  -96 & uan[2] <= -63) &&
                (uan[3] >=  -83 & uan[3] <=  35) &&
                (uan[4] >=    3 & uan[4] <=  45) &&
                (uan[5] >=   22 & uan[5] <=  72) &&
                (uan[6] >=  -92 & uan[6] <= -55) &&
                (uan[7] >=  -98 & uan[7] <= -35) &&
                (uan[8] >=  -90 & uan[8] <=  18) &&
                (uan[9] >=  -72 & uan[4] <=  2)) {
            txtTest.setText(getString(R.string.fail_description));
        }

//      for (int i=0; i>uan.length; i++) {
//         int resID;
//         resID = getResources().getIdentifier("A1", "string", "ru.omdroid.aptitude");

//Огромный кусок говнокода, который нужно заменить на прогон через цикл, изменяется только блок с R.string

        txtTest.append(String.valueOf(categoryQuest[0])+"\n");

        if (uan[0]>=70) {
            txtTest.append(getString(R.string.A1));
        } else if (uan[0] <70 & uan[0]>=20) {
            txtTest.append(getString(R.string.A2));
        } else if (uan[0]<20 & uan[0]>=-40) {
            txtTest.append(getString(R.string.A3));
        } else if (uan[0]<-40) {
            txtTest.append(getString(R.string.A4));
        }

        txtTest.append("\n\n"+String.valueOf(categoryQuest[1])+"\n");

        if (uan[1]>=70) {
            txtTest.append(getString(R.string.B1));
        } else if (uan[1] <70 & uan[1]>=20) {
            txtTest.append(getString(R.string.B2));
        } else if (uan[1]<20 & uan[1]>=-40) {
            txtTest.append(getString(R.string.B3) + "\n");
        } else if (uan[1]<-40) {
            txtTest.append(getString(R.string.B4) + "\n");
        }

        txtTest.append("\n\n"+String.valueOf(categoryQuest[2])+"\n");

        if (uan[2]>=70) {
            txtTest.append(getString(R.string.C1)+"\n");
        } else if (uan[2] <70 & uan[2]>=20) {
            txtTest.append(getString(R.string.C2)+"\n");
        } else if (uan[2]<20 & uan[2]>=-40) {
            txtTest.append(getString(R.string.C3)+"\n");
        } else if (uan[2]<-40) {
            txtTest.append(getString(R.string.C4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[3])+"\n");
        if (uan[3]>=70) {
            txtTest.append(getString(R.string.D1)+"\n");
        } else if (uan[3] <70 & uan[3]>=20) {
            txtTest.append(getString(R.string.D2)+"\n");
        } else if (uan[3]<20 & uan[3]>=-40) {
            txtTest.append(getString(R.string.D3)+"\n");
        } else if (uan[3]<-40) {
            txtTest.append(getString(R.string.D4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[4])+"\n");
        if (uan[4]>=70) {
            txtTest.append(getString(R.string.E1)+"\n");
        } else if (uan[4] <70 & uan[4]>=20) {
            txtTest.append(getString(R.string.E2)+"\n");
        } else if (uan[4]<20 & uan[4]>=-40) {
            txtTest.append(getString(R.string.E3)+"\n");
        } else if (uan[4]<-40) {
            txtTest.append(getString(R.string.E4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[5])+"\n");
        if (uan[5]>=70) {
            txtTest.append(getString(R.string.F1)+"\n");
        } else if (uan[5] <70 & uan[5]>=20) {
            txtTest.append(getString(R.string.F2)+"\n");
        } else if (uan[5]<20 & uan[5]>=-40) {
            txtTest.append(getString(R.string.F3)+"\n");
        } else if (uan[5]<-40) {
            txtTest.append(getString(R.string.F4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[6])+"\n");
        if (uan[6]>=70) {
            txtTest.append(getString(R.string.G1)+"\n");
        } else if (uan[6] <70 & uan[6]>=20) {
            txtTest.append(getString(R.string.G2)+"\n");
        } else if (uan[6] <20 & uan[6]>=-40) {
            txtTest.append(getString(R.string.G3)+"\n");
        } else if (uan[6]<-40) {
            txtTest.append(getString(R.string.G4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[7])+"\n");
        if (uan[7]>=70) {
            txtTest.append(getString(R.string.H1)+"\n");
        } else if (uan[7] <70 & uan[7]>=20) {
            txtTest.append(getString(R.string.H2)+"\n");
        } else if (uan[7] <20 & uan[7]>=-40) {
            txtTest.append(getString(R.string.H3)+"\n");
        } else if (uan[7]<-40) {
            txtTest.append(getString(R.string.H4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[8])+"\n");
        if (uan[8]>=70) {
            txtTest.append(getString(R.string.I1)+"\n");
        } else if (uan[8] <70 & uan[8]>=20) {
            txtTest.append(getString(R.string.I2)+"\n");
        } else if (uan[8] <20 & uan[8]>=-40) {
            txtTest.append(getString(R.string.I3)+"\n");
        } else if (uan[8]<-40) {
            txtTest.append(getString(R.string.I4)+"\n");
        }
        txtTest.append("\n\n"+String.valueOf(categoryQuest[9])+"\n");
        if (uan[9]>=70) {
            txtTest.append(getString(R.string.J1)+"\n");
        } else if (uan[9] <70 & uan[9]>=20) {
            txtTest.append(getString(R.string.J2)+"\n");
        } else if (uan[9] <20 & uan[9]>=-40) {
            txtTest.append(getString(R.string.J3)+"\n");
        } else if (uan[9]<-40) {
            txtTest.append(getString(R.string.J4)+"\n");
        }
      
    }    */
}


