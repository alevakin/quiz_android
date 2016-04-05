package de.alexey.quiz;

public class Question {

    private String Q;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String right_answer;

    public Question(String quest, String a, String b, String c, String d, String ans){
        Q = quest;
        choiceA = a;
        choiceB = b;
        choiceC = c;
        choiceD = d;
        right_answer = ans;
    }

    public Question(){
        Q = "";
        choiceA = "";
        choiceB = "";
        choiceC = "";
        choiceD = "";
        right_answer = "";
    }

    public String get_question(){
        return Q;
    }

    public String get_A(){
        return choiceA;
    }

    public String get_B(){
        return choiceB;
    }

    public String get_C(){
        return choiceC;
    }

    public String get_D(){
        return choiceD;
    }

    public String get_ans(){
        return right_answer;
    }

}
