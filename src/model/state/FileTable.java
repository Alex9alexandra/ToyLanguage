package model.state;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Set;

import model.value.StringValue;

public interface FileTable {
    boolean isOpen(StringValue fileName);

    void addOpenFile(StringValue fileName, BufferedReader bufferReader);

    BufferedReader getOpenFile(StringValue fileName);

    void closeFile(StringValue fileName);

    Set<StringValue> keySet();

    String toString();

    Map<StringValue, BufferedReader> getContent();
}
