package co.id.indoquizz.base.contract

class BaseContract {
    interface View {
        fun showProgressDialog()
        fun dismissProgressDialog()
        fun showError(message: String)
        fun showInfo(msg: String)
    }

    interface Presenter {
    }
}