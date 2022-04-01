package hello.mysite.dao;

public enum Gender {
    MAN("남자"), FEMALE("여자");

    private final String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
