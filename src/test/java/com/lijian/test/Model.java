package com.lijian.test;

import java.util.Objects;

public class Model
{
    private String  date;

    public Model(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Model{" +
                "date='" + date + '\'' +
                '}';
    }


    public int compareTo(Object o) {
       return ((Model)o).getDate().compareTo(this.getDate());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(date, model.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date);
    }
}
