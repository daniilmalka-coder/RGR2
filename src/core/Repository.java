package core;
import java.util.ArrayList;

public class Repository {
    private ArrayList<Point> list = new ArrayList<>();
    public void add(Point p) { list.add(p); }
    public void remove(int i) { if (i >= 0 && i < list.size()) list.remove(i); }
    public Point get(int i) { return list.get(i); }
    public int size() { return list.size(); }
}