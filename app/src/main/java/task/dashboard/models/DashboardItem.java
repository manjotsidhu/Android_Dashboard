package task.dashboard.models;

public interface DashboardItem {
    int TYPE_ITEM = 0;
    int TYPE_HEADER = 1;

    int getItemType();
}
