package com.example.geoquiz;

public class CountryInfo {
    int id;
    String pays, capitale, devise, flag, monument, population;

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

  /*  public CountryInfo(int id, String pays, String capitale, String devise, String flag, String monument) {
        this.id = id;
        this.pays = pays;
        this.capitale = capitale;
        this.devise = devise;
        this.flag = flag;
        this.monument = monument;
    }*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMonument() {
        return monument;
    }

    public void setMonument(String monument) {
        this.monument = monument;
    }

    @Override
    public String toString() {
        return " ID: " + id +"; Pays: " + pays + "; Capitale: " + capitale
                + "; Population: " + population + "; Devise: " + devise
                + "; Monument: " + monument+ "; Flag: " + flag;
    }
}
