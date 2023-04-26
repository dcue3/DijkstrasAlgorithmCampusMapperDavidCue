public interface PathInterface extends Comparable<PathInterface> {
  // private double dist;
  // private String predecessor;
  // private String successor;
  // public Path(double dist, String pred, String succ) {
  // }
  public String getPredecessor();

  public String getSuccessor();

  public double getDistance();

}

