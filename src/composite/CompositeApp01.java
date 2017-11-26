import java.util.ArrayList;
import java.util.ListIterator;

// COMPONENT
abstract class AbstractFile {
  // ONE LINE CODE
}

// COMPOSITE
class Folder extends AbstractFile {
  ArrayList<AbstractFile> elements;

  public Folder() {
      elements = new ArrayList<AbstractFile>();
  }

  void add(AbstractFile c) {
      elements.add(c);
  }

  // OPERATION in COMPOSITE
  int getSize() {
    // YOU CODE HERE
    return size;
  }  
}

// LEAF
class TextFile extends AbstractFile {
  int size;

  public TextFile(int s) {
  	this.size = s;
  }

  // OPERATION in LEAF
  int getSize() {
    // ONE LINE CODE
  }
}


// LEAF
class ZipFile extends AbstractFile {
  int size;
  double rate;

  public ZipFile(int size, double rate) {
    // YOU CODE HERE
  }

  // OPERATION in LEAF
  int getSize() {
    return (int) (size * rate);
  }
}

public class CompositeApp02 {
  public static void main(String[] args) {
    Folder f1 = new Folder();
    f1.add(new TextFile(120));
    System.out.println(f1.getSize());

    TextFile text = new TextFile(1200);
    ZipFile zipFile = new ZipFile(900, 0.4);

    Folder f2 = new Folder();
    f2.add(text);
    f2.add(zipFile);
    f2.add(f1);

    System.out.println(f2.getSize());
  }
}