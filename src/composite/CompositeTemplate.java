package composite;

public class CompositeTemplate {
	public static void main(String args[]) {
		//just for demo
		Composite c1 = new Composite();
		c1.addComponent(new Leaf("J"));
		c1.addComponent(new Leaf("K"));
		c1.op();

		Composite c2 = new Composite();
		c2.addComponent(c1);
		c2.addComponent(new Leaf("Q"));
		c2.op();
	}
}

// COMPONENT
abstract class Component {
  abstract void op(); //OPERATION
}

// COMPOSITE
class Composite extends Component {
  ArrayList<Component> list;
  String name;

  public Composite(String n) {
  	  this.name = n;
      list = new ArrayList<Component>();
  }

  void addComponent(Component c) {
      list.add(c);
  }

  // OPERATION in COMPOSITE
  void op() {
    ListerIterator<Component> iterator = list.listIterator();
    while (iterator.hasNext()) {
       Component c = iterator.next();
       c.op();
    }
  }  
  System.out.println(name);
}

// LEAF
class Leaf extends Component {
  String name; 	

  public Leaf(String a) {
  	name = a;
  }

  // OPERATION in LEAF
  void op() {
 	 System.out.println(name); 
  }
}