package User;

import Class._Class;
import io.netty.channel.ChannelId;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private static ConcurrentHashMap<Long, User> userHashMap;
    private static ConcurrentHashMap<ChannelId, User> channelUserConcurrentHashMap;

    static {
        userHashMap = new ConcurrentHashMap<Long, User>();
        channelUserConcurrentHashMap = new ConcurrentHashMap<>();
    }

    private String classPostion;
    private ArrayList<_Class> classArrayList;
    private long id;
    private String name;
    private String email;
    private String password;
    private boolean isActive = false;

    public User ( Long id, String name, String email, String password ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User findUser ( long id ) {
        return userHashMap.get( id );
    }

    public static void addUser ( User user ) {
        userHashMap.put( user.getId(), user );
    }

    public static void bindUser ( ChannelId id, User user ) {
        if ( channelUserConcurrentHashMap.containsKey( id ) ) {
            channelUserConcurrentHashMap.remove( id );
        }
        channelUserConcurrentHashMap.put( id, user );
    }

    public static void removeBind ( ChannelId channel ) {
        channelUserConcurrentHashMap.remove( channel );
    }

    public static User getUser ( ChannelId channel ) {
        return channelUserConcurrentHashMap.getOrDefault( channel, null );
    }

    public String getClassPosition () {
        return classPostion;
    }

    public void setClassPosition ( String postion ) {
        this.classPostion = postion;
    }

    public void addClasses ( ArrayList<_Class> classes ) {
        classArrayList = classes;
    }

    public ArrayList<_Class> getClassArrayList () {
        return classArrayList;
    }

    public String getUserType () {
        return "User";
    }

    public Long getId () {
        return this.id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    public void setActive ( boolean active ) {
        isActive = active;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

}
