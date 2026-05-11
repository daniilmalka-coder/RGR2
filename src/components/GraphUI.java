package components;
import core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class GraphUI extends JPanel {
    private Repository repo;
    private Color color = Color.RED;

    public void setRepo(Repository repo) { this.repo = repo; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (repo == null || repo.size() == 0) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        double w = getWidth(), h = getHeight();
        g2.draw(new Line2D.Double(0, h/2, w, h/2)); // Вісь X
        g2.draw(new Line2D.Double(w/2, 0, w/2, h)); // Вісь Y

        g2.setColor(color);
        for (int i = 0; i < repo.size(); i++) {
            double px = w/2 + repo.get(i).getX() * 10;
            double py = h/2 - repo.get(i).getY() * 10;
            g2.fill(new Ellipse2D.Double(px-3, py-3, 6, 6));
        }
    }
}
