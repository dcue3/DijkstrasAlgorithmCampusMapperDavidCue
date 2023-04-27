public class Path {
    private Building start;
    private Building end;
    private double length;
    // any other relevant attributes

    public Path(Building start, Building end, double length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    // getters and setters for attributes
    public Building getStart() {
        return start;
    }

    public void setStart(Building start) {
        this.start = start;
    }

    public Building getEnd() {
        return end;
    }

    public void setEnd(Building end) {
        this.end = end;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

}
