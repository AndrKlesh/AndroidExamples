package com.example.pcexample.view.main.meters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcexample.R
import com.example.pcexample.presentation.main.MainPresenter
import com.example.pcexample.view.abstractions.main.IMeterListViewItem

/**
 * Элемент списка устройств
 */
class MeterListViewHolder(val presenter: MainPresenter, view: View) :
  RecyclerView.ViewHolder(view),
  View.OnClickListener,
  IMeterListViewItem {
  /**
   * Колбэк, вызываемый при привязке элемента к списку
   */
  fun onBind() {
    //В нем инициализируем переменные и элементы управления
    nameTextView = itemView.findViewById(R.id.meter_name_text_view)
    indicationsTextView = itemView.findViewById(R.id.meter_indications_text_view)
    itemView.setOnClickListener(this)
  }

  /**
   * Колбэк, вызываемый при очистке элемента управления
   */
  fun onCleanup() {
    //В нем освобождаем ресурсы
    itemView.setOnClickListener(null)
  }

  override fun setName(name: String) {
    nameTextView.text = name
  }

  override fun setIndications(value: String) {
    indicationsTextView.text = value
  }

  override fun onClick(v: View?) {
    if(v === itemView){
      presenter.onItemClick(adapterPosition)
    }
  }

  private lateinit var nameTextView : TextView
  private lateinit var indicationsTextView : TextView

}