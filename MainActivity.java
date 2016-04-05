package de.alexey.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Vector;

import android.view.LayoutInflater;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private TextView ans1;
    private TextView ans2;
    private TextView ans3;
    private TextView ans4;
    private EditText output;
    private View view;
    private Vector<Question> vec = new Vector<Question>();
    private Question currently_question;
    private int i=0;
    private int score = 0;
    final Context context = this;
    private EditText new_q;
    private EditText new_a, new_b, new_c, new_d, new_r;

    Question q1 = new Question("What is the capital of Germany?", "Berlin", "Hamburg", "Munich", "Stuttgard", "a");
    Question q2 = new Question("Square root of 144 is ", "10", "11", "12", "13", "c");
    Question q3 = new Question("Which city has the largest population?", "Omsk", "Hamburg", "Tokyo", "New York", "c");
    Question q4 = new Question("Which is the largest planet in the solar system?", "Earth", "Mars", "Jupiter", "Saturn", "c");
    Question q5 = new Question("Name the Yellow Telly Tubby.", "Tinky-Winky", "Dipsy", "Laa-Laa", "Po", "c");
    Question q6 = new Question("What are Alpha Centauri and Sirius?", "Books", "Planets", "Cities", "Stars", "d");
    Question q7 = new Question("Which country is bordered by both the Atlantic and Indian Oceans?", "Germany", "South Africa", "Usa", "China", "b");
    Question q8 = new Question("Which is the world's second-largest country in land area?", "Canada", "USA", "Russia", "China", "a");
    Question q9 = new Question("The first television set was sold in which year?", "1914", "1928", "1930", "1935", "b");
    Question q10 = new Question("How to say 'Hello' in Russian", "Prost", "Privet", "Cheers", "Na Zdorovie", "b");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    public void start(View view){
        setContentView(R.layout.activity_main);
        question = (TextView)findViewById(R.id.textView);
        ans1 = (TextView)findViewById(R.id.textView2);
        ans2 = (TextView)findViewById(R.id.textView3);
        ans3 = (TextView)findViewById(R.id.textView4);
        ans4 = (TextView)findViewById(R.id.textView5);
        output = (EditText)findViewById(R.id.editText);
        i = 0;
        score = 0;
        output.setText("Points: " + score);

        vec.add(q1);
        vec.add(q2);
        vec.add(q3);
        vec.add(q4);
        vec.add(q5);
        vec.add(q6);
        vec.add(q7);
        vec.add(q8);
        vec.add(q9);
        vec.add(q10);

        removeDuplicates(vec);

        Collections.shuffle(vec);
        setQuestion(0);

    }

    public static void removeDuplicates(Vector v){
        for(int k = 0; k < v.size(); k++) {
            for(int j = 0; j < v.size(); j++) {
                if(k != j) {
                    if(v.elementAt(k).equals(v.elementAt(j))){
                        v.removeElementAt(j);
                    }
                }
            }
        }
    }

    public void add_button(View view) {
        setContentView(R.layout.add_q);
        new_q = (EditText)findViewById(R.id.new_q);
        new_a = (EditText)findViewById(R.id.new_a);
        new_b = (EditText)findViewById(R.id.new_d);
        new_c = (EditText)findViewById(R.id.new_c);
        new_d = (EditText)findViewById(R.id.new_b);
        new_r = (EditText)findViewById(R.id.new_r);

    }

    public void save (View view) {

        setContentView(R.layout.start);
        Question nq = new Question(new_q.getText().toString(), new_a.getText().toString(), new_b.getText().toString(), new_c.getText().toString(), new_d.getText().toString(), new_r.getText().toString());
        vec.add(nq);
        removeDuplicates(vec);
        Collections.shuffle(vec);
    }


    public void quit(View view){
        System.exit(0);
    }

    public void setQuestion (int NrQ) {
        currently_question = vec.get(NrQ);

        question.setText(currently_question.get_question());
        ans1.setText(currently_question.get_A());
        ans2.setText(currently_question.get_B());
        ans3.setText(currently_question.get_C());
        ans4.setText(currently_question.get_D());
    }



    public boolean rightAnswer(TextView view){
        if((view == ans1) && (currently_question.get_ans().equals("a")))
            return true;
        else if ((view == ans2) && (currently_question.get_ans().equals("b")))
            return true;
        else if((view == ans3) && (currently_question.get_ans().equals("c")))
            return true;
        else if ((view == ans4) && (currently_question.get_ans().equals("d")))
            return true;
        else
            return false;
    }

    public void onClick(View view) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Try again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                setContentView(R.layout.start);
                            }
                        })
                .setNegativeButton("Quit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                System.exit(0);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        if(view == ans1) {
            if(rightAnswer(ans1)){
                Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
                i++;
                if(i == 5) {
                    alertDialog.setTitle("Congratulation!");
                    alertDialog.show();
                }
                if (i < 5) {
                    setQuestion(i);
                }

                score++;
                output.setText("Points: " + score);

            } else {
                alertDialog.setTitle("Wrong! Try again");
                alertDialog.show();
            }
        }

        if(view == ans2) {
            if(rightAnswer(ans2)) {
                Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
                i++;
                if(i == 5) {
                    alertDialog.setTitle("Congratulation!");
                    alertDialog.show();
                }
                if(i < 5) {
                    setQuestion(i);
                }

                score++;
                output.setText("Points: " + score);

            } else {
                alertDialog.setTitle("Wrong! Try again");
                alertDialog.show();
            }
        }

        if(view == ans3) {
            if(rightAnswer(ans3)) {
                Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
                i++;
                if(i == 5) {
                   alertDialog.setTitle("Congratulation!");
                    alertDialog.show();
                }
                if(i < 5) {
                    setQuestion(i);
                }

                score++;
                output.setText("Points: " + score);

            } else {
                alertDialog.setTitle("Wrong! Try again");
                alertDialog.show();
            }
        }

        if(view == ans4) {
            if(rightAnswer(ans4)) {
                Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
                i++;
                if(i == 5) {
                    alertDialog.setTitle("Congratulation!");
                    alertDialog.show();
                }
                if(i < 5) {
                    setQuestion(i);
                }

                score++;
                output.setText("Points: " + score);

            } else {
                alertDialog.setTitle("Wrong! Try again");
                alertDialog.show();
            }

        }

        }
}