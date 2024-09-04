public class Product {
    private int EanCode;
    private int PLUCode;
    private String name;
    private String description;
    private double price;

    public Product(int eanCode, int PLUCode, String name, String description, double price) {
        EanCode = eanCode;
        this.PLUCode = PLUCode;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getEanCode() {
        return EanCode;
    }

    public void setEanCode(int eanCode) {
        EanCode = eanCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPLUCode() {
        return PLUCode;
    }

    public void setPLUCode(int PLUCode) {
        this.PLUCode = PLUCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
