package core;
import java.util.EventListener;

public interface DataListener extends EventListener {
    void dataChanged(DataEvent e);
}