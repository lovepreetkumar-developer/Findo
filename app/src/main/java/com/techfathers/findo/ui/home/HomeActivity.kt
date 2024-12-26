package com.techfathers.findo.ui.home

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.techeytech.rich_dad_poor_dad.data.preferences.PreferenceProvider
import com.techfathers.findo.R
import com.techfathers.findo.databinding.ActivityHomeBinding
import com.techfathers.findo.databinding.BottomSheetDialogBinding
import com.techfathers.findo.util.rateApplication
import com.techfathers.findo.util.shareApplication
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), View.OnClickListener, KodeinAware {

    override val kodein by kodein()
    private val preferences: PreferenceProvider by instance()

    private lateinit var binding: ActivityHomeBinding
    private lateinit var notificationManager: NotificationManager
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.rlBack.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
        binding.imgShare.setOnClickListener(this)
        binding.tvAbout.setOnClickListener(this)
        binding.imgLike.setOnClickListener(this)

        setSavedNumbers()
        prepareBottomSheet()
    }

    private fun setSavedNumbers() {
        if (preferences.getFirstNumber() != "null")
            binding.etFirstNo.setText(preferences.getFirstNumber())
        if (preferences.getSecondNumber() != "null")
            binding.etSecondNo.setText(preferences.getSecondNumber())
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.rl_back -> onBackPressed()
            R.id.img_share -> shareApplication(this)
            //R.id.btn_save -> saveBothNumbers()
            R.id.tv_about -> bottomSheetDialog.show()
            R.id.btn_close -> bottomSheetDialog.dismiss()
            R.id.img_like -> rateApplication(this)
        }
    }

    private fun saveBothNumbers() {
        /*Coroutines.main {
            try {
                askPermission(
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_PHONE_STATE
                )
                //all permissions already granted or just granted
                //your action

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted) {

                    val intent = Intent(
                        Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS
                    )
                    startActivity(intent)

                    toast("Enable Findo")
                } else {
                    hideKeyboard(this)
                    preferences.setFirstNumber(binding.etFirstNo.text.toString())
                    preferences.setSecondNumber(binding.etSecondNo.text.toString())
                    binding.parent.snackBar("Numbers has been Saved")
                }

            } catch (e: PermissionException) {
                if (e.hasDenied()) {
                    //binding.root.snackBar("Denied")
                    //appendText(resultView, "Denied :")
                    //the list of denied permissions
                    *//*e.denied.forEach { permission ->
                        //binding.root.snackBar(permission)
                        //appendText(resultView, permission)
                    }*//*
                    //but you can ask them again, eg:

                    AlertDialog.Builder(this@HomeActivity)
                        .setMessage("Please accept our permissions to work app properly in background")
                        .setPositiveButton("yes") { _, _ ->
                            e.askAgain()
                        }
                        .setNegativeButton("no") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }

                if (e.hasForeverDenied()) {
                    //appendText(resultView, "ForeverDenied")
                    //the list of forever denied permissions, user has check 'never ask again'
                    e.foreverDenied.forEach { permission ->
                        binding.root.snackBar(permission)
                        //appendText(resultView, permission)
                    }
                    //you need to open setting manually if you really need it
                    e.goToSettings()
                }
            }
        }*/
    }

    private fun prepareBottomSheet() {
        val layoutInflater =
            this.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val sheetDialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(sheetDialogBinding.root)
        sheetDialogBinding.btnClose.setOnClickListener(this)
    }

}