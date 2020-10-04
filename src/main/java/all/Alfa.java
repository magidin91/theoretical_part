package all;

class Alfa {
 Alfa getThis() {
 return this;
 }
 
 public void doAlfa() {
 System.out.println("что-то делаем в Alfa");
 }
}

class Beta extends Alfa {
 public void doBeta() {
 System.out.println("что-то делаем в Beta");
 }
}

class Omega extends Alfa{
 public void doOmega() {
  System.out.println("что-то делаем с Omega");
 }
}