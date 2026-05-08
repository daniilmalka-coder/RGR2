package components;
import core.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableLogic extends AbstractTableModel {
    private Repository repo;
    private ArrayList<DataListener> listeners = new ArrayList<>();

    public void setRepo(Repository repo) {
        this.repo = repo;
        fireUpdate();
    }
    public Repository getRepo() { return repo; }

    @Override public int getRowCount() { return repo != null ? repo.size() : 0; }
    @Override public int getColumnCount() { return 3; }
    @Override public String getColumnName(int c) { return new String[]{"Дата", "X", "Y"}[c]; }
    @Override public boolean isCellEditable(int r, int c) { return true; }

    @Override public Object getValueAt(int r, int c) {
        Point p = repo.get(r);
        return (c == 0) ? p.getDate() : (c == 1) ? p.getX() : p.getY();
    }

    @Override public void setValueAt(Object v, int r, int c) {
        try {
            Point p = repo.get(r);
            if (c == 0) p.setDate(v.toString());
            else if (c == 1) p.setX(Double.parseDouble(v.toString()));
            else if (c == 2) p.setY(Double.parseDouble(v.toString()));
            fireUpdate();
        } catch (Exception e) {}
    }

    public void addListener(DataListener l) { listeners.add(l); }
    public void fireUpdate() {
        fireTableDataChanged();
        for (DataListener l : listeners) l.dataChanged(new DataEvent(this));
    }
}