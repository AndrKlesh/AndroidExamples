package com.example.pcexample.view.abstractions.dialogs

import android.app.ActionBar.LayoutParams
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.pcexample.R
import com.example.pcexample.presentation.abstractions.ISupportDialogPresenter
import androidx.appcompat.widget.LinearLayoutCompat

abstract class BaseDialogFragment<TDialogView : IDialogView>(
  private val presenter: ISupportDialogPresenter<TDialogView>
) :
  DialogFragment(),
  View.OnClickListener,
  IDialogView {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.base_dialog, container, false)
    contentDialogLayout = view.findViewById(R.id.dialog_content_layout)
    titleTextView = view.findViewById(R.id.dialog_title_text_view)
    positiveButton = view.findViewById(R.id.dialog_positive_button)
    positiveButton?.setOnClickListener(this)
    negativeButton = view.findViewById(R.id.dialog_negative_button)
    negativeButton?.setOnClickListener(this)
    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    @Suppress("UNCHECKED_CAST")
    presenter.onDialogCreate(this as TDialogView)
  }

  override fun onResume() {
    super.onResume()
    dialog?.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.onDialogDestroy()
    positiveButton?.setOnClickListener(null)
    negativeButton?.setOnClickListener(null)
  }

  override fun setTitle(stringResourceId: Int) {
    titleTextView?.setText(stringResourceId)
  }

  override fun setTitle(title: String) {
    titleTextView?.text = title
  }

  override fun setTitleDrawable(resourceDrawableId: Int) {
    titleTextView?.setCompoundDrawablesRelativeWithIntrinsicBounds(resourceDrawableId, 0, 0, 0)
  }

  override fun onClick(view: View?) {
    when (view?.id) {
      R.id.dialog_positive_button -> presenter.onOkButtonClick()
      R.id.dialog_negative_button -> presenter.onCancelButtonClick()
    }
    dialog?.dismiss()
  }

  protected var contentDialogLayout: LinearLayoutCompat? = null
  private var titleTextView: TextView? = null
  private var positiveButton: Button? = null
  private var negativeButton: Button? = null
}