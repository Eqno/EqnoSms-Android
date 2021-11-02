package cn.edu.ujn.EqnoSms;

public class ChatItem {
    public static final int RECEIVE = 0;
    public static final int SEND = 1;

    private final int type;
    private final String phone;
    private final String message;
    public ChatItem(int type, String phone, String message) {
        this.type = type;
        this.phone = phone;
        this.message = message;
    }
    public int getType() { return type; }
    public String getPhone() { return phone; }
    public String getMessage() { return message; }
}