public class Building {
    private String name;
    private double x;
    private double y;
    private String department;
    // any other relevant attributes

    public Building(String name, double x, double y, String department) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.department = department;
    }

    // getters and setters for attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
