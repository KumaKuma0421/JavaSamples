package XML;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import Exception.ApplicationException;

public class XMLSample {
    public class XmlData {
        public String Name;
        public int Age;
        public String Interest;
    }

    public List<XmlData> read(String xmlFile) throws ApplicationException {
        var response = new ArrayList<XmlData>();

        try {
            // XML文書読み込みの準備
            var xmlFactory = DocumentBuilderFactory.newInstance();
            var xmlDocumentBuilder = xmlFactory.newDocumentBuilder();
            var xmlDocument = xmlDocumentBuilder.parse(xmlFile);
            var xmlDocumentRoot = xmlDocument.getDocumentElement();

            // <persons name="@@">
            // --<person name="@@">
            // ----<age>
            // ----<interest>

            // ルート要素のノード名を取得する
            var rootNode = xmlDocumentRoot.getNodeName();
            System.out.println("親要素：" + rootNode);

            // ルート要素の属性を取得する
            var rootNodeAttributeName = xmlDocumentRoot.getAttribute("name");
            System.out.println("属性(name)：" + rootNodeAttributeName);

            // ルート要素の子ノードを取得する
            var rootNodeChildren = xmlDocumentRoot.getChildNodes();

            for (int i = 0; i < rootNodeChildren.getLength(); i++) {
                var childNode = rootNodeChildren.item(i);

                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                    var data = new XmlData();
                    var element = (Element) childNode;
                    if (element.getNodeName().equals("person")) {
                        System.out.println(" 名前：" + element.getAttribute("name"));
                        data.Name = element.getAttribute("name");
                        var personChildren = childNode.getChildNodes();
                        for (int j = 0; j < personChildren.getLength(); j++) {
                            var personNode = personChildren.item(j);
                            if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                                if (personNode.getNodeName().equals("age")) {
                                    System.out.println(" 年齢：" + personNode.getTextContent());
                                    data.Age = Integer.parseInt(personNode.getTextContent());
                                } else if (personNode.getNodeName().equals("interest")) {
                                    System.out.println(" 趣味：" + personNode.getTextContent());
                                    data.Interest = personNode.getTextContent();
                                }
                            }
                        }
                    }
                    response.add(data);
                }
            }
        } catch (IOException e) {
            throw new ApplicationException(e);
        } catch (ParserConfigurationException e) {
            throw new ApplicationException(e);
        } catch (SAXException e) {
            throw new ApplicationException(e);
        }

        return response;
    }

    public void write(List<XmlData> data) throws ApplicationException {
        try {
            // XML文書書き込み読み込みの準備
            var xmlFactory = DocumentBuilderFactory.newInstance();
            var xmlDocumentBuilder = xmlFactory.newDocumentBuilder();
            var xmlDocument = xmlDocumentBuilder.newDocument();

            // <Persons Version="@@">
            // --<Person>
            // ----<Name>
            // ----<Age>
            // ----<Interest>

            // ルート要素のノード名を作成する
            var rootNode = xmlDocument.createElement("Persons");
            xmlDocument.appendChild(rootNode);

            // ルート要素の属性を作成する
            rootNode.setAttribute("Version", "1.0");

            // ルート要素の子ノードを作成する
            for (var item : data) {
                var nodePerson = xmlDocument.createElement("Person");
                rootNode.appendChild(nodePerson);

                createChildNode(xmlDocument, nodePerson, "Name", item.Name);
                createChildNode(xmlDocument, nodePerson, "Age", String.valueOf(item.Age));
                createChildNode(xmlDocument, nodePerson, "Interest", item.Interest);
            }

            // TODO:ここでファイル操作するより、呼び元で準備してもらうほうが、機能的にスッキリする。
            // var file = new File("src/XML/response.xml");
            var output = new StringWriter();
            var xmlTransformerFactory = TransformerFactory.newInstance();
            var transformer = xmlTransformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.transform(new DOMSource(xmlDocument), new StreamResult(output));

            System.out.println(output);
            // TODO:StringWriter.close()が必要ではないか？
            // TODO:SpotBugsで確認する

        } catch (ParserConfigurationException e) {
            throw new ApplicationException(e);
        } catch (TransformerConfigurationException e) {
            throw new ApplicationException(e);
        } catch (TransformerException e) {
            throw new ApplicationException(e);
        }
    }

    private void createChildNode(Document document, Element element, String nodeName, String item) {
        var node = document.createElement(nodeName);
        node.setTextContent(item);
        node.setAttribute("Modify", LocalDateTime.now().toString());
        element.appendChild(node);
    }
}
