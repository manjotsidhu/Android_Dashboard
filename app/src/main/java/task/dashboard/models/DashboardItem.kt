package task.dashboard.models

interface DashboardItem {

    val itemType: Int

    companion object {
        val TYPE_ITEM = 0
        val TYPE_HEADER = 1
    }
}
