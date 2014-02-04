package ru.omdroid.aptitude;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Start extends Activity implements SeekBar.OnSeekBarChangeListener {

    int age, checker;
    Boolean gender;
    boolean seekStatus;
    String uname;
    TextView txtAge;
    EditText userName;
    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final SeekBar seekB = (SeekBar)findViewById(R.id.seekBar);
        seekB.setOnSeekBarChangeListener(this);
        txtAge = (TextView)findViewById(R.id.txtAge);
        txtAge.setText(getString(R.string.age)+": " + String.valueOf(seekB.getProgress()+14));
        gender = null;

    }

    public void onClick(View v) {

        ImageView imgMale = (ImageView)findViewById(R.id.imgMale);
        ImageView imgFemale = (ImageView)findViewById(R.id.imgFemale);
        TextView txtMale = (TextView)findViewById(R.id.txtMale);
        TextView txtFemale = (TextView)findViewById(R.id.txtFemale);
        userName = (EditText)findViewById(R.id.etName);


        switch (v.getId()) {
            case R.id.imgMale:
               imgMale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.male_color)), PorterDuff.Mode.MULTIPLY);
               txtMale.setTextColor(Color.parseColor(getString(R.color.male_color)));
               imgFemale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.gender_color)), PorterDuff.Mode.MULTIPLY);
               txtFemale.setTextColor(Color.parseColor(getString(R.color.gender_color)));
               gender = true;
            break;
            case R.id.txtMale:
                imgMale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.male_color)), PorterDuff.Mode.MULTIPLY);
                txtMale.setTextColor(Color.parseColor(getString(R.color.male_color)));
                imgFemale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.gender_color)), PorterDuff.Mode.MULTIPLY);
                txtFemale.setTextColor(Color.parseColor(getString(R.color.gender_color)));
                gender = true;
            break;
            case R.id.imgFemale:
                imgFemale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.female_color)), PorterDuff.Mode.MULTIPLY);
                txtFemale.setTextColor(Color.parseColor(getString(R.color.female_color)));
                imgMale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.gender_color)), PorterDuff.Mode.MULTIPLY);
                txtMale.setTextColor(Color.parseColor(getString(R.color.gender_color)));
                gender = false;
            break;
            case R.id.txtFemale:
                imgFemale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.female_color)), PorterDuff.Mode.MULTIPLY);
                txtFemale.setTextColor(Color.parseColor(getString(R.color.female_color)));
                imgMale.getDrawable().setColorFilter(Color.parseColor(getString(R.color.gender_color)), PorterDuff.Mode.MULTIPLY);
                txtMale.setTextColor(Color.parseColor(getString(R.color.gender_color)));
                gender = false;
            break;
            case R.id.btnStart:
                if (gender !=null && userName.getText().length()!=0 && seekStatus == true) {
                   uname = userName.getText().toString();
                   Intent testStart = new Intent();
                   testStart.setClass(Start.this, Test.class);
                   testStart.putExtra("uAge", age);
                   testStart.putExtra("uGender", gender);
                   testStart.putExtra("uName", uname);

                   startActivity(testStart);
        }
            break;
            default:break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    txtAge.setText(getString(R.string.age)+": "+ String.valueOf(seekBar.getProgress()+14));
    seekStatus = b;

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        txtAge.setText(getString(R.string.age)+": " + seekBar.getProgress());
        age = seekBar.getProgress()+14;
    }
}
