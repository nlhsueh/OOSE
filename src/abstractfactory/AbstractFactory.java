public class Main {
  public static void main(String args[]) {
      Client c = new Client();
      // 由主程式決定系列
      Factory1 cf1 = new Factory1();
      c.doSomeThing(cf1);
  }
}

// Client 只會看到 Abstract 的工廠和零件
class Client {
  // 不同的工廠，產生不同系列的零件
  void doSomeThing(AbstractFacotry f) {
    AbstractProductA a = f.createProductA();
    AbstractProductB b = f.createProductB();
    ...
  }
}

//抽象零件 A，定義零件 A 的規格（方法）
abstract class AbstractProductA {
  //零件 A 需要履行的功能
  public abstract void m1(int x); 
}

//系列1 的零件 A
class ProductA1 extends AbstractProductA {
  public abstract void m1(int x) {
     ... // 系列1 的實踐的方法
  }   
}
//系列2 的零件 A
class ProductA2 extends AbstractProductA {
  public abstract void m1(int x) {
     ... // 系列2 的實踐的方法
  }   
}

//抽象零件 B，定義零件 B 的規格（方法）
abstract class AbstractProductB {
  //零件 B 需要履行的功能
  public abstract void doit(int x); 
}

//系列一的零件 B
class ProductA1 extends AbstractProductA {
  public abstract void doit(int x) {
     ... // 系列一的實踐的方法
  }   
}
//系列二的零件 B
class ProductA2 extends AbstractProductA {
  public abstract void doit(int x) {
     ... // 系列二的實踐的方法
  }   
}

// 定義一個工廠所需要產生的產品
interface AbstractFactory {
  public AbstractProductA createProductA();
  public AbstractProductB createProductB();
}

// 系列一的工廠
class Factory1 implements AbstractFactory {
  AbstractProductA createProductA() {
    return new ProductA1();
  }
  AbstractProductB createProductB() {
    return new ProductB1();
  }
}

// 系列二的工廠
class Factory2 implements AbstractFactory {
  AbstractProductA createProductA() {
    return new ProductA2();
  }
  AbstractProductB createProductB() {
    return new ProductB2();
  }
}