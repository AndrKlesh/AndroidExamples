package com.example.pcexample.presentation.main

import android.Manifest
import com.example.pcexample.R
import com.example.pcexample.di.App
import com.example.pcexample.model.abstractions.meters.Meter
import com.example.pcexample.presentation.abstractions.IPresenter
import com.example.pcexample.presentation.abstractions.ISupportWarningDialogPresenter
import com.example.pcexample.view.abstractions.dialogs.IWarningDialogView
import com.example.pcexample.view.abstractions.main.IMainView
import com.example.pcexample.view.abstractions.main.IMeterListViewItem

/**
 * Представитель главного окна
 */
class MainPresenter :
  IPresenter<IMainView>,
  ISupportWarningDialogPresenter {
  override fun onViewCreated(view: IMainView) {
    this.view = view
    val username = App.loginService.username
    view.setHeader(username)
    if(parameter == 0) {
      meters = App.metersService.getMeters(username)
    } else {
      meters = emptyList()
    }
  }

  override fun onDestroy() {
    view = null
  }

  override fun onDialogCreate(view: IWarningDialogView) {
    if(currentClickedPosition < 0){
      return
    }
    view.setTitle(meters[currentClickedPosition].name)
    view.setWarningMessage(meters[currentClickedPosition].indications.toString())
    view.setWarningDrawable(R.drawable.baseline_electric_meter_24)
  }

  override fun onDialogDestroy() {
    currentClickedPosition = -1
  }

  override fun onOkButtonClick() {
  }

  override fun onCancelButtonClick() {
  }

  fun onParameterChanged(parameter: Int) {
    this.parameter = parameter
  }

  /**
   * Колбэк при создании элемента списка
   * @param view Представление элемента списка счетчиков
   * @param position Индекс позиции, по которой будет отображен элемент
   */
  fun onBindViewItem(view: IMeterListViewItem, position: Int) {
    view.setName(meters[position].name)
    view.setIndications(meters[position].indications.toString())
  }

  /**
   * Колбэк при нажатии на элемент списка устройств
   */
  fun onItemClick(position: Int) {
    currentClickedPosition = position
    view?.showDialog()
  }

  /**
   * Кол-во элементов в списке
   */
  val itemsCount
    get() = meters.size

  private lateinit var meters: List<Meter>
  private var view: IMainView? = null
  private var currentClickedPosition: Int = -1
  private var parameter: Int = 0
}