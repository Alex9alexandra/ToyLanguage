package model.state;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.value.StringValue;

public class MapFileTable implements FileTable {
    private final Map<StringValue, BufferedReader> fileTable = new HashMap<>();

    @Override
    public boolean isOpen(StringValue fileName) {
        return fileTable.containsKey(fileName);
    }

    @Override
    public void addOpenFile(StringValue fileName, BufferedReader bufferReader) {
        fileTable.put(fileName, bufferReader);
    }

    @Override
    public BufferedReader getOpenFile(StringValue fileName) {
        return fileTable.get(fileName);
    }

    @Override
    public void closeFile(StringValue fileName) {
        try {
            BufferedReader br = fileTable.remove(fileName);
            if (br != null)
                br.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing file: " + fileName, e);
        }
    }

    @Override
    public Set<StringValue> keySet() {
        return fileTable.keySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FileTable:\n");
        for (StringValue key : fileTable.keySet()) {
            sb.append(key).append("\n");
        }
        return sb.toString();
    }
}
