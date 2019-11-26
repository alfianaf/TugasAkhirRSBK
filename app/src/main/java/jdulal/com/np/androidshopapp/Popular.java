package jdulal.com.np.androidshopapp;

public class Popular {
    private String product_title, produt_price, product_image;

    public Popular() {
    }

    public Popular(String product_title, String produt_price, String product_image) {
        this.product_title = product_title;
        this.produt_price = produt_price;
        this.product_image = product_image;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProdut_price() {
        return produt_price;
    }

    public void setProdut_price(String produt_price) {
        this.produt_price = produt_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
