package tech.skot.libraries.map

import tech.skot.core.components.SKComponentVC
import tech.skot.core.components.SKLayoutIsSimpleView
import tech.skot.core.view.Color
import tech.skot.core.view.Icon


/**
 * # SKMap
 * ## This component can be used if you want to show a map in your application
 * @property markers the list of [markers][Marker] shown on the map
 * @property onMapClicked a function type called when map clicked, can be null if map click is not used
 * @property selectedMarker the currentSelected Marker, use it to select a marker instead of previous, or to unselect
 *
 */
@SKLayoutIsSimpleView
interface SKMapVC : SKComponentVC {
    var markers: List<Marker>
    var onMapClicked: ((Pair<Double, Double>) -> Unit)?
    val onMarkerClick : (Marker) -> Unit
    var selectedMarker: Marker?


    /**
     *  function to call for moving camera on another location
     *  @param pos a [Pair] of [Double] representing the requested location for the center of the map
     *  @param zoomLevel a [Float] representing the zoom level to use
     *  @param animate true if the position change must be animated, false otherwise
     */
    fun setCameraPosition(position: Pair<Double, Double>, zoomLevel: Float, animate: Boolean)

    fun centerOnPositions(positions : List<Pair<Double, Double>>)


    /**
     * data class representing a marker to show on the map
     * @param itemId unique id for the marker, used to update position
     * @param normalIcon the [Icon] to use when marker is not selected, null to use google mapView default icon
     * @param selectedIcon the [Icon] to use when marker is selected, null to use google mapView default icon
     * @param position a [Pair] of [Double] representing the location of the marker
     * @param onMarkerClick a function type called when marker is clicked
     */
    sealed class Marker(
        open val itemId : String?,
        open val position: Pair<Double, Double>,
        open val onMarkerClick: () -> Unit
    )

    open class IconMarker(
        override val itemId : String?,
        open val normalIcon: Icon,
        open val selectedIcon: Icon,
        override val position: Pair<Double, Double>,
        override val onMarkerClick: () -> Unit
    ) : Marker(itemId, position, onMarkerClick)

    class ColorizedIconMarker(
        override val itemId : String?,
        val icon: Icon,
        val normalColor : Color,
        val selectedColor : Color,
        override val position: Pair<Double, Double>,
        override val onMarkerClick: () -> Unit
    ) : Marker(itemId, position, onMarkerClick)

    class   CustomMarker(
        override val itemId : String?,
        val data : Any,
        override val position: Pair<Double, Double>,
        override val onMarkerClick: () -> Unit
    ) : Marker(itemId, position, onMarkerClick)


}




