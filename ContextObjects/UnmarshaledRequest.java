package ContextObjects;

public interface UnmarshaledRequest {
    public String getHost();
    public void setHost(String host);
    public long getPort();
    public long setPort(long newPort);
    public String getObjName();
    public void setObjName(String newName);
    public String getMethod();
    public void getMethod(String name);
    public void addInfo(Object info);
    // .............
}
