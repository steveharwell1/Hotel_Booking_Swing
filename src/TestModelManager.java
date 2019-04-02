
public class TestModelManager {

	public static void main(String[] args) {
		UserManager manager = new UserManager();
		UserOptional result = manager.createUser("Dave");
		if(result.succeded()) {
			System.out.println("The user's name is " + result.getUser().getName());
		}
		else {
			System.out.println(result.getErrMsg());
		}
	}

}
