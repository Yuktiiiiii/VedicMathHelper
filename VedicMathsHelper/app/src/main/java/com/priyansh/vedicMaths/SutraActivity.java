package com.priyansh.vedicMaths;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SutraActivity extends AppCompatActivity {

    TextView sutraTitle, questionText, hintText, solutionText;
    EditText answerInput;
    Button checkBtn, nextBtn;

    int sutraNumber;
    int a, b, correctAnswer;
    int step = 0;

    boolean isDemo = true;
    int demoStep = 0;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sutra);

        sutraNumber = getIntent().getIntExtra("sutraNumber", 1);

        sutraTitle = findViewById(R.id.sutraTitle);
        questionText = findViewById(R.id.questionText);
        hintText = findViewById(R.id.hintText);
        solutionText = findViewById(R.id.solutionText);
        answerInput = findViewById(R.id.answerInput);
        checkBtn = findViewById(R.id.checkBtn);
        nextBtn = findViewById(R.id.nextBtn);

        sutraTitle.setText(getSutraName(sutraNumber));

        generateQuestion();
        startDemo();

        checkBtn.setOnClickListener(v -> checkAnswer());

        nextBtn.setOnClickListener(v -> {
            if (isDemo) {
                isDemo = false;

                answerInput.setVisibility(View.VISIBLE);
                checkBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("Next");

                step = 0;
                loadStep();

            } else {
                generateQuestion();
                step = 0;
                loadStep();
            }
        });
    }

    // ---------------- SUTRA NAMES ----------------

    private String getSutraName(int id) {
        switch (id) {
            case 1: return "Ekadhikena Purvena";
            case 2: return "Nikhilam Navatashcaramam Dashatah";
            case 3: return "Urdhva-Tiryagbhyam";
            case 4: return "Paravartya Yojayet";
            case 5: return "Shunyam Saamyasamuccaye";
            case 6: return "Anurupyena";
            case 7: return "Sankalana-Vyavakalanabhyam";
            case 8: return "Puranapuranabhyam";
            case 9: return "Chalana-Kalanabhyam";
            case 10: return "Yavadunam";
            case 11: return "Vyashtisamanstih";
            case 12: return "Shesanyankena Charamena";
            case 13: return "Sopantyadvayamantyam";
            case 14: return "Ekanyunena Purvena";
            case 15: return "Gunakasamuccayah";
            case 16: return "Gunita Samuccayah";
            default: return "Sutra " + id;
        }
    }

    // ---------------- QUESTION GENERATION ----------------

    private void generateQuestion() {

        answerInput.setText("");
        solutionText.setText("");

        switch (sutraNumber) {

            case 1: // Ekadhikena (square ending 5)
                a = (random.nextInt(9) + 1) * 10 + 5;
                b = a;
                break;

            case 2: // Nikhilam
                a = 100 - (random.nextInt(20) + 1);
                b = 100 - (random.nextInt(20) + 1);
                break;

            case 3: // Urdhva
                a = random.nextInt(90) + 10;
                b = random.nextInt(90) + 10;
                break;

            case 6: // Anurupyena (near 50)
                a = 50 + random.nextInt(10);
                b = 50 + random.nextInt(10);
                break;

            case 10: // Yavadunam
                a = 100 - random.nextInt(30);
                b = 100 - random.nextInt(30);
                break;

            default:
                a = random.nextInt(50) + 10;
                b = random.nextInt(50) + 10;
        }

        correctAnswer = a * b;
    }

    // ---------------- DEMO MODE ----------------

    private void startDemo() {

        answerInput.setVisibility(View.GONE);
        checkBtn.setVisibility(View.GONE);
        nextBtn.setText("Start Practice");

        demoStep = 0;

        playDemo();
    }

    private void playDemo() {

        solutionText.setAlpha(0f);
        solutionText.animate().alpha(1f).setDuration(400).start();

        switch (sutraNumber) {

            case 1:
                solutionText.setText(
                        "Square numbers ending with 5\n\n" +
                                "Example: 25²\n" +
                                "2 × 3 = 6\n" +
                                "Append 25 → 625"
                );
                return;

            case 2:
                solutionText.setText(
                        "Use base 100\n\n" +
                                "Subtract from base\n" +
                                "Cross subtract\n" +
                                "Multiply deficits"
                );
                return;

            case 3:
                solutionText.setText(
                        "Vertical & Crosswise multiplication\n\n" +
                                "Multiply units\nCross multiply\nMultiply tens"
                );
                return;

            case 4:
                solutionText.setText("Used for division via inversion method");
                return;

            case 5:
                solutionText.setText("If expressions are equal, result is zero");
                return;

            case 6:
                solutionText.setText("Adjust base (like 50 instead of 100)");
                return;

            case 7:
                solutionText.setText("Addition and subtraction together");
                return;

            case 8:
                solutionText.setText("Completion and non-completion method");
                return;

            case 9:
                solutionText.setText("Used in calculus-like transformations");
                return;

            case 10:
                solutionText.setText("Find deficiency from base and adjust");
                return;

            case 11:
                solutionText.setText("Part and whole relationship");
                return;

            case 12:
                solutionText.setText("Remainders using last digit");
                return;

            case 13:
                solutionText.setText("Use penultimate and last digits");
                return;

            case 14:
                solutionText.setText("One less than previous");
                return;

            case 15:
                solutionText.setText("Product of sum equals sum of product");
                return;

            case 16:
                solutionText.setText("Multiplication relationships");
                return;
        }
    }

    // ---------------- PRACTICE ----------------

    private void loadStep() {

        answerInput.setText("");
        solutionText.setText("");

        switch (sutraNumber) {

            case 1:
                questionText.setText("Square of " + a);
                hintText.setText("Multiply first digit with next");
                break;

            case 2:
                questionText.setText("Use base 100 method");
                hintText.setText("Find deviations");
                break;

            case 3:
                questionText.setText((a % 10) + " × " + (b % 10));
                hintText.setText("Multiply units");
                break;

            default:
                questionText.setText(a + " × " + b);
                hintText.setText("Try solving");
        }
    }

    private void checkAnswer() {

        if (answerInput.getText().toString().isEmpty()) return;

        int user = Integer.parseInt(answerInput.getText().toString());

        if (user == correctAnswer) {
            solutionText.setText("✅ Correct!");
        } else {
            solutionText.setText("❌ Correct: " + correctAnswer);
        }
    }
}