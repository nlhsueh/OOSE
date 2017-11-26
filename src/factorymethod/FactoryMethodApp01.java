import java.util.Scanner;

abstract class Doc {
	protected String content;

	abstract public void save(String word);
	abstract void show();
}

class TextDoc extends Doc {
	//just a simulation, using textDoc approach to
	// save file
	public void save(String word) {
		content = word; 
	}

	public void show() {
		System.out.println("Text Doc shows: " + content);
	}
}

class WordDoc extends Doc {
	//just a simulation, using wordDoc approach to
	// save file
	public void save(String word) {
		content = "**" + word + "**"; 
	}

	public void show() {
		System.out.println("Word Doc shows: " + content);
	}
}

abstract class App {
	public void readToFile() {
		Doc d;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input your name:");
		String name = sc.nextLine();

		d = createDoc();
		d.save(name);		
		d.show();
	}

	abstract public Doc createDoc();
}



// YOU CODE HERE
class TextApp extends App {

}

class WordApp extends App {

}


public class FactoryMethodApp01 {

	public static void main(String[] args) {

		App textApp = new TextApp();
		textApp.readToFile();

		// YOU CODE HERE, create a wordApp
	}
}






