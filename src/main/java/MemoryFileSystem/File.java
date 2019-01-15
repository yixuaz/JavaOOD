package MemoryFileSystem;

public class File extends Entry {
    private String content;
    private int size;

    public File(Directory parent, String name,  int size) {
        super(parent, name);
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int size() {
        return size;
    }
}
