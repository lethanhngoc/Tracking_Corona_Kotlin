package Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tracking_corona.R
import kotlinx.android.synthetic.main.fragment_info.*


class info_treatment : Fragment() {
        lateinit var button : Button
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_info, container, false)
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            button = view?.findViewById<Button>(R.id.button_danh_cho_hs)

            button.setOnClickListener {
                val popupMenu: PopupMenu = PopupMenu(getContext(),button)
                popupMenu.menuInflater.inflate(R.menu.drop_down_menu1,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when(item.itemId) {
                        R.id.dropdown_menu1 ->
                            context?.let{ Toast.makeText(getContext(), "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()}
                        R.id.dropdown_menu2 ->
                            context?.let{Toast.makeText(getContext(), "You Clicked : " + item.title, Toast.LENGTH_SHORT).show()}
                    }
                    true
                })
                popupMenu.show()
            }
        }
}