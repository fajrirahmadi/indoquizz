package co.id.indoquizz.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.id.indoquizz.R
import co.id.indoquizz.objects.adapter.VoucherAdapter
import co.id.indoquizz.base.view.BaseFragment
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.fragment_voucher.*
import kotlinx.android.synthetic.main.toolbar_layout_center_title.*

class VoucherFragment : BaseFragment() {

    private val promoAdapter = FastItemAdapter<VoucherAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getInflate(inflater, R.layout.fragment_voucher, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureEmptyToolbarNoHome(view)
        titleToolbar.text = "Promo"
        configureItemAdapter(promoAdapter, voucherListRecycleView)
        voucherListRecycleView.isFocusable = false
        initDummyData()
    }

    private fun initDummyData() {
        promoAdapter.clear()
        promoAdapter.add(VoucherAdapter(R.drawable.promo_01, false))
        promoAdapter.add(VoucherAdapter(R.drawable.promo_02, true))
        promoAdapter.add(VoucherAdapter(R.drawable.promo_03, false))
    }
}