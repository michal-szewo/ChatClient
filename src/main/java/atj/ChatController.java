package atj;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatController  implements AutoCloseable{
	
	@FXML
	TextField messageTextField;
	@FXML
	Label welcomeLabel;
	@FXML
	WebView webViewMessages;
	@FXML
	Circle circleImage;
	@FXML
	ImageView sendImageView;
	
	private String userName = ""; // nazwa wybrana przez użytkownika
	private String senderName; // nazwa nadawcy wiadomości
	private String host ; // adres serwera
	private Socket socket; // obiekt gniazda
	private BufferedReader inputBufferedReader; // bufor wejściowy (dane odebrane z serwera)
	private PrintWriter outputPrintWriter; // bufor wyjściowy (dane do wysłania)
	private final int PROTOCOL_PREFIX_LENGTH = 3; // długość słów kluczowych komunikatów
	private Document messagesLayout;
	Task<Void> task;
	@FXML
	private void initialize() {
		String welcome = "Nice to see you there!This is a welcome message. " +
		"Say hello to other users.";
		
		messagesLayout = Jsoup.parse(
		"<html><head><meta charset='UTF-8'>" +
		"</head><body><ul><li class=\"welcome\"><div class=\"message\"><div class=\"content\">" +
		welcome +
		"</div></div></li></ul></body></html>",
		"UTF-16",
		
		Parser.xmlParser()
	);
	webViewMessages.getEngine().loadContent(messagesLayout.html());
	//webViewMessages.getEngine().setUserStyleSheetLocation(
	//getClass().getResource("application.css").toString());
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
		welcomeLabel.setText("Hello " + this.userName + "!");
		Image myImage = new Image(new File("res/harveyspecter.png").toURI().toString());
		ImagePattern pattern = new ImagePattern(myImage);
		circleImage.setFill(pattern); 
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void close() throws Exception {
		if (socket != null) {
			socket.close();
			
		}
	}
	
}
