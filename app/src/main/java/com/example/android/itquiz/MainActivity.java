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
        boolean questionOneChecked = questionOneCheckBox.isChecked();

        // Get question two's radio button correct answer status
        RadioButton questionTwoCheckBox = findViewById(R.id.question_two_answer_button);
        boolean questionTwoChecked = questionTwoCheckBox.isChecked();

        // Getting user input from question three answer field
        EditText userAnswerThreeField = findViewById(R.id.question_three_answer_field);
        String userAnswerToQuestionThree = userAnswerThreeField.getText().toString();
        userAnswerToQuestionThree = userAnswerToQuestionThree.toLowerCase();

        // Get question four's radio button correct answer status
        int questionFourCheckedBoxes = 0;
        CheckBox questionFourCheckBoxOne = findViewById(R.id.question_four_check_box_one);
        boolean questionFourCheckBoxOneChecked = questionFourCheckBoxOne.isChecked();

        CheckBox questionFourCheckBoxTwo = findViewById(R.id.question_four_check_box_two);
        boolean questionFourCheckBoxTwoChecked = questionFourCheckBoxTwo.isChecked();

        CheckBox questionFourCheckBoxThree = findViewById(R.id.question_four_check_box_three);
        boolean questionFourCheckBoxThreeChecked = questionFourCheckBoxThree.isChecked();

        CheckBox questionFourCheckBoxFour = findViewById(R.id.question_four_check_box_four);
        boolean questionFourCheckBoxFourChecked = questionFourCheckBoxFour.isChecked();

        if (questionFourCheckBoxOneChecked) {
            questionFourCheckedBoxes++;
        }
        if (questionFourCheckBoxTwoChecked) {
            questionFourCheckedBoxes++;
        }
        if (questionFourCheckBoxThreeChecked) {
            questionFourCheckedBoxes++;
        }
        if (questionFourCheckBoxFourChecked) {
            questionFourCheckedBoxes++;
        }

        // Get question five's radio button correct answer status
        RadioButton questionFiveCheckBox = findViewById(R.id.question_five_answer_button);
        boolean questionFiveChecked = questionFiveCheckBox.isChecked();

        // Checking answers for the questions

        // Question one
        if (questionOneChecked) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_one);
        }

        // Question two
        if (questionTwoChecked) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_two);
        }

        // Question three
        if (userAnswerToQuestionThree.equals(questionThreeAnswer)) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_three);
        }

        // Question four
        // Check to see if multiple answers are checked
        if (questionFourCheckedBoxes > 1) {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.too_many_answer_question_four);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        } else if (questionFourCheckBoxFourChecked) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_four);
        }

        // Question five
        if (questionFiveChecked) {
            correctAnswers++;
        } else {
            wrongAnswerMessage += "\n" + getString(R.string.question_five);
        }

        // Toast to display if answers are incorrect
        if (correctAnswers < 5) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, wrongAnswerMessage, duration);
            toast.show();
        } else {
            // What to do if every answer is correct
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, correctAnswerMessage, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

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
