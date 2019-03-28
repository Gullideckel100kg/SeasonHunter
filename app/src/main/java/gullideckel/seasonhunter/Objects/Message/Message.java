package gullideckel.seasonhunter.Objects.Message;

public class Message
{
    private String usrId;
    private String message;

    public Message(String usrId, String message)
    {
        this.usrId = usrId;
        this.message = message;
    }

    public String getUsrId()
    {
        return usrId;
    }

    public String getMessage()
    {
        return message;
    }
}
