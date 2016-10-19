package abstractfactory;

public class FactoryDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShoesFactory sf = new LeatherShoesFactory();
		makeShoes(sf);
		
		System.out.println();
		
		ShoesFactory sf2 = new SportShoesFactory();
		makeShoes(sf2);		

	}
	
	public static void makeShoes(ShoesFactory f) {
		System.out.println(f.makeShoesBody());
		System.out.println(f.makeShoesBottom());	
		System.out.println(f.makeShoesStrap());	
	}
}

class ShoesBody {
}

class ShoesBottom{	
}

class ShoesStrap{	
}

class SportShoesBody extends ShoesBody{	
}

class SportShoesStrap extends ShoesStrap{	
}

class SportShoesBottom extends ShoesBottom{	
}

class LeatherShoesBody extends ShoesBody{	
}

class LeatherShoesStrap extends ShoesStrap{	
}

class LeatherShoesBottom extends ShoesBottom{	
}

interface ShoesFactory {
	public ShoesBody makeShoesBody();
	public ShoesBottom makeShoesBottom();
	public ShoesStrap makeShoesStrap();
	
}

class SportShoesFactory implements ShoesFactory {
	public ShoesBody makeShoesBody() {
		return new SportShoesBody();
	}

	public ShoesBottom makeShoesBottom() {
		return new SportShoesBottom();
	}
	public ShoesStrap makeShoesStrap() {
		return new SportShoesStrap();
	}
}

class LeatherShoesFactory implements ShoesFactory {
	public ShoesBody makeShoesBody() {
		return new LeatherShoesBody();
	}

	public ShoesBottom makeShoesBottom() {
		return new LeatherShoesBottom();
	}
	public ShoesStrap makeShoesStrap() {
		return new LeatherShoesStrap();
	}	
}