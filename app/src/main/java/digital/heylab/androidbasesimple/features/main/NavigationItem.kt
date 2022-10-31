package digital.heylab.androidbasesimple.features.main

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", android.R.drawable.ic_menu_compass,  "Home")
    object Search : NavigationItem("search", android.R.drawable.ic_menu_search, "Search")
}
