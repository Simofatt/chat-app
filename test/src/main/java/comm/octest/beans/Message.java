package comm.octest.beans;

public class Message {
    public String from;
    public String to;
    public String msg;
    public String type;

    public Message(String from, String to, String msg, String type) {
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.type = type;
    }
}
