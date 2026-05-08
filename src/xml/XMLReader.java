package xml;
import core.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class XMLReader {
    public static Repository read(String path) {
        Repository repo = new Repository();
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(new File(path), new DefaultHandler() {
                Point p; String tag;
                @Override public void startElement(String u, String l, String q, Attributes a) {
                    tag = q; if (q.equals("point")) p = new Point();
                }
                @Override public void characters(char[] ch, int s, int len) {
                    String val = new String(ch, s, len).trim();
                    if (val.isEmpty()) return;
                    if (tag.equals("date")) p.setDate(val);
                    else if (tag.equals("x")) p.setX(Double.parseDouble(val));
                    else if (tag.equals("y")) p.setY(Double.parseDouble(val));
                }
                @Override public void endElement(String u, String l, String q) {
                    if (q.equals("point")) repo.add(p);
                }
            });
        } catch (Exception e) { e.printStackTrace(); }
        return repo;
    }
}