package adaptor.classadaptor;
// Adaptee的specificRequest()對應到Target的request()
interface Target {
    public void request(Object arg);
}

class Adaptee {
    public void SpecificRequest(Object arg) {
        //...
    }
}

class Adapter extends Adaptee implements Target {
    public void request(Object arg) {
        this.SpecificRequest(arg);
    }
    
}

class Client {
    // t 可以是一個Target,  或是一個Adapter (實作了Target)
    public void makeRequest(Target t, Object o) {
      t.request(o);
    }
}