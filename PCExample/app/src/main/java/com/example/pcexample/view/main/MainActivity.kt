package com.example.pcexample.view.main

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pcexample.R
import com.example.pcexample.presentation.main.MainPresenter
import com.example.pcexample.view.abstractions.main.IMainView
import com.example.pcexample.view.dialogs.WarningDialogFragment
import com.example.pcexample.view.main.meters.MetersRowAdapter

class MainActivity :
  AppCompatActivity(),
  IMainView {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    //Ищем RecyclerView в разметке
    recyclerView = findViewById<RecyclerView?>(R.id.main_meters_recycler_view)
      .apply {
        //Устанавливаем ему адаптер
        adapter = this@MainActivity.adapter
      }
    val param = intent.getIntExtra(PARAMETER_NAME, 0)
    presenter.onParameterChanged(param)
    presenter.onViewCreated(this)

  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDestroy()
  }

  override fun setHeader(header: String) {
    supportActionBar?.title = header
  }

  @SuppressLint("NotifyDataSetChanged")
  override fun refreshItems() {
    adapter.notifyDataSetChanged()
  }

  override fun showDialog() {
    val dialog = WarningDialogFragment(presenter)
    dialog.show(supportFragmentManager, "")
  }



  private lateinit var recyclerView: RecyclerView
  private val presenter = MainPresenter()
  private val adapter = MetersRowAdapter(presenter)

  companion object {
    const val PARAMETER_NAME = "ID"
  }
}