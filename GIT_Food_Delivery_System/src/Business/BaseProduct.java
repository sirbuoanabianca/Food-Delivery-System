package Business;

import java.util.Objects;

public class BaseProduct extends MenuItem {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    public BaseProduct() { }
    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public int getProtein() {
        return protein;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setAllAttributes(double rating, int calories, int protein, int fat, int sodium, int price) {
        //numele produsului nu se modifica,deoarece dupa nume se face ulterior cautarea produsului
        //pentru a fi modificat
        this.calories=calories;
        this.protein=protein;
        this.rating=rating;
        this.sodium=sodium;
        this.fat=fat;
        this.price=price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n" +
                "Title='" + title + '\'' +
                ",\n rating=" + rating +
                ",\n calories=" + calories +
                ",\n protein=" + protein +
                ",\n fat=" + fat +
                ",\n sodium=" + sodium +
                ",\n price=" + price +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Double.compare(that.rating, rating) == 0 && calories == that.calories && protein == that.protein && fat == that.fat && sodium == that.sodium && price == that.price && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, calories, protein, fat, sodium, price);
    }

}
