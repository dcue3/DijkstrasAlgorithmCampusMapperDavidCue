// path object Interface
public interface PathInterface {
    //private double data;
    //private String predecessor;
    //private String successor;
    //public Path(double data, String pred, String succ) {
    //}
    public String getPredecessor();
    public String getSuccessor();
    public double getDistance();
    void addPath(Path path);
    void removePath(Path path);
    void modifyPath(Path path, Building newStart, Building newEnd, double newLength);

}
