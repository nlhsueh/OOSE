class Client {
  // 不同的AbstractFactory f，可以使用不同的一組Product (ex. A, B, ...)
  void doSomeThing(AbstractFacotry f) {
    AbstractProductA a = f.createProductA();
    AbstractProductB b = f.createProductB();
    ...
  }
}

abstract class AbstractProductA {}
class ProductA1 extends AbstractProductA {}
class ProductA2 extends AbstractProductA {}

abstract class AbstractProductB {}
class ProductB1 extends AbstractProductB {}
class ProductB2 extends AbstractProductB {}

abstract class AbstractFacotry {
  abstract AbstractProductA createProductA();
  abstract AbstractProductB createProductB();
}

class ConcreteFactory1 extends AbstractFactory {
  AbstractProductA createProductA() {
    return new ProductA1();
  }
  AbstractProductB createProductB() {
    return new ProductB1();
  }
}

class ConcreteFactory2  extends AbstractFactory {
  AbstractProductA createProductA() {
    return new ProductA2();
  }
  AbstractProductB createProductB() {
    return new ProductB2();
  }
}