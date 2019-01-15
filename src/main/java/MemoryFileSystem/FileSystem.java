package MemoryFileSystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    private final Directory root;

    public FileSystem(Directory root) {
        this.root = new Directory(null, "/");
    }

    public List<Entry> resolve(String path) {
        assert path.startsWith("/");
        String[] components = path.substring(1).split("/");
        List<Entry> entries = new ArrayList<>(components.length + 1);
        entries.add(root);

        Entry entry = root;
        for (String component : components) {
            if (entry == null || !(entry instanceof Directory)) {
                throw new IllegalArgumentException("invalid path :" + path);
            }
            if (!component.isEmpty()) {
                entry = ((Directory) entry).getChild(component);
                entries.add(entry);
            }
        }

        return entries;
    }

    public void mkdir(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("Directory Already Exists:" + path);
        }
        String[] components = path.split("/");
        final String dirName = components[components.length - 1];
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        Directory newDir = new Directory(parent, dirName);
        parent.addEntry(newDir);
    }

    public void createFile(String path) {
        List<Entry> entries = resolve(path);
        if (entries.get(entries.size() - 1) != null) {
            throw new IllegalArgumentException("File Already Exists:" + path);
        }
        String[] components = path.split("/");
        final String fileName = components[components.length - 1];
        final Directory parent = (Directory) entries.get(entries.size() - 2);
        File newFile = new File(parent, fileName, 0);
        parent.addEntry(newFile);
    }

//    public void delete(String path) {
//
//    }
//
//    public Entry[] list(String path) {
//
//    }
//
//    public int count() {
//
//    }

}
