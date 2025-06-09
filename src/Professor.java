public class Professor extends User {
    private String labLocation;

    public Professor(String name, String professorId, String id, String password)
    {
        super(id, name, professorId, password, UserType.Professor);
        this.labLocation = "";
    }

    @Override
    public void displayInfo() {
        System.out.println("--- 교수 정보 ---");
        System.out.println("ID: " + getId());
        System.out.println("이름: " + getName());
        System.out.println("연구실: " + this.labLocation);
        System.out.println("-----------------");
    }

    // 연구실 위치 설정/조회 메서드 추가 (선택)
    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }

    public String getLabLocation() {
        return labLocation;
    }
}