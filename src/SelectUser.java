public class SelectUser {
    private static String professorId;

    public static void main(String[] args) {
        int choice = UserInput.getUserType();

        User user = null; // User 타입으로 변수 선언 (핵심!)

        if (choice == 1) {
            String id = UserInput.getUserId();
            String name = UserInput.getUserName();
            String password = UserInput.getPassword();
            String studentId = UserInput.getStudentId();
            user = new Student(id, name, studentId, password);

        } else if (choice == 2) {
            String id = UserInput.getUserId();
            String name = UserInput.getUserName();
            String labLocation = UserInput.getLabLocation();
            user = new Professor(id, name, professorId, labLocation);

        } else {
            System.out.println("잘못된 선택입니다. 프로그램을 종료합니다.");
            return;
        }
    }
}