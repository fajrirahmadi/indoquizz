package co.id.indoquizz

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import butterknife.BindView
import co.id.indoquizz.base.view.BaseActivity
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class MainActivity : BaseActivity() {
    @BindView(R.id.bottomBar)
    lateinit var bottomBar: BottomNavigationViewEx
    @BindView(R.id.frameHome)
    lateinit var homeFrame: FrameLayout
    @BindView(R.id.frameFind)
    lateinit var findFrame: FrameLayout
    @BindView(R.id.frameVoucher)
    lateinit var voucherFrame: FrameLayout
    @BindView(R.id.frameProfile)
    lateinit var profileFrame: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureBottomBar()
        showHomeFragment()
    }

    private fun configureBottomBar() {
        bottomBar.enableAnimation(false)
        bottomBar.isItemHorizontalTranslationEnabled = false
        bottomBar.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED
        bottomBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> showHomeFragment()
                R.id.bottom_find -> showFindFragment()
                R.id.bottom_voucher -> showVoucherFragment()
                R.id.bottom_profile -> showProfileFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun showProfileFragment() {
        homeFrame.visibility = View.GONE
        findFrame.visibility = View.GONE
        voucherFrame.visibility = View.GONE
        profileFrame.visibility = View.VISIBLE
    }

    private fun showVoucherFragment() {
        homeFrame.visibility = View.GONE
        findFrame.visibility = View.GONE
        voucherFrame.visibility = View.VISIBLE
        profileFrame.visibility = View.GONE
    }

    private fun showFindFragment() {
        homeFrame.visibility = View.GONE
        findFrame.visibility = View.VISIBLE
        voucherFrame.visibility = View.GONE
        profileFrame.visibility = View.GONE
    }

    private fun showHomeFragment() {
        homeFrame.visibility = View.VISIBLE
        findFrame.visibility = View.GONE
        voucherFrame.visibility = View.GONE
        profileFrame.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (homeFrame.visibility == View.GONE) {
            bottomBar.selectedItemId = R.id.bottom_home
        } else
            super.onBackPressed()
    }
}