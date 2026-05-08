package xml;
import core.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLWriter {
    public static void save(Repository repo, String path) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element root = doc.createElement("points");
            doc.appendChild(root);

            for (int i = 0; i < repo.size(); i++) {
                Point p = repo.get(i);
                Element el = doc.createElement("point");

                Element d = doc.createElement("date"); d.setTextContent(p.getDate());
                Element x = doc.createElement("x"); x.setTextContent(String.valueOf(p.getX()));
                Element y = doc.createElement("y"); y.setTextContent(String.valueOf(p.getY()));

                el.appendChild(d); el.appendChild(x); el.appendChild(y);
                root.appendChild(el);
            }

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (Exception e) { e.printStackTrace(); }
    }
}