package code.iamstcom.tictactoe

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import java.util.*

/**
 * Created by dev101 on 1/13/15.
 */
class SoftKeyboardStateHelper(
    private val activityRootView: View,
    var isSoftKeyboardOpened: Boolean
) :
    OnGlobalLayoutListener {
    var LIMIT = 100f

    interface SoftKeyboardStateListener {
        fun onSoftKeyboardOpened(keyboardHeightInPx: Int)
        fun onSoftKeyboardClosed()
    }

    private val listeners: MutableList<SoftKeyboardStateListener> =
        LinkedList()

    /**
     * Default value is zero (0)
     *
     * @return last saved keyboard height in px
     */
    var lastSoftKeyboardHeightInPx = 0
        private set

    constructor(context: Context, activityRootView: View) : this(
        activityRootView,
        false
    ) {
        val r = context.resources
        LIMIT = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, r.displayMetrics)
    }

    override fun onGlobalLayout() {
        val r = Rect()
        //r will be populated with the coordinates of your view that area still visible.
        activityRootView.getWindowVisibleDisplayFrame(r)
        val heightDiff = activityRootView.rootView.height - (r.bottom - r.top)
        if (!isSoftKeyboardOpened && heightDiff > LIMIT) { // if more than 100 pixels, its probably a keyboard...
            isSoftKeyboardOpened = true
            notifyOnSoftKeyboardOpened(heightDiff)
        } else if (isSoftKeyboardOpened && heightDiff < LIMIT) {
            isSoftKeyboardOpened = false
            notifyOnSoftKeyboardClosed()
        }
    }

    fun addSoftKeyboardStateListener(listener: SoftKeyboardStateListener) {
        listeners.add(listener)
    }

    fun removeSoftKeyboardStateListener(listener: SoftKeyboardStateListener?) {
        listeners.remove(listener)
    }

    private fun notifyOnSoftKeyboardOpened(keyboardHeightInPx: Int) {
        lastSoftKeyboardHeightInPx = keyboardHeightInPx
        for (listener in listeners) {
            listener?.onSoftKeyboardOpened(keyboardHeightInPx)
        }
    }

    private fun notifyOnSoftKeyboardClosed() {
        for (listener in listeners) {
            listener?.onSoftKeyboardClosed()
        }
    }

    init {
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener(this)
    }
}