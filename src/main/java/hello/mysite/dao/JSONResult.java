package hello.mysite.dao;

public class JSONResult {
    private String result;//success of fail
    private Object data;//if success=>set
    private String message;//if fail=> set

    public static JSONResult success(Object data) {//싱글톤으로 해결
        return new JSONResult(data);
    }
    public static JSONResult fail(String message) {//싱글톤으로 해결
        return new JSONResult(message);
    }
    private JSONResult() {
    }
    private JSONResult(Object data) {
        this.result="success";
        this.data=data;
    }
    private JSONResult(String message) {
        this.result="fail";
        this.message=message;
    }
    public String getResult() {
        return result;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
