package gullideckel.seasonhunter.Objects.Edit;

public class DocEditValue
{
    private Object obj;
    private String id;
    private String usrId;

    public DocEditValue(String id, String usrId)
    {
        this.id = id;
        this.usrId = usrId;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }

    public String getId()
    {
        return id;
    }

    public String getUsrId()
    {
        return usrId;
    }
}
