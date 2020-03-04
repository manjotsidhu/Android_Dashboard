package task.dashboard.models;

public class Header implements DashboardItem {
    int id;
    String name;

    public Header(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
