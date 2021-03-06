package com.example.android.itquiz;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitQuiz(View view) {
        int correctAnswers = 0;
        String questionThreeAnswer = getString(R.string.question_three_correct_answer);
        String correctAnswerMessage = getString(R.string.pass_quiz_toast_message);
        String wrongAnswerMessage = getString(R.string.wrong_answers_toast_message);

        // Get question one's radio button correct answer status
        RadioButton questionOneCheckBox = findViewById(R.id.question_one_answer_button);

        // Get question two's radio button correct answer status
        RadioButton questionTwoCheckBox = findViewById(R.id.question_two_answer_button);

        // Getting user input from question three answer field
        EditText userAnswerThreeField = findViewById(R.id.question_three_answer_field);
        String userAnswerToQuestionThree = userAnswerThreeField.getText().toString().trim();

        // Get question four's check boxes to reference when checking for right answer
        CheckBox questionFourCheckBoxOne = findViewById(R.id.question_four_check_box_one);
        CheckBox questionFourCheckBoxTwo = findViewById(R.id.question_four_check_box_two);
        CheckBox questionFourCheckBoxThree = findViewById(R.id.question_four_check_box_three);
        CheckBox questionFourCheckBoxFour = findViewById(R.id.question_four_check_box_four);

        // Get question five's radio button correct answer status
        RadioButton questionFiveCheckBox = findViewById(R.id.question_five_answer_button);

        // Checking answers for the questions

        // Question one
        if (questionOneCheckBox.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_one);
        }

        // Question two
        if (questionTwoCheckBox.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_two);
        }

        // Question three
        if (userAnswerToQuestionThree.equalsIgnoreCase(questionThreeAnswer)) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_three);
        }

        // Question four
        if (questionFourCheckBoxOne.isChecked() && questionFourCheckBoxTwo.isChecked() && questionFourCheckBoxThree.isChecked() && !questionFourCheckBoxFour.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_four);
        }

        // Question five
        if (questionFiveCheckBox.isChecked()) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_five);
        }

        // Toast to display if answers are incorrect
        if (correctAnswers < 5) {
            wrongAnswerMessage += "\n" + getString(R.string.total_answers_correct) + correctAnswers + getString(R.string.total_slash_five);
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, wrongAnswerMessage, duration);
            toast.show();
        } else {
            // Add correct number of questions to correctAnswerMessage
            correctAnswerMessage += "\n" + getString(R.string.total_answers_correct) + correctAnswers + getString(R.string.total_slash_five);

            //Toast to display when all answer are correct
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, correctAnswerMessage, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

            // Reset app to start again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    /**
     * This method is called when the reset button is clicked.
     */
    public void resetApp(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
