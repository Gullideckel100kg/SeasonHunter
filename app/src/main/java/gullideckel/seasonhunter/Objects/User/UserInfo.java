package gullideckel.seasonhunter.Objects.User;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfo
{
    private String userId = "";
    private boolean isAd = true;
    private List<String> addedCompanies = new ArrayList<>();
    private List<Message> messages;
    private String userName;
    private String email;

    public UserInfo(String email, String userId)
    {
        this.email = email;
        this.userId = userId;
        messages = new ArrayList<>();
    }

    public UserInfo(){}

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public boolean isAd()
    {
        return isAd;
    }

    public void setAd(boolean ad)
    {
        isAd = ad;
    }

    public List<String> getAddedCompanies()
    {
        return addedCompanies;
    }

    public void setAddedCompanies(List<String> addedCompanies)
    {
        this.addedCompanies = addedCompanies;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
