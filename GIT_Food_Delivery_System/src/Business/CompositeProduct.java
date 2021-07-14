package Business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {
    private String menuTitle;
    private ArrayList<MenuItem> compositeProductList;

    public CompositeProduct(String menuTitle, ArrayList<MenuItem> compositeProductList) {
        this.menuTitle = menuTitle;
        this.compositeProductList = compositeProductList;
    }

    @Override
    public double getRating() {
        double r=0;int n=0;
        for(MenuItem p:compositeProductList)
        {
            r+=p.getRating();
            n++;
        }
        return r/n;
    }

    @Override
    public int getCalories() {
        int c=0;
        for(MenuItem p:compositeProductList)
            c+=p.getCalories();
        return c;
    }

    @Override
    public int getProtein() {
        int pr=0;
        for(MenuItem p:compositeProductList)
            pr+=p.getProtein();
        return pr;
    }

    @Override
    public int getFat() {
        int f=0;
        for(MenuItem p:compositeProductList)
            f+=p.getFat();
        return f;
    }

    @Override
    public int getSodium() {
        int s=0;
        for(MenuItem p:compositeProductList)
            s+=p.getSodium();
        return s;
    }

    @Override
    public int getPrice() {
        int price=0;
        for(MenuItem p:compositeProductList)
            price+=p.getPrice();
            return price;
    }

    @Override
    public String getTitle() {
        return menuTitle;
    }

    @Override
    public String toString() {
        return "CompositeProduct{" +
                "menuTitle='" + menuTitle + '\'' +
                ", compositeProductList=" + compositeProductList +
                '}';
    }
}
