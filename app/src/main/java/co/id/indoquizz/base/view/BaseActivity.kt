package co.id.indoquizz.base.view

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import co.id.indoquizz.R
import co.id.indoquizz.base.contract.BaseContract
import co.id.indoquizz.base.view.dialog.ProgressDialog
import co.id.indoquizz.utils.PreferenceManager
import co.id.indoquizz.utils.compressor.Compressor
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.io.IOException
import java.util.*


abstract class BaseActivity : AppCompatActivity(), BaseContract.View {

    lateinit var progressDialog: ProgressDialog
    var preferenceManager: PreferenceManager? = null

    override fun setContentView(layout: Int) {
        super.setContentView(layout)
        ButterKnife.bind(this)
        initProgressDialog()
        initPreference()
    }

    private fun initPreference() {
        preferenceManager = PreferenceManager(this)
    }

    private fun getPreference(): PreferenceManager {
        if (preferenceManager == null)
            initPreference()
        return preferenceManager!!
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun showProgressDialog(title: String, message: String) {
        progressDialog.title = title
        progressDialog.description = message
        progressDialog.show(supportFragmentManager)
    }

    fun showProgressDialog(message: String) {
        progressDialog.description = message
        progressDialog.show(supportFragmentManager)
    }

    override fun showProgressDialog() {
        if (!progressDialog.isAdded)
            showProgressDialog("Nyantai Bentar . . .")
    }

    override fun dismissProgressDialog() {
        if (progressDialog.isAdded)
            progressDialog.dismiss()
    }

    fun showDialogInfoAndFinish(msg: String) {
        alert {
            isCancelable = false
            message = msg
            yesButton { onBackPressed() }
        }.show()
    }

    fun showDialogError(msg: String) {
        if (progressDialog.isAdded)
            dismissProgressDialog()
        alert {
            message = msg
        }.show()
    }

    override fun showError(message: String) {
        showDialogError(message)
    }

    override fun showInfo(msg: String) {
        if (progressDialog.isAdded)
            dismissProgressDialog()
        alert {
            message = msg
        }.show()
    }

    fun showActivityAndFinishCurent(intent: Intent) {
        showActivity(intent)
        finish()
    }

    fun showActivityAndFinishAllActivity(intent: Intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        showActivityAndFinishCurent(intent)
    }

    fun showActivity(intent: Intent) {
        startActivity(intent)
    }

    fun getIntent(context: Context, cls: Class<*>): Intent {
        return Intent(context, cls)
    }

    fun configureItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.isFocusable = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    fun configureHorizontalItemAdapter(
        adapter: FastItemAdapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.isFocusable = false
        if (!adapter.isSelectable)
            adapter.withSelectable(true)
    }

    protected fun configureToolbarWithHomeAndTitle(toolbar: Toolbar, title: String?) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            resources.getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    protected fun configureToolbarEmpty() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        this.setSupportActionBar(toolbar)
        Objects.requireNonNull(this.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowTitleEnabled(true)
        this.supportActionBar?.setDisplayShowHomeEnabled(true)
        Objects.requireNonNull<Drawable>(toolbar.navigationIcon).setColorFilter(
            resources.getColor(R.color.black),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    protected fun configureEasyImage() {
        EasyImage.configuration(this)
            .setImagesFolderName("AkoeImage")
            .saveInAppExternalFilesDir()
            .saveInRootPicturesDirectory()
    }

    protected fun removeEasyImageConfiguration() {
        EasyImage.clearConfiguration(this)
    }

    @Throws(IOException::class)
    protected fun compressFile(file: File): File {
        return Compressor(this)
            .setQuality(50)
            .compressToFile(file)
    }
}