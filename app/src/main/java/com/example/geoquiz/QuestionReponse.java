package com.example.geoquiz;

public class QuestionReponse {
    private String question;
    private  String reponse1;
     private  String reponse2;
     private  String reponse3;
     private String reponse4;
     private  int reponseId;

    public QuestionReponse(String question, String reponse1, String reponse2, String reponse3, String reponse4, int reponseId) {
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
        this.reponseId = reponseId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public String getReponse4() {
        return reponse4;
    }

    public void setReponse4(String reponse4) {
        this.reponse4 = reponse4;
    }

    public int getReponseId() {
        return reponseId;
    }

    public void setReponseId(int reponseId) {
        this.reponseId = reponseId;
    }

    @Override
    public String toString() {
        return " question: " + question +"; repons1: " + reponse1 + "; reponse2: " + reponse2
                + "; reponse3: " + reponse3 + "; reponse4: " + reponse4
                + "; reponseID: " + reponseId;
    }
}
