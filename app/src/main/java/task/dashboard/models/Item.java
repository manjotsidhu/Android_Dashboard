package task.dashboard.models;

public class Item implements DashboardItem{
    private int i;
    private String n;
    private String a;
    private String p;

    public Item (int i, String n, String a, String p) {
        this.i = i;
        this.n = n;
        this.a = a;
        this.p = p;
    }

    public String getName() {
        return n;
    }

    public String getPhoto() {
        return p;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
