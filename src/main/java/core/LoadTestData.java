//@@author A0118894N
package core;

import java.io.File;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadTestData extends BaseCommand {

	private TaskRuby main;
	
	public LoadTestData(StorageBackend storage, TaskRuby main) {
		super(storage);
		this.main = main;
	}

	@Override
	public void execute(String[] args) throws CommandException, SQLException {
		System.out.println("test");
		File xmlFile = new File("SampleData.xml");
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder = dbF.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("task");
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String desc = element.getElementsByTagName("desc").item(0).getTextContent();
					String start = element.getElementsByTagName("start_date").item(0).getTextContent();
					String end = element.getElementsByTagName("end_date").item(0).getTextContent();
					String prio = element.getElementsByTagName("priority").item(0).getTextContent();
					String composed = desc + " \\d " + start + " \\D " + end + " \\p " + prio;
					main.getAvailableCommands().get("add").execute(composed.split("\\s+"));		
				}
			}
		} catch(Exception e) {
			throw new CommandException(e.getMessage());
		}
	}

	@Override
	public String getHelpString() {
		return null;
	}

}
