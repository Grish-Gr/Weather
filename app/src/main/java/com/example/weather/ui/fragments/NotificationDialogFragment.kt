package com.example.weather.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.weather.R
import com.example.weather.databinding.FragmentNotificationBinding
import com.example.weather.viewmodels.NotificationViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationDialogFragment: DialogFragment() {

    private lateinit var binding: FragmentNotificationBinding
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction(){
        binding.cancelDialog.setOnClickListener {
            dismiss()
        }
        binding.successDialog.setOnClickListener {
            when (binding.radioGroupInterval.checkedRadioButtonId){
                binding.intervalNever.id -> showDeleteNotification()
                else -> showAssignNotification()
            }
        }
    }

    private fun showAssignNotification(){
        MaterialAlertDialogBuilder(this.context as Context)
            .setTitle(R.string.title_dialog_asign_notification)
            .setMessage(R.string.message_dialog_change_notification)
            .setPositiveButton(R.string.possitive_btn_assign_notification) { _, _ ->
                assignNotification()
                showToast(R.string.content_toast_assign_notificatopn)
                this.dismiss()
            }
            .setNegativeButton(R.string.cancel_btn_assign_notification){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showDeleteNotification(){
        MaterialAlertDialogBuilder(this.context as Context)
            .setTitle(R.string.title_dialog_delete_notification)
            .setMessage(R.string.message_dialog_change_notification)
            .setPositiveButton(R.string.possitive_btn_assign_notification) { _, _ ->
                notificationViewModel.deleteAllNotification()
                showToast(R.string.content_toast_delete_notificatopn)
                this.dismiss()
            }
            .setNegativeButton(R.string.cancel_btn_assign_notification){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showToast(@StringRes resId: Int) =
        Toast.makeText(this.context, resources.getText(resId), Toast.LENGTH_SHORT).show()

    private fun assignNotification(){
        notificationViewModel.assignNotification(
            getNotificationIntervalByHour(),
            binding.checkFeelsLike.isChecked,
            binding.checkDescription.isChecked
        )
    }

    private fun getNotificationIntervalByHour(): Int = when(binding.radioGroupInterval.checkedRadioButtonId){
        binding.interval3Hour.id -> 3
        binding.interval6Hour.id -> 6
        else -> 12
    }
}