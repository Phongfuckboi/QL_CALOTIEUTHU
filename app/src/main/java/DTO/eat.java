package DTO;

import java.io.Serializable;

public class eat  {
  String food;
  String calories;

    public eat() {
    }

    public eat(String food, String calories) {
        this.food = food;
        this.calories = calories;
    };

    @Override
    public String toString() {
        return
                "food: '" + food + '\'' +
                ", calories='" + calories + '\'' +
                '.';
    }

    public  String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public  String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
