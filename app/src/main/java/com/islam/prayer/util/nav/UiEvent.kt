
import com.islam.prayer.util.nav.Route

sealed class UiEvent{
    data class Navigate(val route: Route): UiEvent()
}