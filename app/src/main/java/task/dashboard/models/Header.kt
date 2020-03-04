package task.dashboard.models

class Header(internal var id: Int, internal var name: String) : DashboardItem {

    override val itemType: Int
        get() = 1
}
