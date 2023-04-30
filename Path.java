public class Path implements PathInterface {
  private double dist;
  private String predecessor;
  private String successor;

  public Path(double dist, String pred, String succ) {
    this.dist = dist;
    predecessor = pred;
    successor = succ;
  }

  @Override
  public String getPredecessor() {
    return predecessor;
  }

  @Override
  public String getSuccessor() {
    return successor;
  }

  @Override
  public double getDistance() {
    return dist;
  }

  @Override
  public int compareTo(PathInterface o) {
    if (this.dist == o.getDistance() && this.predecessor.equals(o.getPredecessor())
        && this.successor.equals(o.getSuccessor())) {
      return 0;
    }
    if (this.dist == o.getDistance() && this.predecessor.equals(o.getSuccessor())
        && this.successor.equals(o.getPredecessor())) {
      return 0;
    }
    return -1;
  }

}
