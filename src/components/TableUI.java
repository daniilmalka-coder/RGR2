package components;
import core.*;
import javax.swing.*;
import java.awt.*;

public class TableUI extends JPanel {
    private JTable table;
    private TableLogic logic = new TableLogic();

    public TableUI() {
        setLayout(new BorderLayout());
        table = new JTable(logic);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btns = new JPanel();
        JButton addBtn = new JButton("+");
        JButton delBtn = new JButton("-");

        addBtn.addActionListener(e -> {
            logic.getRepo().add(new core.Point("2026-05-08", 0, 0));
            logic.fireUpdate();
        });

        delBtn.addActionListener(e -> {
            if (logic.getRepo().size() > 0) {
                logic.getRepo().remove(logic.getRepo().size() - 1);
                logic.fireUpdate();
            }
        });

        btns.add(addBtn); btns.add(delBtn);
        add(btns, BorderLayout.SOUTH);
    }
    public TableLogic getLogic() { return logic; }
}
