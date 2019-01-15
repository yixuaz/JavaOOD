package MemoryFileSystem;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Entry {
    protected List<Entry> contents;

    public Directory(Directory parent, String name) {
        super(parent, name);
        this.contents = new ArrayList<>();
    }

    public List<Entry> getContents() {
        return contents;
    }

    @Override
    public int size() {
        int size = 0;
        for (Entry e : contents) {
            size += e.size();
        }
        return size;
    }

    public int numberOfFiles() {
        int cnt = 0;
        for (Entry e : contents) {
            if (e instanceof  Directory) {
                cnt++;
                Directory d =(Directory) e;
                cnt += d.numberOfFiles();
            } else {
                cnt++;
            }
        }
        return cnt;
    }

    public boolean deleteEntry(Entry e) {
        return contents.remove(e);
    }

    public boolean addEntry(Entry e) {
        return contents.add(e);
    }

    public Entry getChild(String component) {
        for (Entry e : contents) {
            if (component.equals(e.name))
                return e;
        }
        return null;
    }
}
