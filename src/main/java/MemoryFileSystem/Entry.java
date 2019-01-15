package MemoryFileSystem;

public abstract class Entry {
    protected Directory parent;
    protected long created;
    protected long lastUpdated;
    protected long lastAccessed;
    protected String name;

    public Entry(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
        created = System.currentTimeMillis();
    }

    public boolean delete() {
        if (parent == null)
            return false;
        return parent.deleteEntry(this);
    }

    public abstract int size();

    public String getFullPath() {
        if (parent == null)
            return name;
        return parent.getFullPath() + "/" + name;
    }

    public long getCreationTime() {
        return created;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public long getLastAccessed() {
        return lastAccessed;
    }

    public String getName() {
        return name;
    }

    public void changeName(String n) {
        name = n;
    }
}
