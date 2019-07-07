package co.id.indoquizz.base.view

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.Unbinder
import co.id.indoquizz.R
import co.id.indoquizz.base.contract.BaseContract
import co.id.indoquizz.base.view.dialog.BaseJavaDialog
import co.id.indoquizz.base.view.dialog.ProgressDialog
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import pl.aprilapps.easyphotopicker.EasyImage
import java.util.*

abstract class BaseFragment : Fragment(), BaseContract.View {
    var progressDialog: ProgressDialog? = null
    var infoDialog: BaseJavaDialog? = null
    lateinit var unbinder: Unbinder

    override fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.isAdded)
            progressDialog?.dismiss()
    }

    override fun showError(message: String) {
        progressDialog!!.description = message
    }

    override fun showInfo(msg: String) {
        if (infoDialog != null && infoDialog!!.isAdded)
            initInfoDialog()
        infoDialog!!.description = msg
        infoDialog!!.show(fragmentManager!!)
    }

    override fun showProgressDialog() {
        if (progressDialog == null)
            initProgressDialog()
        progressDialog!!.show(fragmentManager!!)
    }

    fun showActivityAndFinishCurent(intent: Intent) {
        showActivity(intent)
        activity?.finish()
    }

    fun showDialogWithCancel(message: String, okListener: View.OnClickListener) {
        infoDialog!!.description = message
        infoDialog!!.hideBtnCancel(false)
        infoDialog!!.okClickListener = okListener
        infoDialog!!.show(fragmentManager)
    }

    fun showActivity(intent: Intent) {
        startActivity(intent)
    }

    fun showActivityAndFinishAllActivity(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        showActivityAndFinishCurent(intent)
    }

    fun getIntent(context: Context, cls: Class<*>): Intent {
        return Intent(context, cls)
    }

    fun getInflate(layoutInflater: LayoutInflater, layoutResID: kotlin.Int, container: ViewGroup?): View {
        val inflate = layoutInflater.inflate(layoutResID, container, false)
        unbinder = ButterKnife.bind(this, inflate)
        initProgressDialog()
        initInfoDialog()
        return inflate
    }

    private fun initInfoDialog() {
        infoDialog = BaseJavaDialog()
        infoDialog!!.hideBtnCancel(true)
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }

    fun navigateTo(navigationContainerId: Int, actionId: Int, bundle: Bundle?) {
        activity?.let {
            Navigation.findNavController(it, navigationContainerId)
                .navigate(actionId, bundle)
        }
    }

    fun navigateTo(navigationContainerId: Int, actionId: Int) {
        navigateTo(navigationContainerId, actionId, null)
    }

    fun navigateToAndFinishCurrent(navigationContainerId: Int, actionId: Int) {
        navigateTo(navigationContainerId, actionId)
        activity?.finish()
    }

    fun configureItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    fun configureHorizontalItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    fun configureGridItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView,
        span: Int
    ) {
        recyclerView.layoutManager = GridLayoutManager(activity, span)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    protected fun configureToolbarNoTitle(toolbar: Toolbar) {
        toolbar.title = ""
        val activities = (activity as AppCompatActivity)
        activities.setSupportActionBar(toolbar)
        Objects.requireNonNull(activities.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        activities.supportActionBar?.setDisplayShowTitleEnabled(true)
        activities.supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            resources.getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    protected fun configureEmptyToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        val activities = (activity as AppCompatActivity)
        activities.setSupportActionBar(toolbar)
        Objects.requireNonNull(activities.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        activities.supportActionBar?.setDisplayShowTitleEnabled(true)
        activities.supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            resources.getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    protected fun configureEmptyToolbarNoHome(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        val activities = (activity as AppCompatActivity)
        activities.setSupportActionBar(toolbar)
        Objects.requireNonNull(activities.supportActionBar)?.setDisplayHomeAsUpEnabled(false)
        activities.supportActionBar?.setDisplayShowTitleEnabled(true)
        activities.supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    protected fun configureEasyImage() {
        EasyImage.configuration(activity)
            .setImagesFolderName("quizIndo")
            .saveInAppExternalFilesDir()
            .saveInRootPicturesDirectory()
    }

    protected fun removeEasyImageConfiguration() {
        EasyImage.clearConfiguration(activity)
    }
}