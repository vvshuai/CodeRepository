import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) {
		Person p1 = new Person(10, "s");
		Person p2 = new Person(10, "s");
		String s1 = new String("abc");
		System.out.println(s1.hashCode());
		HashSet<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p2);
		System.out.println(set.size());
	}

	private static class Person{
		int age;
		String name;
		
		public Person(int age, String name) {
			//super();
			this.age = age;
			this.name = name;
		}
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			int nameHash = name.toUpperCase().hashCode();
			return nameHash ^ age;
		}
		@Override
		public boolean equals(Object object) {
			if(object==null) {
				return false;
			}
			
			if(this==object) {
				return true;
			}
			
			if(this.getClass() != object.getClass()) {
				return false;
			}
			
			Person person = (Person)object;
			return name.equals(person.name) && age==person.age;
		}
	}
}
