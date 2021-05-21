package DTO;

public class Info {
    int Weighthientai;
    int Weightmongmuon;
    int Calorieshientai;
    int Caloriesmuctieu;
    int Id;

    public Info() {
    }

    public Info(int weighthientai, int weightmongmuon, int calorieshientai, int caloriesmuctieu ,int id){
        Weighthientai = weighthientai;
        Weightmongmuon = weightmongmuon;
        Calorieshientai = calorieshientai;
        Caloriesmuctieu = caloriesmuctieu;
        Id=id;

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getWeighthientai() {
        return Weighthientai;
    }

    public void setWeighthientai(int weighthientai) {
        Weighthientai = weighthientai;
    }

    public int getWeightmongmuon() {
        return Weightmongmuon;
    }

    public void setWeightmongmuon(int weightmongmuon) {
        Weightmongmuon = weightmongmuon;
    }

    public int getCalorieshientai() {
        return Calorieshientai;
    }

    public void setCalorieshientai(int calorieshientai) {
        Calorieshientai = calorieshientai;
    }

    public int getCaloriesmuctieu() {
        return Caloriesmuctieu;
    }

    public void setCaloriesmuctieu(int caloriesmuctieu) {
        Caloriesmuctieu = caloriesmuctieu;
    }

    @Override
    public String toString() {
        return "Info{" +
                "Weighthientai=" + Weighthientai +
                ", Weightmongmuon=" + Weightmongmuon +
                ", Calorieshientai=" + Calorieshientai +
                ", Caloriesmuctieu=" + Caloriesmuctieu +
                ",id="+Id+
                '}';
    }
}
