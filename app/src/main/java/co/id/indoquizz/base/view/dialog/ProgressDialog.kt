package co.id.indoquizz.base.view.dialog

import co.id.indoquizz.R


class ProgressDialog : BaseJavaDialog() {
    override fun getLayout(): Int {
        return R.layout.dialog_progress
    }

    override fun getTitle(): String {
        return "Nyantai bentar. . ."
    }
}