//Anthony Wittemann
//9/11/14
//Lab 3 Inheritance, Polymorphism

abstract class Pet implements Nameable{
	
	private String name;

	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public abstract String speak();

}


class Dog extends Pet{

	@Override
	public String speak() {
		return getName() + " says Woof!";
	}
	
}


class Duck extends Pet{

	@Override
	public String speak() {
		return getName() + " says Quack!";
	}
	
}

public class PetTest{
	public static void main(String[] args){
		//create the instances of the 2 pets and make them speak
		Pet dog1 = new Dog();
		dog1.setName("Sampson");
		System.out.println("Dog1: " + dog1.speak());
		
		Pet duck1 = new Duck();
		duck1.setName("Quackles");
		System.out.println("Duck1: " + duck1.speak());
		
		
		System.out.println();
		//prove that dog1 is a Dog, Pet, implements Nameable and isn't a Duck
		if(dog1.getClass().getSuperclass().equals(Pet.class)){
			System.out.println(dog1.getName() + " is a Pet");
		}
		else{
			System.out.println(dog1.getName() + " is NOT a Pet");
		}
		Class[] interfaces = dog1.getClass().getSuperclass().getInterfaces();
		if(interfaces[0].equals(Nameable.class)){
			System.out.println(dog1.getName() + " is Nameable");
		}
		else{
			System.out.println(dog1.getName() + " is NOT Nameable");
		}
		if(dog1.getClass().equals(Dog.class)){
			System.out.println(dog1.getName() + " is a Dog");
		}
		else{
			System.out.println(dog1.getName() + " is NOT a Dog");
		}
		if(!dog1.getClass().equals(Duck.class)){
			System.out.println(dog1.getName() + " is NOT a Duck");
		}
		else{
			System.out.println(dog1.getName() + " is a Dog or some other sort of pet");
		}
		
		
		System.out.println();
		//prove that duck1 is a Duck, Pet, implements Nameable and isn't a Dog
		if(duck1.getClass().getSuperclass().equals(Pet.class)){
			System.out.println(duck1.getName() + " is a Pet");
		}
		else{
			System.out.println(duck1.getName() + " is NOT a Pet");
		}
		Class[] interfaces1 = duck1.getClass().getSuperclass().getInterfaces();
		if(interfaces1[0].equals(Nameable.class)){
			System.out.println(duck1.getName() + " is Nameable");
		}
		else{
			System.out.println(duck1.getName() + " is NOT Nameable");
		}
		if(duck1.getClass().equals(Duck.class)){
			System.out.println(duck1.getName() + " is a Duck");
		}
		else{
			System.out.println(duck1.getName() + " is NOT a Duck");
		}
		if(!duck1.getClass().equals(Dog.class)){
			System.out.println(duck1.getName() + " is NOT a Dog");
		}
		else{
			System.out.println(duck1.getName() + " is a Duck or some other sort of pet");
		}
	}
}
