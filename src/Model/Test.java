package Model;

import java.io.Serializable;


public class Test implements Serializable{
    private String question;
    private String answer;
    private int time;

    public Test(String question, String answer,int time) {
            this.time=time;
            this.question = question;
            this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getTime() {
        return time;
    }
}
