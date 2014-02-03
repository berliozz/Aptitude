package ru.omdroid.aptitude;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class test extends Activity {

    TextView txtInfo, txtNum;
    Button btnYes, btnNo, btnMaybe;
    String quiz[];
    int result[], arraySize;
    int iter = 0;
    String catQ[][], answers[][];
    int procentilCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Resources qset = getResources();
        String [] qsetArray = qset.getStringArray(R.array.testquestions);

        arraySize = qsetArray.length;
        quiz = new String[arraySize]; // Вопрос
        catQ = new String[arraySize][2]; // Номер вопроса и соответствующая ему категория
        answers = new String[arraySize][3]; /* Баллы за ответы на вопросы */
        result = new int[arraySize]; //Результаты тестирования в виде суммы баллов по каждой категории, потом сравнивается с таблице процентилей

        for (int i=0; i<arraySize; i++) {
            String s = qsetArray[i];
            String [] st = s.split(":");

            quiz[i] = st[2]; //текст вопроса помещается в отдельный массив
            System.arraycopy(st, 0, catQ[i], 0, 2); //в отдельный массив помещается номер вопроса и категория
            System.arraycopy(st, 3, answers[i], 0, 3); //в отдельный массив помещаются баллы за ответы на вопросы (Нет, Не знаю, Да)
        }
        questList();
    }

    private void questList() {
        txtInfo = (TextView)findViewById(R.id.txtQuiz);
        txtNum = (TextView)findViewById(R.id.txtQNum);

        txtInfo.setText(String.valueOf(quiz[iter]));
        txtNum.setText(String.format("Вопрос %s из %d", String.valueOf(catQ[iter][1]), arraySize));
    }

    public void onClick (View v) {

        btnYes = (Button)findViewById(R.id.btnYes);
        btnMaybe = (Button)findViewById(R.id.btnMaybe);
        btnNo = (Button)findViewById(R.id.btnNo);

        switch (v.getId()) {
            case R.id.btnYes:
                result[iter] = Integer.valueOf(answers[iter][2]);
            break;
            case R.id.btnMaybe:
                result[iter] = Integer.valueOf(answers[iter][1]);
            break;
            case R.id.btnNo:
                result[iter] = Integer.valueOf(answers[iter][0]);
            break;
            default:break;
        }
        iter++;

        if (iter >= result.length) {
            iter = 0;
            calcResult(result);
            return;
        }
        questList();
    }

    public void calcResult (int [] finalResult) {
        // Ведется расчет баллов по каждой категории и вносится в отдельный массив

        int [] points = new int[10];

//Блок переменных с возрастом (userAge), полом (userGender) и именем тестируемого (userName):
        String userName = this.getIntent().getExtras().getString("uName");
        int userAge = this.getIntent().getExtras().getInt("uAge");
        boolean userGender = this.getIntent().getExtras().getBoolean("uGender");

//Конец блока параметров пользователя

        if (userAge >= 18 && userGender == true) {
            procentilCase = R.array.male18plus;
        } else if (userAge >= 18 && userGender == false) {
            procentilCase = R.array.woman18plus;
        } else if (userAge < 18 && userGender == true) {
            procentilCase = R.array.boy14plus;
        } else if (userAge < 18 && userGender == false) {
            procentilCase = R.array.girl14plus;
        }

        for (int i=0; i<catQ.length;i++) {
            if (catQ[i][0].equals("A")) {
                points[0] = points[0] + finalResult[i];

            } else if (catQ[i][0].equals("B")) {
                points[1] = points[1] + finalResult[i];

            } else if (catQ[i][0].equals("C")) {
                points[2] = points[2] + finalResult[i];

            } else if (catQ[i][0].equals("D")) {
                points[3] = points[3] + finalResult[i];

            } else if (catQ[i][0].equals("E")) {
                points[4] = points[4] + finalResult[i];

            } else if (catQ[i][0].equals("F")) {
                points[5] = points[5] + finalResult[i];

            } else if (catQ[i][0].equals("G")) {
                points[6] = points[6] + finalResult[i];

            } else if (catQ[i][0].equals("H")) {
                points[7] = points[7] + finalResult[i];

            } else if (catQ[i][0].equals("I")) {
                points[8] = points[8] + finalResult[i];

            } else if (catQ[i][0].equals("J")) {
                points[9] = points[9] + finalResult[i];

            }
        }
// Расчет данных по таблицам процентилей
        Resources procentil = getResources();
        String procentilRaw[] = procentil.getStringArray(procentilCase);
        String [][] prepareResult = new String[procentilRaw.length][points.length+1];

        for (int i=0; i<procentilRaw.length; i++) {
            String s = procentilRaw[i];
            String [] st = s.split(":");
            System.arraycopy(st, 0, prepareResult[i], 0, points.length+1);
        }

        for (int i=0; i<points.length; i++) {
            boolean b = false;
            int j=0;
            while (b == false) {
                if (points[i] == Integer.valueOf(prepareResult[j][0])) {
                    points[i] = Integer.valueOf(prepareResult[j][i+1]);
                    b = true;
                }
                j++;
            }
        }
        Intent testResult = new Intent();
        testResult.setClass(test.this, result.class);
        Bundle testSummary = new Bundle();
        testSummary.putIntArray("strDef", points);
        testResult.putExtras(testSummary);
        startActivity(testResult);
    }

}
