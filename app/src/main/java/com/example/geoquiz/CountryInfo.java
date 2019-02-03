package com.example.geoquiz;

public class CountryInfo {
    private static final String TAG = "CountryInfo";
    private int id;
    private String pays, capitale, devise, flag, monument, population;

    String getPopulation() {
        return population;
    }

    void setPopulation(String population) {
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String getPays() {
        return pays;
    }

    void setPays(String pays) {
        this.pays = pays;
    }

    String getCapitale() {
        return capitale;
    }

    void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    String getDevise() {
        return devise;
    }

    void setDevise(String devise) {
        this.devise = devise;
    }

    String getFlag() {
        return flag;
    }

    void setFlag(String flag) {
        this.flag = flag;
    }

    String getMonument() {
        return monument;
    }

    void setMonument(String monument) {
        this.monument = monument;
    }

    @Override
    public String toString() {
        return " ID: " + id + "; Pays: " + pays + "; Capitale: " + capitale
                + "; Population: " + population + "; Devise: " + devise
                + "; Monument: " + monument + "; Flag: " + flag;
    }
}
