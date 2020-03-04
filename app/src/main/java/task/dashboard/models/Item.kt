package task.dashboard.models

class Item(private val i: Int, val name: String, private val a: String, val photo: String) : DashboardItem {

    override val itemType: Int
        get() = 0
}
